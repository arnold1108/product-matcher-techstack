package com.adventure.store.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import com.adventure.store.model.Tasks

class StoreCreator(private val context: ActorContext<Tasks>): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(): Behavior<Tasks> {
            return Behaviors.setup { context ->
                StoreCreator(context)
            }
        }
    }

    override fun createReceive(): Receive<Tasks> {
        TODO("Not yet implemented")
    }
}