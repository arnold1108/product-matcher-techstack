package com.adventure.accounts.actors

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import com.adventure.accounts.model.Messages
import com.adventure.accounts.model.Messages.*
import com.adventure.accounts.model.Tasks.*
import com.adventure.accounts.service.AccountCreation
import org.axonframework.extensions.reactor.eventhandling.gateway.ReactorEventGateway
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.UUID

@Service
class Guardian(
    private val event: ReactorEventGateway,
    private val accountCreator: AccountCreation
) {
   val system: ActorSystem<Messages> = ActorSystem.create(
       Behaviors.setup{ context ->
           val receptionist = context.spawn(
               Receptionist.create(accountCreator), "Receptionist"
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

                   is BuyerAddedValidation -> {
                       message.validation.doOnSuccess {result ->
                           event.publish(result)
                               .then()
                               .thenReturn(result)
                       }.subscribe()
                   }
                   is SellerAddedValidation -> {
                       message.validation.doOnSuccess {result ->
                           event.publish(result)
                               .then()
                               .thenReturn(result)
                       }.subscribe()
                   }
               }
               Behaviors.same()
           }
       }, "AccountsSystem"
   )
}