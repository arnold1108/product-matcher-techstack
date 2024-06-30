package com.adventure.apis.accounts

import java.util.*

class Queries {
    
    data class DoesAccountExist(
        val accountId: UUID
    )
}