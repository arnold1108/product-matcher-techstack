package com.adventure.accounts.ingest

import com.adventure.apis.accounts.Commands.*
import com.adventure.apis.store.Commands
import com.adventure.apis.store.Commands.*
import org.axonframework.commandhandling.CommandHandler

class CommandIngest {
    @CommandHandler
    fun handle(command: CreateSellerAccount){
        TODO()
    }

    @CommandHandler
    fun handle(command: CreateBuyerAccount) {
        TODO()
    }
}