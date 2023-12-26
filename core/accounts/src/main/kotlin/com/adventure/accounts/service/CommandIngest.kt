package com.adventure.accounts.service

import com.adventure.apis.accounts.Commands.*
import org.axonframework.commandhandling.CommandHandler
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
class CommandIngest() {
    @CommandHandler
    fun handle(command: CreateSellerAccount){

    }

    @CommandHandler
    fun handle(command: CreateBuyerAccount) {
        TODO()
    }
}