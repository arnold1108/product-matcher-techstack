package com.adventure.authorization_server.service

import com.adventure.authorization_server.api.RecoverAccountRequest
import com.adventure.authorization_server.api.SignupRequest
import com.adventure.authorization_server.api.UpdatePasswordRequest
import com.adventure.authorization_server.components.CustomAuthenticationProvider
import com.adventure.authorization_server.dao.UserRepository
import com.adventure.authorization_server.dao.entity.Users
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import javax.security.auth.login.AccountException
import javax.security.auth.login.AccountNotFoundException
import kotlin.jvm.optionals.getOrElse

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationProvider: CustomAuthenticationProvider
) {

    fun createUser(requestBody: SignupRequest): UsernamePasswordAuthenticationToken {
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
            return UsernamePasswordAuthenticationToken(
                requestBody.emailAddress,
                requestBody.password
            )
        } else throw AccountException("An account with the provided credentials exists")
    }

    fun resetPassword(requestBody: RecoverAccountRequest): String {
        val user = userRepository
            .findByUserName(username = requestBody.emailAddress)
            .getOrElse { throw AccountNotFoundException("User does not exist") }

        user.updatePassword(newPassword = passwordEncoder.encode(requestBody.newPassword))
        userRepository.save(user)

        return "Password Updated successfully"
    }

    fun updatePassword(requestBody: UpdatePasswordRequest): String {
        val user = userRepository
            .findByUserName(username = requestBody.emailAddress)
            .getOrElse { throw AccountNotFoundException("User does not exist") }

        if (passwordEncoder.matches(requestBody.oldPassword, user.password)) {
            user.updatePassword(requestBody.newPassword)
            userRepository.save(user)
        } else {
            throw BadCredentialsException("Passwords do not match")
        }

        return "Password Updated successfully"
    }

    private fun accountExists(emailAddress: String): Boolean {
        return userRepository.existsByUserName(userName = emailAddress)  // We will use email address as the username
    }

}

