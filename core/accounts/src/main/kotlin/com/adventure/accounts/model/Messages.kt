package com.adventure.accounts.model

import com.adventure.apis.accounts.Commands.CreateBuyerAccount
import com.adventure.apis.accounts.Commands.CreateSellerAccount
import reactor.core.publisher.Mono
import java.util.*

sealed class Messages {
    data class AddBuyerCommand(
        val command: CreateBuyerAccount
    ): Messages()
    data class AddSellerCommand(
        val command: CreateSellerAccount
    ): Messages()
    data class BuyerAddedValidation(
        val messageId: UUID,
        val validation: Mono<String>
    ): Messages()
    data class SellerAddedValidation(
        val messageId: UUID,
        val validation: Mono<String>
    ): Messages()
}