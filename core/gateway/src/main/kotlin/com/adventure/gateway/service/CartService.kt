package com.adventure.gateway.service

import com.adventure.apis.cart.Commands
import com.adventure.apis.cart.Queries
import com.adventure.apis.cart.Queries.ViewCart
import com.adventure.apis.cart.QueryResults.*
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class CartService(
    private val query: ReactorQueryGateway,
    private val command: ReactorCommandGateway
) {

    fun fetchCartById( buyerId: UUID): Flux<ViewCartQueryResult> {

        val projection = ViewCartQueryResult::class.java
        return query.query(ViewCart(buyerId = buyerId), projection)
            .flux()

    }

    fun removeProductFromCart( buyerId: UUID, productId: UUID): Mono<String> {

        val removeProductFromCartCommand = Commands.RemoveProductFromCart(
            productId = productId,
            buyerId = buyerId
        )
        return command.send<String>(removeProductFromCartCommand)
            .thenReturn("Product removed from Cart")

    }

    fun checkout( buyerId: UUID): Mono<String> {
        val checkoutCommand = Commands.Checkout(
            buyerId = buyerId
        )
        return command.send<ResponseEntity<String>>(checkoutCommand)
            .thenReturn("Enjoy!")
    }
}