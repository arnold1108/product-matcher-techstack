package com.adventure.apis.cart

import java.util.UUID

class Commands {
    data class AddProductToCart(
        val buyerId: UUID,
        val productId: UUID,
        val quantity: Int
    )
    data class RemoveProductFromCart(
        val buyerId: UUID,
        val productId: UUID
    )
    data class Checkout(
        val buyerId: UUID
    )
}