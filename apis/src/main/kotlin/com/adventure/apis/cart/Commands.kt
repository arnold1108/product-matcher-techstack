package com.adventure.apis.cart

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.UUID

class Commands {
    data class AddProductToCart(
        @TargetAggregateIdentifier
        val shopperId: UUID,
        val productId: UUID,
        val productName: String,
        val quantity: Int,
        val unitPrice: Double
    )
    data class RemoveProductFromCart(
        @TargetAggregateIdentifier
        val shopperId: UUID,
        val productId: UUID,
        val quantity: Int,
        val unitPrice: Double
    )
    data class Checkout(
        @TargetAggregateIdentifier
        val shopperId: UUID
    )
}