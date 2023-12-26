package com.adventure.accounts.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.*
import com.adventure.accounts.model.Messages.BuyerAddedValidation
import com.adventure.accounts.model.Messages.SellerAddedValidation
import com.adventure.accounts.model.Tasks
import com.adventure.accounts.model.Tasks.AddBuyer
import com.adventure.accounts.model.Tasks.AddSeller
import com.adventure.accounts.service.AccountCreation
import reactor.core.publisher.Mono

class Receptionist(
    private val context: ActorContext<Tasks>,
    private val accountCreator: AccountCreation
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(accountCreator: AccountCreation): Behavior<Tasks> {
            return Behaviors.setup { context ->
                Receptionist(context, accountCreator)
            }
        }
    }
    override fun createReceive(): Receive<Tasks> {
        return newReceiveBuilder()
            .onMessage(AddBuyer::class.java) { task ->
                accountCreator.addBuyer(task.command)
                    .doOnSuccess { validation ->
                        context.log.info(validation)
                        task.replyTo.tell(
                            BuyerAddedValidation(
                                task.messageId,
                                Mono.just(validation)
                             )
                        )
                    }
                    .doOnError { error ->
                        context.log.error("Unable to handle message: ${error.message}")
                    }
                    .subscribe()
                Behaviors.same()
            }
            .onMessage(AddSeller::class.java) {task ->
                accountCreator.addSeller(task.command)
                    .doOnSuccess { validation ->
                        context.log.info(validation)
                        task.replyTo.tell(
                            SellerAddedValidation(
                                task.messageId,
                                Mono.just(validation)
                            )
                        )
                    }
                    .doOnError {error ->
                        context.log.error("Unable to handle message: ${error.message}")
                    }
                    .subscribe()
                Behaviors.same()
            }
            .build()
    }

}