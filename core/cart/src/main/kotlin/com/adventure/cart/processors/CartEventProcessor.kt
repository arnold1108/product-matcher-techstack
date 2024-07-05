package com.adventure.cart.processors

import com.adventure.apis.cart.Events.*
import com.adventure.cart.entity.CartEntity
import com.adventure.cart.entity.CartItemRepository
import com.adventure.cart.entity.CartRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse


@Service
class CartEventProcessor(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository
) {

    @EventHandler
    fun handle(event: ProductAddedToCart) {
        val cartEntity = CartEntity.addItem(
            shopperId = event.shopperId,
            productId = event.productId,
            productName = event.productName,
            quantity = event.quantity,
            itemPrice = event.unitPrice
        )

        cartRepository.save(cartEntity)
    }

    @EventHandler
    fun handle(event: ProductRemovedFromCart) {
        val cartEntity = cartRepository.findById(event.shopperId)
            .getOrElse { throw NoSuchElementException("Cart Not Found") }

        val cartItem = cartItemRepository.findById(event.productId)
            .getOrElse { throw NoSuchElementException("Cart Not Found") }

        cartEntity.removeItem(
            cartItem = cartItem,
            quantity = event.quantity,
            unitPrice = event.unitPrice
        )
        cartRepository.save(cartEntity)
    }

    @EventHandler
    fun handle(event: CartCheckedOut) {
        val cart = cartRepository.findById(event.shopperId)
            .getOrElse { throw NoSuchElementException("Cart Not Found") }

        cartRepository.delete(cart)
    }
}


