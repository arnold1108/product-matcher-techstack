package com.adventure.marketplace.ingest

import com.adventure.apis.marketplace.Commands.LikeProduct
import org.axonframework.commandhandling.CommandHandler

class CommandIngest {
    @CommandHandler
    fun handle(command: LikeProduct) {
        TODO()
    }
}