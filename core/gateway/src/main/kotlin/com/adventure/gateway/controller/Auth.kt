package com.adventure.gateway.controller

import com.adventure.apis.auth.Requests
import com.adventure.apis.auth.Requests.SignupRequest
import com.adventure.gateway.service.AuthService
import com.adventure.gateway.utils.Mappings.LOGIN_MAPPING
import com.adventure.gateway.utils.Mappings.LOGOUT_MAPPING
import com.adventure.gateway.utils.Mappings.SIGNUP_MAPPING
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class Auth(private val auth: AuthService) {

    @PostMapping(SIGNUP_MAPPING)
    fun signUp(@RequestBody request: SignupRequest) {
        ResponseEntity.ok(auth.createUser(requestBody = request))
    }

    @PostMapping(LOGIN_MAPPING)
    fun login(@RequestBody request: Requests.LoginRequest) {
        ResponseEntity.ok(auth.login(requestBody = request))
    }

    @PostMapping(LOGOUT_MAPPING)
    fun logout() {
        ResponseEntity.ok(auth.logout())
    }
}
