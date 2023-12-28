package com.adventure.accounts.service
import com.adventure.accounts.actors.Guardian
import com.adventure.accounts.model.Messages.AddBuyerCommand
import com.adventure.accounts.model.Messages.AddSellerCommand
import com.adventure.apis.accounts.Commands.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import reactor.core.publisher.Mono
import java.util.*

@Service
class CommandIngest(
    private val guardian: Guardian
) {
    @CommandHandler
    fun handle(command: CreateSellerAccount){
        guardian.system.tell(AddSellerCommand(command))
    }

    @CommandHandler
    fun handle(command: CreateBuyerAccount) {
        guardian.system.tell(AddBuyerCommand(command))
    }
}

@Controller
class Controller(private val command: ReactorCommandGateway){
    @PostMapping("/seller/account/create")
    fun createSellerAccount(@RequestBody details: UserDetails): Mono<ResponseEntity<String>> {
        val createSellerAccountCommand = CreateSellerAccount(
            sellerId = UUID.randomUUID(),
            details
        )
        return command.send<ResponseEntity<String>>(createSellerAccountCommand)
            .then()
            .thenReturn(ResponseEntity.ok("Welcome to Soko!"))
    }
}