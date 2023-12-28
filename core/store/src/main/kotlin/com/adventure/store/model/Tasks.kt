package com.adventure.store.model

import akka.actor.typed.ActorRef
import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import java.util.UUID

sealed class Tasks {
    data class AddStore(
        val messageId: UUID,
        val command: CreateStore,
        val replyTo: ActorRef<Messages>
    ): Tasks()
    data class AddProduct(
        val messageId: UUID,
        val command: AddStock,
        val replyTo: ActorRef<Messages>
    ): Tasks()
}