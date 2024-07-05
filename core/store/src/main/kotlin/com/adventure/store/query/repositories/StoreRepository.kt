package com.adventure.store.query.repositories

import com.adventure.store.query.entities.Product
import com.adventure.store.query.entities.Store
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StoreRepository: CassandraRepository<Store, UUID> {
}

@Repository
interface ProductRepository: CassandraRepository<Product, UUID> {
}



