package com.adventure.apis.marketplace

import java.util.*

class Commands {
    data class LikeProduct(
        val buyerId: UUID,
        val productId: UUID
    )
}