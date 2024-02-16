package com.adventure.store.actors

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import com.adventure.store.model.Messages
import com.adventure.store.model.Messages.*
import com.adventure.store.model.Tasks.AddProduct
import com.adventure.store.model.Tasks.AddStore
import com.adventure.store.service.ProductService
import com.adventure.store.service.StoreService
import org.axonframework.extensions.reactor.eventhandling.gateway.ReactorEventGateway
import org.springframework.stereotype.Service
import reactor.core.scheduler.Schedulers
import java.util.*

@Service
class Guardian(
    private val event: ReactorEventGateway,
    storeService: StoreService,
    productService: ProductService
) {

    val actorSystem: ActorSystem<Messages> = ActorSystem.create(
        Behaviors.setup { context ->
            val stocker =
                context.spawn(Stocker.create(productService), "Stocker")
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

                    is StoreAddedFeedback -> {
                        message.feedback.doOnSuccess { feedback ->
                            event.publish(feedback)
                                .then()
                                .thenReturn(feedback)
                        }
                            .subscribeOn(Schedulers.immediate())
                    }
                    is ProductAddedFeedback -> {
                        message.feedback.doOnSuccess { feedback ->
                            event.publish(feedback)
                                .then()
                                .thenReturn(feedback)
                        }
                            .subscribeOn(Schedulers.immediate())
                    }
                    is ManageStore -> {}
                }
                Behaviors.same()
            }
        }, "StoreSystem"
    )
}