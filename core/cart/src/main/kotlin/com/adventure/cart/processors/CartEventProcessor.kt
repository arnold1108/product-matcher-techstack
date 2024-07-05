package com.adventure.cart.processors

import com.adventure.apis.cart.Events.*
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service


@Service
class CartEventProcessor {

    @EventHandler
    fun handle(event: ProductAddedToCart) {
        TODO()
    }

    @EventHandler
    fun handle(event: ProductRemovedFromCart) {
        TODO()
    }

    @EventHandler
    fun handle(event: CheckingOutCart) {
        TODO()
    }
}


