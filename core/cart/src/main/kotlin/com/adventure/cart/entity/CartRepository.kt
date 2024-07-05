package com.adventure.cart.entity

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CartRepository: JpaRepository<CartEntity, UUID> {

    fun<T> fetchByShopperId(shopperId: UUID): Optional<T>
}

@Repository
interface  CartItemRepository: JpaRepository<CartItems, UUID> {

}