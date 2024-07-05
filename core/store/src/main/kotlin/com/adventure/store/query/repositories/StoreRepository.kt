package com.adventure.store.query.repositories

import com.adventure.apis.store.Commands.CreateStore
import com.adventure.apis.store.QueryResults.ManageStoreQueryResults
import com.adventure.store.query.entities.Store
import org.slf4j.LoggerFactory
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
interface StoreRepository: ReactiveCassandraRepository<Store, UUID> {
    companion object {
        private val logger = LoggerFactory.getLogger(StoreRepository::class.java)
    }
    @Query("INSERT INTO stores (storeid, sellerid, category, storename) VALUES (:#{#details.storeId}, :#{#details.sellerId}, :#{#details.category}, :#{#details.storeName})")
    fun createStore(@Param("details") details: CreateStore): Mono<Void> {
        return Mono.empty()
    }

    @Query("SELECT s.store_id, s.seller_id, s.store_name, p.product_id, p.product_name, p.remaining_quantity, p.likes, p.price, p.time_added FROM stores s JOIN products p ON s.store_id = p.store_id WHERE s.seller_id = ?0")
    fun manageStore(sellerId: UUID, response: ManageStoreQueryResults): Mono<ManageStoreQueryResults>



}