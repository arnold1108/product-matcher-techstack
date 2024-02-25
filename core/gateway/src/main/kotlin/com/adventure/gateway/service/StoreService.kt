package com.adventure.gateway.service

import com.adventure.apis.store.Commands
import com.adventure.apis.store.Queries
import com.adventure.apis.store.Queries.*
import com.adventure.apis.store.QueryResults
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import com.adventure.apis.store.Requests
import com.adventure.apis.store.Requests.AddStockRequest
import com.adventure.apis.store.Requests.CreateStoreRequest
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class StoreService(
    private val command: ReactorCommandGateway,
    private val query: ReactorQueryGateway
) {

    fun addStore(request: CreateStoreRequest, sellerId: UUID): Mono<String> {
        val createStoreCommand = Commands.CreateStore(
            storeId = UUID.randomUUID(),
            sellerId = sellerId,
            category = request.category,
            storeName = request.storeName
        )
        return command.send<ResponseEntity<String>>(createStoreCommand)
            .thenReturn("Successfully Created your store")
    }

    fun addStock(request: AddStockRequest, sellerId: UUID, storeId: UUID): Mono<String> {
        val addStockCommand = Commands.AddStock(
            sellerId = sellerId,
            storeId = storeId,
            productId = UUID.randomUUID(),
            productName = request.productName,
            productCategory = request.productCategory,
            productDescription = request.productDescription,
            price = request.price,
            remainingQuantity = request.quantity,
            likes = request.likes,
            timeAdded = request.timeAdded
        )
        return command.send<ResponseEntity<String>>(addStockCommand)
            .thenReturn("${request.productName} Added")
    }

    fun getStoreById(storeId: UUID): Mono<ManageStoreQueryResults> {
        val projection = ManageStoreQueryResults::class.java
        return query.query(ManageStoreQuery(storeId = storeId), projection)
    }

}