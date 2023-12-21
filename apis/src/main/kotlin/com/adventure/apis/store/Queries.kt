package com.adventure.apis.store

import java.time.LocalDateTime
import java.util.*

class Queries {
    data class ManageStore(
        val storeId: UUID,
        val sellerId: UUID,
        val storeName: String,
        val products: List<ProductInfo>
        )
    data class ProductInfo(
        val productId: UUID,
        val productName: String,
        val remainingQuantity: Int,
        val likes: Int,
        val price: Double,
        val timeAdded: LocalDateTime
    )
}