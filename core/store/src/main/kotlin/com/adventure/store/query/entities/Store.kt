package com.adventure.store.query.entities

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("stores")
data class Store(
    @PrimaryKey
    val storeId: UUID,
    val sellerId: UUID,
    val category: String,
    val storeName: String
)
