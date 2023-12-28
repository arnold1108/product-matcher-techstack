package com.adventure.store.model

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries
import com.adventure.apis.store.Queries.ManageStore

sealed class Messages {
    data class AddStoreCommand(
        val command: CreateStore
    ): Messages()
    data class AddProductCommand(
        val command: AddStock
    ): Messages()
    data class ManageStoreQuery(
        val query: ManageStore
    ): Messages()
}