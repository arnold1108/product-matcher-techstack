package com.adventure.store.repositories

import com.adventure.store.entities.Store
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface StoreRepository: CrudRepository<Store, UUID> {
    @Query("INSERT INTO stores (store_id, category, store_name, products) VALUES (?0, ?1, ?3, ?4)")
    fun createStore(storeId: UUID, sellerId: UUID, category: String, storeName: String)

    @Query("SELECT * FROM stores WHERE seller_id = ?0")
    fun manageStore(sellerId: UUID)
}