package com.adventure.store.query.entities

import com.adventure.apis.store.State.StoreStatus
import org.springframework.data.cassandra.core.mapping.CassandraType
import org.springframework.data.cassandra.core.mapping.CassandraType.Name
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("products")
data class Product(
    @PrimaryKey
    val productId: UUID,
    val sellerId: UUID,
    val storeId: UUID,
    val productName: String,
    val productCategory: String,
    val productDescription: String,
    val price: Double,
    var remainingQuantity: Int = 1,
    var likes: Int = 0,
    val timeAdded: LocalDateTime
)