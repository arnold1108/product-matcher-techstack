package com.adventure.gateway.service

import com.adventure.apis.auth.Requests
import com.adventure.apis.auth.Requests.LoginRequest
import com.adventure.apis.auth.Requests.SignupRequest
import com.adventure.gateway.security.components.CustomAuthenticationProvider
import com.adventure.gateway.security.dao.entity.Users
import com.adventure.gateway.security.dao.repository.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.security.auth.login.AccountException

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationProvider: CustomAuthenticationProvider
) {

    fun accountExists(emailAddress: String): Boolean {
        return userRepository.existsByUserName(userName = emailAddress)  // We will use email address as the username
    }

    fun createUser(requestBody: SignupRequest): Authentication {
        val userExists = accountExists(emailAddress = requestBody.emailAddress)

        if (!userExists) {
            val encodedPassword = passwordEncoder.encode(requestBody.password)
            val user = Users.newUser(
                principalId = UUID.randomUUID(),
                userName = requestBody.emailAddress,
                password = encodedPassword,
                role = requestBody.role
            )

            userRepository.save(user)
            return authenticationProvider.authenticate(
                UsernamePasswordAuthenticationToken(
                    requestBody.emailAddress,
                    requestBody.password
                )
            )

        }
        else throw AccountException("An account with the provided credentials exists")
    }

    fun login(requestBody: LoginRequest): Authentication{
        val authentication = UsernamePasswordAuthenticationToken(
            requestBody.emailAddress,
            requestBody.password
        )
        return authenticationProvider.authenticate(authentication)
    }
}