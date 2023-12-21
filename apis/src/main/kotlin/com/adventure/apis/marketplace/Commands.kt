package com.adventure.apis.marketplace

import java.util.*

class Commands {
    data class LikeProduct(
        val accountId: UUID,
        val productId: UUID
    )
}