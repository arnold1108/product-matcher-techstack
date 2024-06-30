package com.adventure.gateway.utils

import com.adventure.apis.accounts.State
import com.adventure.apis.accounts.State.Role
import org.springframework.security.core.Authentication
import java.util.UUID

object SecurityUtils {
    fun extractPrincipalDetails(authentication: Authentication): PrincipalDetails {
        TODO()
    }
}

data class PrincipalDetails(
    val principalId: UUID,
    val emailAddress: String,
    val role: Role
)