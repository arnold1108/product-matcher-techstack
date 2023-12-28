package com.adventure.store.entities

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("stores")
data class Store(
    @PrimaryKey @Column("store_id")val storeId: UUID,
    @Column("seller_id") val sellerId: UUID,
    @Column("category") val category: String,
    @Column("store_name") val storeName: String,
    @Column("products") val products: Set<UUID> = emptySet()
)
