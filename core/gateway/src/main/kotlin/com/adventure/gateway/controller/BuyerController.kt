package com.adventure.gateway.controller

import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.Commands.UserDetails
import com.adventure.apis.cart.Commands.*
import com.adventure.apis.cart.QueryResults.ViewCartQueryResult
import com.adventure.apis.marketplace.Commands.LikeProduct
import com.adventure.apis.marketplace.QueryResults.ExploreProductsQueryResult
import com.adventure.gateway.utils.Mappings.ADD_PRODUCT_TO_CART_MAPPING
import com.adventure.gateway.utils.Mappings.BUYER_ACCOUNT_CREATION_MAPPING
import com.adventure.gateway.utils.Mappings.CHECKOUT_MAPPING
import com.adventure.gateway.utils.Mappings.EXPLORE_PRODUCT_MAPPING
import com.adventure.gateway.utils.Mappings.LIKE_PRODUCT_MAPPING
import com.adventure.gateway.utils.Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING
import com.adventure.gateway.utils.Mappings.VIEW_CART_MAPPING
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
    @PostMapping(BUYER_ACCOUNT_CREATION_MAPPING)
    fun createBuyerAccount( @RequestBody details: UserDetails): Mono<ResponseEntity<String>> {
        val createBuyerAccountCommand = CreateBuyerAccount(
            buyerId = UUID.randomUUID(),
            details
        )
        return command.send<ResponseEntity<String>?>(createBuyerAccountCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Welcome to Soko!"))
    }

    @GetMapping(EXPLORE_PRODUCT_MAPPING)
    fun exploreProducts(@PathVariable buyerId: UUID): Mono<ResponseEntity<ExploreProductsQueryResult>> {
        TODO()
    }

    @PostMapping(LIKE_PRODUCT_MAPPING)
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

    @GetMapping(VIEW_CART_MAPPING)
    fun viewCart(@PathVariable buyerId: UUID): Mono<ResponseEntity<ViewCartQueryResult>> {
        TODO()
    }

    @PostMapping(ADD_PRODUCT_TO_CART_MAPPING)
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

    @DeleteMapping(REMOVE_PRODUCT_FROM_CART_MAPPING)
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

    @PostMapping(CHECKOUT_MAPPING)
    fun checkout(@PathVariable buyerId: UUID): Mono<ResponseEntity<String>> {
        val checkoutCommand = Checkout(
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(checkoutCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Enjoy!"))
    }

}