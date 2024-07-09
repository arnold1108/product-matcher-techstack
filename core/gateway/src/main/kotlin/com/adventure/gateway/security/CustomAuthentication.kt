package com.adventure.gateway.security

//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.oauth2.jwt.Jwt
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
//import java.util.UUID
//
//class CustomAuthentication(
//    jwt: Jwt,
//    authorities: Collection<GrantedAuthority>,
//    private val userId: UUID
//): JwtAuthenticationToken(jwt, authorities) {
//
//    fun extractPrincipalId(): UUID {
//        return userId
//    }
//}