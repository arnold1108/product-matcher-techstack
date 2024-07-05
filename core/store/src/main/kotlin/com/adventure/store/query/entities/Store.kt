package com.adventure.store.query.entities

import com.adventure.apis.store.State.StoreCategory
import com.adventure.apis.store.State.StoreStatus
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.CassandraType.Name
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("stores")
data class Store(
    @PrimaryKey
    val storeId: UUID,
    val sellerId: UUID,
    @CassandraType(type = Name.TEXT)
    val category: StoreCategory,
    val storeName: String,
    @CassandraType(type = Name.TEXT)
    val storeStatus: StoreStatus
)
