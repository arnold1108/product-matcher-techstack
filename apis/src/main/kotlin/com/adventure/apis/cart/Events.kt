package com.adventure.apis.cart

import com.adventure.apis.cart.QueryResults.CartItem
import java.util.*

class Events {
    data class ProductAddedToCart(
        val shopperId: UUID,
        val productId: UUID,
        val productName: String,
        val quantity: Int,
        val unitPrice: Double
        )
    data class ProductRemovedFromCart(
        val shopperId: UUID,
        val productId: UUID,
        val quantity: Int,
        val unitPrice: Double
    )
    data class CheckedOut(
        val buyerId: UUID,
        val cartItem: List<CartItem>,
        val totalPrice: Double
    )
    data class CartCheckedOut(
        val shopperId: UUID,
        val cartItems: MutableSet<UUID>
    )
}