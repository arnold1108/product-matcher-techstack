package com.adventure.accounts.actors

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.*
import com.adventure.accounts.model.Messages
import com.adventure.accounts.model.Tasks
import com.adventure.accounts.model.Tasks.AddBuyer
import com.adventure.accounts.model.Tasks.AddSeller
import com.adventure.accounts.respository.Repo
import reactor.core.publisher.Mono

class Receptionist(
    private val context: ActorContext<Tasks>,
    private val repo: Repo
): AbstractBehavior<Tasks>(context) {
    companion object {
        fun create(): Behavior<Tasks> {
            return Behaviors.setup { context ->
                Receptionist(context)
            }
        }
    }
    override fun createReceive(): Receive<Tasks> {
        return newReceiveBuilder()
            .onMessage(AddBuyer::class.java) { task ->
                repo.addBuyer()
                    .subscribe { validation ->
                        task.replyTo.tell(Messages.BuyerAddedValidation(
                            task.messageId,
                            Mono.just(validation)
                        ))
                    }
                Behaviors.same()
            }
            .onMessage(AddSeller::class.java) {task ->
                repo.addSeller()
                    .subscribe { validation ->
                        task.replyTo.tell(Messages.SellerAddedValidation(
                            task.messageId,
                            Mono.just(validation)
                        ))
                    }
                Behaviors.same()
            }
            .build()
    }

}