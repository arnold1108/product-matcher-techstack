package com.adventure.apis.store

import com.adventure.apis.store.State.StoreCategory
import java.time.LocalDateTime
import java.util.*

class Events {
    data class StoreCreated(
        val storeId: UUID,
        val sellerId: UUID,
        val category: StoreCategory,
        val storeName: String,
    )

    data class StockAdded(
        val storeId: UUID,
        val sellerId: UUID,
        val productId: UUID,
        val productName: String,
        val productCategory: String,
        val productDescription: String,
        val price: Double,
        val remainingQuantity: Int,
        val timeAdded: LocalDateTime
    )
    data class ProductLiked(val productId: UUID)
    data class StoreClosed(val storeId: UUID)
    data class StoreReOpened(val storeId: UUID)
}