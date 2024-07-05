package com.adventure.gateway.service

import com.adventure.apis.store.Commands.*
import com.adventure.apis.store.Queries.*
import com.adventure.apis.store.QueryResults.ManageStoreProjection
import com.adventure.apis.store.Requests.AddStockRequest
import com.adventure.apis.store.Requests.CreateStoreRequest
import com.adventure.gateway.utils.Authorizations.STORE_BELONGS_TO_THE_AUTHENTICATED_USER
import com.adventure.gateway.utils.SecurityUtils.extractPrincipalDetails
import kotlinx.coroutines.reactive.awaitLast
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class StoreService(
    private val command: CommandGateway,
    private val query: QueryGateway
) {
    private val authentication = SecurityContextHolder.getContext().authentication

    fun addStore(request: CreateStoreRequest): String {
        val principal = extractPrincipalDetails(authentication = authentication)
        command.send<Void>(
            CreateStore(
                storeId = UUID.randomUUID(),
                sellerId = principal.principalId,
                category = request.category,
                storeName = request.storeName
            )
        )

        return "${request.storeName} added "
    }

    fun addStock(request: AddStockRequest, storeId: UUID): String {
        val principal = extractPrincipalDetails(authentication = authentication)
        command.send<Void>(
            AddStock(
                sellerId = principal.principalId,
                storeId = storeId,
                productId = UUID.randomUUID(),
                productName = request.productName,
                productCategory = request.productCategory,
                productDescription = request.productDescription,
                price = request.price,
                remainingQuantity = request.quantity,
                timeAdded = request.timeAdded
            )
        )

        return "${request.productName} added"
    }

    @PostAuthorize(STORE_BELONGS_TO_THE_AUTHENTICATED_USER)
    suspend fun getStoreById(storeId: UUID): ManageStoreProjection {
        return query.streamingQuery(
            ManageStoreQuery(storeId = storeId),
            ManageStoreProjection::class.java
        ).awaitLast()
    }

    fun closeStore(storeId: UUID): String {
        val exist = query.query(
            DoesStoreExist(storeId = storeId), Boolean::class.java
        ).get()

        if (exist) {
            command.send<Void>(CloseStore(storeId = storeId))
            return "Store Closed"
        } else {
            throw NoSuchElementException("Store Not Found")
        }
    }

    fun reOpenStore(storeId: UUID): String {
        val exist = query.query(
            DoesStoreExist(storeId = storeId), Boolean::class.java
        ).get()

        if (exist) {
            command.send<Void>(ReOpenStore(storeId = storeId))
            return "Store ReOpened"
        } else {
            throw NoSuchElementException("Store Not Found")
        }
    }

    fun getNotification() {
        TODO("Notifications functionality not yet implemented")
    }

}