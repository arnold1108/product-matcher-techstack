package com.adventure.store.service

import com.adventure.apis.accounts.Commands
import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries.ManageStore
import com.adventure.store.actors.Guardian
import com.adventure.store.model.Messages.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.queryhandling.QueryHandler
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
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
    fun handle(query: ManageStore): Any {
        return guardian.actorSystem.tell(ManageStoreQuery(query))
    }
}
data class StoreDetails(
    val category: String,
    val storeName: String
)
@Controller
class Controller(private val command: ReactorCommandGateway){
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
}