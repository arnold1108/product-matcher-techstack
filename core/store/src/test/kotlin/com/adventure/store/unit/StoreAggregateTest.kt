package com.adventure.store.unit

import com.adventure.apis.store.Commands.*
import com.adventure.apis.store.Events.*
import com.adventure.apis.store.State.StoreStatus.*
import com.adventure.apis.store.State.StoreCategory.*
import com.adventure.store.command.StoreAggregate
import org.axonframework.test.aggregate.AggregateTestFixture
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.util.*

class StoreAggregateTest {

    private lateinit var aggregateTestFixture: AggregateTestFixture<StoreAggregate>

    @BeforeEach
    fun setUp() {
        aggregateTestFixture = AggregateTestFixture(StoreAggregate::class.java)
    }

    @Test
    fun `test CreateStore command`() {
         aggregateTestFixture
             .givenNoPriorActivity()
             .`when`(createStoreCommand)
             .expectSuccessfulHandlerExecution()
             .expectEvents(storeCreatedEvent)
             .expectState{it.storeStatus == CREATED }
    }

    @Test
    fun `test AddStock command`() {
        aggregateTestFixture
            .given(storeCreatedEvent)
            .`when`(addStockCommand)
            .expectSuccessfulHandlerExecution()
            .expectEvents(stockAddedEvent)
            .expectState{it.storeStatus == OPEN }
    }

    @Test
    fun `test CloseStore command`() {
        aggregateTestFixture
            .given(storeCreatedEvent)
            .andGiven(stockAddedEvent)
            .`when`(closeStoreCommand)
            .expectSuccessfulHandlerExecution()
            .expectEvents(storeClosedEvent)
            .expectState{it.storeStatus == CLOSED }
    }

    @Test
    fun `test ReOpenStore command`() {
        aggregateTestFixture
            .given(storeCreatedEvent)
            .andGiven(storeClosedEvent)
            .`when`(reOpenStoreCommand)
            .expectSuccessfulHandlerExecution()
            .expectEvents(storeReopenedEvent)
            .expectState { it.storeStatus == OPEN }

    }

    companion object {
        private val createStoreCommand =  CreateStore(
            storeId = UUID.randomUUID(),
            sellerId = UUID.randomUUID(),
            category = COMPUTING,
            storeName = "AlphaTech"
        )
        private val storeCreatedEvent = StoreCreated(
            storeId = createStoreCommand.storeId,
            sellerId = createStoreCommand.sellerId,
            category = createStoreCommand.category,
            storeName = createStoreCommand.storeName
        )
        private val addStockCommand = AddStock(
            storeId = createStoreCommand.storeId,
            sellerId = createStoreCommand.sellerId,
            productId = UUID.randomUUID(),
            productName = "Mouse",
            productDescription = "Logitech mechanically charged",
            productCategory = "Electronics",
            price = 6500.00,
            remainingQuantity = 6,
            timeAdded = LocalDateTime.now()
        )
        private val stockAddedEvent = StockAdded(
            storeId = addStockCommand.storeId,
            sellerId = addStockCommand.sellerId,
            productId = addStockCommand.productId,
            productName = addStockCommand.productName,
            productDescription = addStockCommand.productDescription,
            productCategory = addStockCommand.productCategory,
            price = addStockCommand.price,
            remainingQuantity = addStockCommand.remainingQuantity,
            timeAdded = addStockCommand.timeAdded
        )
        private val closeStoreCommand = CloseStore(storeId = storeCreatedEvent.storeId)
        private val storeClosedEvent = StoreClosed(storeId = closeStoreCommand.storeId)
        private val reOpenStoreCommand = ReOpenStore(storeId = storeCreatedEvent.storeId)
        private val storeReopenedEvent = StoreReOpened(storeId = storeClosedEvent.storeId)
    }
}