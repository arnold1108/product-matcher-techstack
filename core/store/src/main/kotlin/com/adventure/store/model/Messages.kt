package com.adventure.store.model

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore

sealed class Messages {
    data class AddStoreCommand(
        val command: CreateStore
    ): Messages()
    data class AddProductCommand(
        val command: AddStock
    ): Messages()
}