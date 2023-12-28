package com.adventure.store.entities

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.time.LocalDateTime
import java.util.UUID

@Table("products")
data class Product(
    @PrimaryKey @Column("product_id") val productId: UUID,
    @Column("store_id") val storeId: UUID,
    @Column("product_name") val productName: String,
    @Column("product_category") val productCategory: String,
    @Column("product_description") val productDescription: String,
    @Column("price") val price: Double,
    @Column("quantity") val remainingQuantity: Int,
    @Column("likes") var likes: Int = 0,
    @Column("time_added") val timeAdded: LocalDateTime
)
