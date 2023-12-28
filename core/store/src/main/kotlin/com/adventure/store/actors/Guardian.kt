package com.adventure.store.actors

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import com.adventure.apis.store.Commands
import com.adventure.apis.store.Commands.AddStock
import com.adventure.store.model.Messages
import com.adventure.store.model.Messages.*
import com.adventure.store.model.Tasks
import com.adventure.store.model.Tasks.AddProduct
import com.adventure.store.model.Tasks.AddStore
import com.adventure.store.service.StoreService
import org.axonframework.extensions.reactor.eventhandling.gateway.ReactorEventGateway
import org.springframework.stereotype.Service
import java.util.*

@Service
class Guardian(
    private val event: ReactorEventGateway,
    storeService: StoreService
) {
    val actorSystem: ActorSystem<Messages> = ActorSystem.create(
        Behaviors.setup { context ->
            val stocker =
                context.spawn(Stocker.create(), "Stocker")
            val storeCreator = context.spawn(StoreCreator.create(storeService), "StoreCreator")
            Behaviors.receiveMessage<Messages> {message ->
                when(message) {
                    is AddStoreCommand -> {
                        storeCreator.tell(
                            AddStore(
                                messageId = UUID.randomUUID(),
                                command = message.command,
                                replyTo = context.self
                            )
                        )
                    }

                    is AddProductCommand -> {
                        stocker.tell(
                            AddProduct(
                                messageId = UUID.randomUUID(),
                                command = message.command,
                                replyTo = context.self
                            )
                        )
                    }

                    is ManageStoreQuery -> TODO()
                }
                Behaviors.same()
            }
        }, "StoreSystem"
    )
}