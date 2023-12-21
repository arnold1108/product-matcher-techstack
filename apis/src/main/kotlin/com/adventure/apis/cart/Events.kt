package com.adventure.apis.cart

import com.adventure.apis.cart.QueryResults.CartItem
import java.util.*

class Events {
    data class ProductAddedToCart(
        val buyerId: UUID,
        val productId: UUID,
        val quantity: Int
        )
    data class ProductRemovedFromCart(
        val buyerId: UUID,
        val productId: UUID
    )
    data class CheckedOut(
        val buyerId: UUID,
        val cartItem: List<CartItem>,
        val totalPrice: Double
    )
}