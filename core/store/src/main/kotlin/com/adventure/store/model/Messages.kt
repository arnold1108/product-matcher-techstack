package com.adventure.store.model

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries
import com.adventure.apis.store.Queries.ManageStoreQuery
import reactor.core.publisher.Mono
import java.util.UUID

sealed class Messages {
    data class AddStoreCommand(
        val command: CreateStore
    ): Messages()
    data class AddProductCommand(
        val command: AddStock
    ): Messages()
    data class ManageStore(
        val query: ManageStoreQuery
    ): Messages()
    data class StoreAddedFeedback(
        val messageId: UUID,
        val feedback: Mono<String>
    ): Messages()
    data class ProductAddedFeedback(
        val messageId: UUID,
        val feedback: Mono<String>
    ): Messages()
}