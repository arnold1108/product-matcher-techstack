package com.adventure.store.service


import com.adventure.apis.store.Commands.CreateStore
import com.adventure.store.repositories.StoreRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class StoreService(private val storeRepository: StoreRepository) {
    fun addStore(command: CreateStore): Mono<String> {
        val storeDetails = CreateStore(
            storeId = command.storeId,
            sellerId = command.sellerId,
            category = command.category,
            storeName = command.storeName
        )
        return Mono.fromCallable {
            storeRepository.createStore(storeDetails)
            "successfully added store ${storeDetails.storeId}"
        }
    }
}