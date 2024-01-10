package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.Commands.UserDetails
import com.adventure.apis.cart.Commands.*
import com.adventure.apis.cart.QueryResults.ViewCartQueryResult
import com.adventure.apis.marketplace.Commands.LikeProduct
import com.adventure.apis.marketplace.QueryResults.ExploreProductsQueryResult
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.*

@RestController
class BuyerController(
    private val command: ReactorCommandGateway
) {
    @RequestMapping("/buyer")
    @PostMapping("/account/create")
    fun createBuyerAccount( @RequestBody details: UserDetails): Mono<ResponseEntity<String>> {
        val createBuyerAccountCommand = CreateBuyerAccount(
            buyerId = UUID.randomUUID(),
            details
        )
        return command.send<ResponseEntity<String>?>(createBuyerAccountCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Welcome to Soko!"))
    }

    @GetMapping("/{buyerId}/explore")
    fun exploreProducts(@PathVariable buyerId: UUID): Mono<ResponseEntity<ExploreProductsQueryResult>> {
        TODO()
    }

    @PostMapping("/{buyerId}/explore/{productId}/like")
    fun likeProduct(
        @PathVariable productId: UUID,
        @PathVariable buyerId: UUID
    ): Mono<ResponseEntity<String>> {
        val likeProductCommand = LikeProduct(
            buyerId = buyerId,
            productId = productId
        )
        return command.send<ResponseEntity<String>?>(likeProductCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product Liked"))
    }

    @GetMapping("/{buyerId}/cart")
    fun viewCart(@PathVariable buyerId: UUID): Mono<ResponseEntity<ViewCartQueryResult>> {
        TODO()
    }

    @PostMapping("/{buyerId}/cart/add/{productId}")
    fun addProductToCart(
        @PathVariable buyerId: UUID,
        @PathVariable productId: UUID,
        quantity: Int
        ): Mono<ResponseEntity<String>> {
        val addProductToCartCommand = AddProductToCart(
            buyerId = buyerId,
            productId = productId,
            quantity = quantity
        )
        return command.send<ResponseEntity<String>>(addProductToCartCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product added to cart"))
    }

    @DeleteMapping("/{buyerId}/cart/remove/{productId}")
    fun removeProductFromCart(
        @PathVariable productId: UUID,
        @PathVariable buyerId: UUID
        ): Mono<ResponseEntity<String>> {
        val removeProductFromCartCommand = RemoveProductFromCart(
            productId = productId,
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(removeProductFromCartCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product removed from cart"))
    }

    @PostMapping("/{buyerId}/cart/checkout")
    fun checkout(@PathVariable buyerId: UUID): Mono<ResponseEntity<String>> {
        val checkoutCommand = Checkout(
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(checkoutCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Enjoy!"))
    }

}