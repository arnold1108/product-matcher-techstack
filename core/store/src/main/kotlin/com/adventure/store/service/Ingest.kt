package com.adventure.store.service

import com.adventure.apis.store.Commands.AddStock
import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.Queries.ManageStore
import com.adventure.store.actors.Guardian
import com.adventure.store.model.Messages.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class Ingest(private val guardian: Guardian) {
    @CommandHandler
    fun handle(command: CreateStore) {
        guardian.actorSystem.tell(AddStoreCommand(command))
    }
    @CommandHandler
    fun handle(command: AddStock) {
        guardian.actorSystem.tell(AddProductCommand(command))
    }
    @QueryHandler
    fun handle(query: ManageStore) {
        guardian.actorSystem.tell(ManageStoreQuery(query))
    }
}