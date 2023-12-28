package com.adventure.store.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import com.adventure.store.model.Tasks
import com.adventure.store.service.StoreService

class StoreCreator(
    context: ActorContext<Tasks>,
    storeService: StoreService
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(storeService: StoreService): Behavior<Tasks> {
            return Behaviors.setup { context ->
                StoreCreator(context, storeService)
            }
        }
    }

    override fun createReceive(): Receive<Tasks> {
        TODO("Not yet implemented")
    }
}