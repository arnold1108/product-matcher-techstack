package com.adventure.apis.cart

import java.util.UUID

class Queries {
    data class ViewCart(
        val buyerId: UUID
    )

    data class FetchCartProductDetails(
        val productId: UUID
    )
}