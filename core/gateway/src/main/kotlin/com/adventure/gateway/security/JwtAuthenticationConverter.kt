package com.adventure.gateway.security

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class JwtAuthenticationConverter: Converter<Jwt, CustomAuthentication> {
    override fun convert(source: Jwt): CustomAuthentication {
        val authorities = source
            .getClaimAsStringList("authorities")?.map {
                GrantedAuthority {it}
            }?: emptyList()
        val userId = source.claims["userId"] as UUID
        return CustomAuthentication(
            jwt = source,
            authorities = authorities,
            userId = userId
        )
    }
}