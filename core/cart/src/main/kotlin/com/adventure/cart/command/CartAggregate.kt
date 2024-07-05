package com.adventure.cart.command

import com.adventure.apis.cart.Commands.*
import com.adventure.apis.cart.Events.*
import com.adventure.apis.cart.State.CartStatus
import com.adventure.apis.cart.State.CartStatus.CREATED
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class CartAggregate() {
    @AggregateIdentifier
    private lateinit var shopperId: UUID
    private lateinit var cartStatus: CartStatus
    private var cartItems: Int = 0
    private var totalAmount: Double = 0.00

    private lateinit var eventGateway: EventGateway

    @CommandHandler
    constructor(command: AddProductToCart): this() {
        eventGateway.publish(
            ProductAddedToCart(
                shopperId = command.shopperId,
                productId = command.productId,
                productName = command.productName,
                quantity = command.quantity,
                unitPrice = command.unitPrice
            )
        )
    }

    @EventSourcingHandler
    fun handle(event: ProductAddedToCart) {
        shopperId = event.shopperId
        cartStatus = CREATED
        cartItems + 1
        val totalPrice = event.unitPrice * event.quantity
        totalAmount + totalPrice
    }

    @CommandHandler
    fun on(command: RemoveProductFromCart) {
        eventGateway.publish(
            ProductRemovedFromCart(
                shopperId = command.shopperId,
                productId = command.productId,
                quantity = command.quantity,
                unitPrice = command.unitPrice
            )
        )
    }

    @EventSourcingHandler
    fun handle(event: ProductRemovedFromCart) {
        val totalPrice = event.unitPrice * event.quantity
        cartItems - 1
        totalAmount - totalPrice
    }

    @CommandHandler
    fun on (command: Checkout) {
        if (cartStatus == CREATED) {
            eventGateway.publish(
                CheckingOutCart(
                    shopperId = command.shopperId
                )
            )
        }
    }
}