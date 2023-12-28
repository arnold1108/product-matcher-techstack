package com.adventure.store.model

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore

sealed class Tasks {
    data class AddStore(
        val command: CreateStore
    ): Tasks()
    data class AddProduct(
        val command: AddStock
    ): Tasks()
}