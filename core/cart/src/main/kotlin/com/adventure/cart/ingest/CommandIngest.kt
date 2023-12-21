package com.adventure.cart.ingest

import com.adventure.apis.cart.Commands
import com.adventure.apis.cart.Commands.*
import org.axonframework.commandhandling.CommandHandler

class CommandIngest {
    @CommandHandler
    fun handle(command: AddProductToCart) {
        TODO()
    }

    @CommandHandler
    fun handle(command: RemoveProductFromCart) {
        TODO()
    }

    @CommandHandler
    fun handle(command: Checkout) {
        TODO()
    }

}