package com.adventure.store.service

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries.ManageStore
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class Ingest {
    @CommandHandler
    fun handle(command: CreateStore) {
        TODO("AddStoreCommand")
    }
    @CommandHandler
    fun handle(command: AddStock) {
        TODO("AddProductCommand")
    }
    @QueryHandler
    fun handle(query: ManageStore) {
        TODO()
    }
}