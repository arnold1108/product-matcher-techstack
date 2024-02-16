package com.adventure.gateway.controller

import com.adventure.gateway.service.CartService
import com.adventure.gateway.utils.Mappings
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono
import java.util.*

@RestController
class Cart(private val cartService: CartService) {
    @GetMapping(Mappings.VIEW_CART_MAPPING)
    fun viewCart(@PathVariable("buyer_id") buyerId: UUID) = ResponseEntity.ok(cartService.fetchCartById(buyerId))

    @DeleteMapping(Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING)
    fun removeProductFromCart(
        @PathVariable("product_id") productId: UUID,
        @PathVariable("buyer_id") buyerId: UUID
    ): Mono<ResponseEntity<String>> {

        return cartService.removeProductFromCart(buyerId = buyerId, productId = productId)
            .map { ResponseEntity.ok(it) }
    }

    @PostMapping(Mappings.CHECKOUT_MAPPING)
    fun checkout(@PathVariable("buyer_id") buyerId: UUID): Mono<ResponseEntity<String>> {
        return cartService.checkout(buyerId = buyerId)
            .map { ResponseEntity.ok(it) }
    }

}

