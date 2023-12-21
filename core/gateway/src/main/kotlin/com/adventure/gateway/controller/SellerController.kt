package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands
import com.adventure.apis.accounts.Commands.CreateSellerAccount
import com.adventure.apis.accounts.State
import com.adventure.apis.accounts.State.Sex
import com.adventure.apis.store.Category
import com.adventure.apis.store.Category.StoreCategory
import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.QueryResults
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.UUID

@RestController
class SellerController(
    private val command: ReactorCommandGateway
) {
    @PostMapping("/seller/account/create")
    fun createSellerAccount(
        @RequestBody
        firstName: String,
        lastName: String,
        emailAddress: String,
        gender: Sex
    ): Mono<ResponseEntity<String>> {
        val sellerAccountCreateRequest = CreateSellerAccount(
            sellerId = UUID.randomUUID(),
            firstName = firstName,
            lastName = lastName,
            email = emailAddress,
            gender = gender
        )
        return command.send(sellerAccountCreateRequest)
    }
    @PostMapping("/seller/store/create")
    fun createStore(
        @RequestBody
        sellerId: UUID,
        category: StoreCategory
    ): Mono<ResponseEntity<String>> {
        val createStoreRequest = CreateStore(
            storeId = UUID.randomUUID(),
            sellerId = sellerId,
            category = category
        )
        return command.send(createStoreRequest)
    }
    @PostMapping("/seller/store/{storeId}/stock/add")
    fun addStock(
        @PathVariable
        storeId: UUID,
        @RequestBody
        productName: String,
        productCategory: String,
        productDescription: String,
        price: Double
    ): Mono<ResponseEntity<String>> {
        val addStockRequest = AddStock(
            storeId = storeId,
            productId = UUID.randomUUID(),
            productName = productName,
            productCategory = productCategory,
            productDescription = productDescription,
            price = price
        )
        return command.send(addStockRequest)
    }
    @GetMapping("/seller/store/{storeId}/manage")
    fun manageStore(@PathVariable storeId: UUID): Mono<ResponseEntity<ManageStoreQueryResults>> {
        TODO()
    }
}