package com.adventure.accounts.service
import com.adventure.accounts.actors.Guardian
import com.adventure.accounts.model.Messages.AddBuyerCommand
import com.adventure.accounts.model.Messages.AddSellerCommand
import com.adventure.apis.accounts.Commands.*
import org.axonframework.commandhandling.CommandHandler
import org.springframework.stereotype.Service

@Service
class CommandIngest(
    private val guardian: Guardian
) {
    @CommandHandler
    fun handle(command: CreateSellerAccount){
        guardian.system.tell(AddSellerCommand(command))
    }

    @CommandHandler
    fun handle(command: CreateBuyerAccount) {
        guardian.system.tell(AddBuyerCommand(command))
    }
}