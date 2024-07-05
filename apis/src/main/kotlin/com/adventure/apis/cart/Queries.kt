package com.adventure.apis.cart

import java.util.UUID

class Queries {
    data class ViewCart(
        val shopperId: UUID
    )

    data class FetchCartProductDetails(
        val productId: UUID
    )
}