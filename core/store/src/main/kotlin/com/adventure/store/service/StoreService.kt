package com.adventure.store.service


import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.QueryResults
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import com.adventure.store.repositories.StoreRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.*

@Service
class StoreService(private val storeRepository: StoreRepository) {
    private val logger = LoggerFactory.getLogger(StoreService::class.java)
    fun addStore(command: CreateStore): Mono<String> {
        val storeDetails = CreateStore(
            storeId = command.storeId,
            sellerId = command.sellerId,
            category = command.category,
            storeName = command.storeName
        )
        return storeRepository.createStore(storeDetails)
            .then()
            .thenReturn("Successfully added store ${storeDetails.storeName}")
            .log("Added store ${storeDetails.storeName}")

    }

    fun getStore(sellerId: UUID): Mono<ManageStoreQueryResults> {
        
        return storeRepository.manageStore(sellerId)
    }

}