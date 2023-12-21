package com.adventure.store.ingest

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import org.axonframework.commandhandling.CommandHandler

class CommandIngest {
    @CommandHandler
    fun handle(command: CreateStore) {
        TODO()
    }

    @CommandHandler
    fun handle(command: AddStock) {
        TODO()
    }
}