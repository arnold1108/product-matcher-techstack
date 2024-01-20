package com.adventure.gateway.controller

import com.adventure.apis.cart.Commands
import com.adventure.apis.cart.QueryResults
import com.adventure.apis.cart.QueryResults.*
import com.adventure.gateway.utils.Mappings
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
class Cart(private val command: ReactorCommandGateway) {
    @GetMapping(Mappings.VIEW_CART_MAPPING)
    fun viewCart(@PathVariable("buyer_id") buyerId: UUID): Mono<ResponseEntity<ViewCartQueryResult>> {
        TODO()
    }

    @DeleteMapping(Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING)
    fun removeProductFromCart(
        @PathVariable("product_id") productId: UUID,
        @PathVariable("buyer_id") buyerId: UUID
    ): Mono<ResponseEntity<String>> {
        val removeProductFromCartCommand = Commands.RemoveProductFromCart(
            productId = productId,
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(removeProductFromCartCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product removed from cart"))
    }

    @PostMapping(Mappings.CHECKOUT_MAPPING)
    fun checkout(@PathVariable("buyer_id") buyerId: UUID): Mono<ResponseEntity<String>> {
        val checkoutCommand = Commands.Checkout(
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(checkoutCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Enjoy!"))
    }

}

