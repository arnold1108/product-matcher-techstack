package com.adventure.accounts.actors

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import com.adventure.accounts.model.Messages
import com.adventure.accounts.model.Messages.*
import com.adventure.accounts.model.Tasks.*
import org.axonframework.extensions.reactor.eventhandling.gateway.ReactorEventGateway
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class Guardian(
    private val event: ReactorEventGateway
) {
   val system: ActorSystem<Messages> = ActorSystem.create(
       Behaviors.setup{ context ->
           val receptionist = context.spawn(
               Receptionist.create(), "Receptionist"
           )
           Behaviors.receiveMessage<Messages> { message ->
               when(message) {
                   is AddBuyerCommand -> {
                       val addBuyer = AddBuyer (
                           messageId = UUID.randomUUID(),
                           message.command,
                           context.self
                       )
                       receptionist.tell(addBuyer)
                   }
                  is AddSellerCommand -> {
                      val addSeller = AddSeller(
                          messageId = UUID.randomUUID(),
                          message.command,
                          context.self
                      )
                      receptionist.tell(addSeller)
                  }

                   is BuyerAddedValidtion -> {
                       message.validation.subscribe {
                           event.publish(message.validation)
                       }
                   }
                   is SellerAddedValidtion -> {
                       message.validation.subscribe {
                           event.publish(message.validation)
                       }
                   }
               }
               Behaviors.same()
           }
       }, "AccountsSystem"
   )
}