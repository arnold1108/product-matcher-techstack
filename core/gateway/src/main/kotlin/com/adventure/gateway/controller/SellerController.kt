package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands.CreateSellerAccount
import com.adventure.apis.accounts.Commands.UserDetails
import com.adventure.apis.store.Category.StoreCategory
import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID
data class StoreDetails(
    val category: String,
    val storeName: String
)
@RestController
@RequestMapping("/seller")
class SellerController(
    private val command: ReactorCommandGateway
) {
    @PostMapping("/account/create")
    fun createSellerAccount(@RequestBody details: UserDetails): Mono<ResponseEntity<String>> {
        val createSellerAccountCommand = CreateSellerAccount(
            sellerId = UUID.randomUUID(),
            details
        )
        return command.send<ResponseEntity<String>>(createSellerAccountCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Welcome to Soko!"))
    }
    @CommandHandler
    fun handle(command: CreateSellerAccount){
        println("Received command: $command")
    }

    @PostMapping("/{sellerId}/store/create")
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
    @CommandHandler
    fun handle(command: CreateStore){
        println("Received command: $command")
    }

    @PostMapping("/store/{storeId}/stock/add")
    fun addStock(
        @PathVariable
        storeId: UUID,
        @RequestBody
        productName: String,
        productCategory: String,
        productDescription: String,
        price: Double
    ): Mono<ResponseEntity<String>> {
        val addStockCommand = AddStock(
            storeId = storeId,
            productId = UUID.randomUUID(),
            productName = productName,
            productCategory = productCategory,
            productDescription = productDescription,
            price = price
        )
        return command.send<ResponseEntity<String>>(addStockCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product Added"))
    }

    @GetMapping("/store/{storeId}/manage")
    fun manageStore(@PathVariable storeId: UUID): Mono<ResponseEntity<ManageStoreQueryResults>> {
        TODO()
    }
}