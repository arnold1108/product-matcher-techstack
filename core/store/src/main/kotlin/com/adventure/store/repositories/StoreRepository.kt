package com.adventure.store.repositories

import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import com.adventure.store.entities.Store
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import reactor.core.publisher.Mono
import java.util.UUID

interface StoreRepository: CrudRepository<Store, UUID> {
    @Query("INSERT INTO stores (store_id, category, store_name, products) VALUES (?0, ?1, ?3, ?4)")
    fun createStore(
        storeId: UUID,
        sellerId: UUID,
        category: String,
        storeName: String
    )

    @Query("SELECT s.store_id, s.seller_id, s.store_name, p.product_id, p.product_name, p.remaining_quantity, p.likes, p.price, p.time_added FROM stores s JOIN products p ON s.store_id = p.store_id WHERE s.seller_id = ?0")
    fun manageStore(sellerId: UUID): Mono<ManageStoreQueryResults>
}