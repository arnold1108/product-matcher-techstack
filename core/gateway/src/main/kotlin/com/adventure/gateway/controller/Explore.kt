package com.adventure.gateway.controller

import com.adventure.apis.cart.Commands.AddProductToCart
import com.adventure.apis.marketplace.Commands
import com.adventure.apis.marketplace.QueryResults.ExploreProductsQueryResult
import com.adventure.gateway.utils.Mappings.ADD_PRODUCT_TO_CART_MAPPING
import com.adventure.gateway.utils.Mappings.EXPLORE_PRODUCT_MAPPING
import com.adventure.gateway.utils.Mappings.LIKE_PRODUCT_MAPPING
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import reactor.core.publisher.Mono
import java.util.*

class Explore (private val command: ReactorCommandGateway) {
    @GetMapping(EXPLORE_PRODUCT_MAPPING)
    fun exploreProducts(@PathVariable("buyer_id") buyerId: UUID): Mono<ResponseEntity<ExploreProductsQueryResult>> {
        TODO()
    }

    @PostMapping(LIKE_PRODUCT_MAPPING)
    fun likeProduct(
        @PathVariable("product_id") productId: UUID,
        @PathVariable("buyer_id") buyerId: UUID
    ): Mono<ResponseEntity<String>> {
        val likeProductCommand = Commands.LikeProduct(
            buyerId = buyerId,
            productId = productId
        )
        return command.send<ResponseEntity<String>?>(likeProductCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Product Liked"))
    }

    @PostMapping(ADD_PRODUCT_TO_CART_MAPPING)
    fun addProductToCart(
        @PathVariable("buyer_id") buyerId: UUID,
        @PathVariable("product_id") productId: UUID,
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

}