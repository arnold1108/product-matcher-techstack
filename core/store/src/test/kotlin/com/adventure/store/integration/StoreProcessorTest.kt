package com.adventure.store.integration

import com.adventure.apis.store.Events
import com.adventure.apis.store.Queries.DoesStoreExist
import com.adventure.apis.store.State.StoreCategory.COMPUTING
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.queryhandling.QueryGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import java.util.*
import kotlin.test.assertTrue

@SpringBootTest
@ContextConfiguration(initializers = [TestContainerInitializer::class])
@ActiveProfiles("test")
class StoreProcessorTest {

    @Autowired
    private lateinit var eventGateway: EventGateway

    @Autowired
    private lateinit var queryGateway: QueryGateway

    @Test
    fun `test StoreCreated eventHandler`() {
        eventGateway.publish(storeCreatedEvent)
        Thread.sleep(500)
        assertTrue {
            queryGateway.equals(
                DoesStoreExist(storeId = storeCreatedEvent.storeId)
            )
        }
    }

    companion object {
        private val storeCreatedEvent = Events.StoreCreated(
            storeId = UUID.randomUUID(),
            sellerId = UUID.randomUUID(),
            category = COMPUTING,
            storeName = "AlphaTech"
        )
    }

}