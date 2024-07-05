package com.adventure.apis.cart

import java.util.*

class QueryResults {
    data class ViewCartQueryResult(
        val buyerId: UUID,
        val cartItems: List<CartItem>,
        val totalItems: Int,
        val totalPrice: Double
    )
    data class CartItem(
        val productId: UUID,
        val productName: String,
        val quantity: Int,
        val price: Double
    )

    data class CartProductDetails(
        val productName: String,
        val quantity: Int,
        val productPrice: Double
    )
}