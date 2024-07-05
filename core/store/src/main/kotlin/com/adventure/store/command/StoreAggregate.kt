package com.adventure.store.command

import com.adventure.apis.store.Commands.*
import com.adventure.apis.store.Events.*
import com.adventure.apis.store.State.*
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import java.util.UUID

@Aggregate
class StoreAggregate() {

    @AggregateIdentifier
    private lateinit var storeId: UUID
    private lateinit var storeStatus: StoreStatus
    private var stockValue: Double = 0.00

    private lateinit var eventGateway: EventGateway

    @CommandHandler
    constructor(command: CreateStore): this() {
        eventGateway.publish(
            StoreCreated(
                storeId = command.storeId,
                sellerId = command.sellerId,
                category = command.category,
                storeName = command.storeName
            )
        )
    }

    @EventSourcingHandler
    fun hande(event: StoreCreated) {
        storeId = event.storeId
        storeStatus = StoreStatus.CREATED
    }

    @CommandHandler
    fun on(command: AddStock) {
        if (storeStatus  == StoreStatus.CREATED) {
            eventGateway.publish(
                StockAdded(
                    storeId = command.storeId,
                    sellerId = command.sellerId,
                    productId = command.productId,
                    productName = command.productName,
                    productCategory = command.productCategory,
                    price = command.price,
                    productDescription = command.productDescription,
                    remainingQuantity = command.remainingQuantity,
                    timeAdded = command.timeAdded
                )
            )
        }
    }

    @EventSourcingHandler
    fun handle(event: StockAdded) {
        val productValue = event.price * event.remainingQuantity
        stockValue + productValue
        storeStatus = StoreStatus.OPEN
    }

    @CommandHandler
    fun on(command: CloseStore) {
        if(storeStatus == StoreStatus.OPEN) {
            eventGateway.publish(
                StoreClosed(storeId = command.storeId)
            )
        }
    }

    @EventSourcingHandler
    fun handle(event: StoreClosed) {
        storeStatus = StoreStatus.CLOSED
    }

    @CommandHandler
    fun on(command: ReOpenStore) {
        if (storeStatus == StoreStatus.CLOSED) {
            eventGateway.publish(
                StoreReOpened(
                    storeId = command.storeId
                )
            )
        }
    }

    @EventSourcingHandler
    fun handle(event: StoreReOpened) {
        storeStatus = StoreStatus.OPEN
    }
}