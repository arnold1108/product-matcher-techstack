package com.adventure.apis.store

import com.adventure.apis.store.Category.StoreCategory
import java.util.UUID

class Commands {
    data class CreateStore(
        val storeId: UUID,
        val sellerId: UUID,
        val category: String,
        val storeName: String,
    )
    data class AddStock(
        val storeId: UUID,
        val productId: UUID,
        val productName: String,
        val productCategory: String,
        val productDescription: String,
        val price: Double
    )
}