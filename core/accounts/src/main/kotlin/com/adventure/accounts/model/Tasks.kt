package com.adventure.accounts.model

import akka.actor.typed.ActorRef
import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.Commands.CreateSellerAccount
import java.util.UUID

sealed class Tasks {
    data class AddBuyer(
        val messageId: UUID,
        val commands: CreateBuyerAccount,
        val replyTo: ActorRef<Messages>
    ): Tasks()
    data class AddSeller(
        val messageId: UUID,
        val command: CreateSellerAccount,
        val replyTo: ActorRef<Messages>
    ): Tasks()
}