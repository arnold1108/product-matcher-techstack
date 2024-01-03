package com.adventure.store.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive
import com.adventure.store.model.Messages.ProductAddedFeedback
import com.adventure.store.model.Tasks
import com.adventure.store.model.Tasks.AddProduct
import com.adventure.store.service.ProductService
import reactor.core.publisher.Mono


class Stocker(
    context: ActorContext<Tasks>,
    private val productService: ProductService
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(productService: ProductService): Behavior<Tasks> {
            return Behaviors.setup { context ->
                Stocker(context, productService)
            }
        }
    }

    override fun createReceive(): Receive<Tasks> {
        return newReceiveBuilder()
            .onMessage(AddProduct::class.java) {task ->
                productService.addProduct(task.command)
                    .doOnSuccess { feedback ->
                        task.replyTo.tell(
                            ProductAddedFeedback(
                                task.messageId,
                                Mono.just(feedback)
                            )
                        )
                    }
                    .doOnError { error ->
                        context.log.error("Unable to handle task: ${error.message}")
                    }
                    .subscribe()
                Behaviors.same()
            }
            .build()
    }
}