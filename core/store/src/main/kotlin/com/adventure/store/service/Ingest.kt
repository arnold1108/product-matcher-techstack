package com.adventure.store.service

import com.adventure.apis.store.*
import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries.ManageStoreQuery
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import com.adventure.store.actors.Guardian
import com.adventure.store.model.Messages.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono
import java.util.*

@Service
class Ingest(private val guardian: Guardian) {

    @CommandHandler
    fun handle(command: CreateStore) {
        guardian.actorSystem.tell(AddStoreCommand(command))
    }
    @CommandHandler
    fun handle(command: AddStock) {
        guardian.actorSystem.tell(AddProductCommand(command))
    }
    @QueryHandler
    fun handle(query: ManageStoreQuery): Any {
        return guardian.actorSystem.tell(ManageStore(query))
    }
}
data class StoreDetails(
    val category: String,
    val storeName: String
)
@Controller
class Controller(
    private val command: ReactorCommandGateway,
    private val query: ReactorQueryGateway
){
    @PostMapping("/{sellerId}/seller/store/create")
    fun createStore(
        @PathVariable sellerId: UUID,
        @RequestBody details: StoreDetails
    ): Mono<ResponseEntity<String>> {
        val createStoreCommand = CreateStore(
            storeId = UUID.randomUUID(),
            sellerId = sellerId,
            category = details.category,
            storeName = details.storeName
        )
        return command.send<ResponseEntity<String>>(createStoreCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Successfully Created your store"))
    }
    @PostMapping("/{seller_id}/store/{store_id}/stock/add")
    fun addStock(
        @PathVariable("seller_id") sellerId: UUID,
        @PathVariable("store_id") storeId: UUID,
        @RequestBody request: Requests.AddStockRequest
    ): Mono<ResponseEntity<String>> {
        val addStockCommand = AddStock(
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
            .then()
            .thenReturn(ResponseEntity.ok("${request.productName} Added"))
    }

    @GetMapping("/store/{store_id}/manage")
    fun manageStore(@PathVariable("store_id") storeId: UUID): Mono<ResponseEntity<ManageStoreQueryResults>> {
        val manageStoreQuery = ManageStoreQuery(storeId)
        return query
            .streamingQuery(manageStoreQuery, ManageStoreQueryResults::class.java)
            .next()
            .map { ResponseEntity.ok(it) }
    }


}