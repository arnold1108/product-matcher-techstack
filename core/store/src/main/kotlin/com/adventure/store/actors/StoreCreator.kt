package com.adventure.store.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import com.adventure.store.model.Messages
import com.adventure.store.model.Messages.StoreAddedFeedback
import com.adventure.store.model.Tasks
import com.adventure.store.model.Tasks.AddStore
import com.adventure.store.service.StoreService
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

class StoreCreator(
    context: ActorContext<Tasks>,
    private val storeService: StoreService
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(storeService: StoreService): Behavior<Tasks> {
            return Behaviors.setup { context ->
                StoreCreator(context, storeService)
            }
        }
    }

    override fun createReceive(): Receive<Tasks> {
        return newReceiveBuilder()
            .onMessage(AddStore::class.java) {task ->
                storeService.addStore(task.command)
                    .doOnSuccess { feedback ->
                        context.log.info(feedback)
                        task.replyTo.tell(
                            StoreAddedFeedback(
                                task.messageId,
                                Mono.just(feedback)
                            )
                        )
                    }
                    .doOnError { error ->
                        context.log.error("unable to handle task: ${error.message}")
                    }
                    .subscribeOn(Schedulers.immediate())
                Behaviors.same()
            }
            .build()
    }
}