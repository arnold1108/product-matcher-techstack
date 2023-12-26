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
    private val accountCreator: AccountCreation,
    private val context: ActorContext<Tasks>
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(): Behavior<Tasks> {
            return Behaviors.setup { context ->
                Receptionist(accountCreator, context)
            }
        }
    }
    override fun createReceive(): Receive<Tasks> {
        return newReceiveBuilder()
            .onMessage(AddBuyer::class.java) { task ->
                accountCreator.addBuyer(task.command.buyerId,
                    task.command.details
                ).subscribe { validation ->
                        task.replyTo.tell(
                            BuyerAddedValidation(
                            task.messageId,
                            Mono.just(validation)
                        )
                        )
                    }
                Behaviors.same()
            }
            .onMessage(AddSeller::class.java) {task ->
                accountCreator.addSeller(task.command.sellerId,
                    task.command.details
                )
                    .subscribe { validation ->
                        task.replyTo.tell(
                            SellerAddedValidation(
                            task.messageId,
                            Mono.just(validation)
                        )
                        )
                    }
                Behaviors.same()
            }
            .build()
    }

}