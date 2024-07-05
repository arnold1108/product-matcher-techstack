package com.adventure.store.processors

import com.adventure.apis.store.Events.*
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service

@Service
class StoreEventProcessor {

    @EventHandler
    fun handle(event: StoreCreated) {
        TODO()
    }

    @EventHandler
    fun handle(event: StockAdded) {
        TODO()
    }

    @EventHandler
    fun handle(event: StoreClosed) {
        TODO()
    }

    @EventHandler
    fun handle(event: StoreReOpened) {
        TODO()
    }

    @EventHandler
    fun handle(event: ProductLiked) {
        TODO()
    }
}