package com.adventure.apis.store

import com.adventure.apis.store.State.StoreCategory
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

class Requests {
    data class CreateStoreRequest(
        @JsonProperty("store_name")
        val storeName: String,
        @JsonProperty("category")
        val category: StoreCategory
    )

    data class AddStockRequest(
        @JsonProperty("product_name")
        val productName: String,
        @JsonProperty("product_category")
        val productCategory: String,
        @JsonProperty("product_description")
        val productDescription: String,
        @JsonProperty("quantity")
        val quantity: Int,
        @JsonProperty("price")
        val price: Double,
        @JsonProperty("time_added")
        val timeAdded: LocalDateTime

    )
}