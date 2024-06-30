package com.adventure.gateway.service

import com.adventure.apis.cart.Commands.*
import com.adventure.apis.cart.Queries.ViewCart
import com.adventure.apis.cart.QueryResults.*
import com.adventure.gateway.utils.Authorizations.BUYER_IS_THE_AUTHENTICATED_USER
import com.adventure.gateway.utils.Authorizations.CART_BELONGS_TO_THE_AUTHENTICATED_USER
import com.adventure.gateway.utils.SecurityUtils.extractPrincipalDetails
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*

@Service
class CartService(
    private val query: QueryGateway,
    private val command: CommandGateway
) {
    private val authentication = SecurityContextHolder.getContext().authentication

    fun addProductToCart(productId: UUID, quantity: Int): String {
        val principal = extractPrincipalDetails(authentication = authentication)
        command.send<Void>(
            AddProductToCart(
                buyerId = principal.principalId,
                productId = productId,
                quantity = quantity
            )
        )

        return "You have added $quantity products to cart"
    }

    @PostAuthorize(CART_BELONGS_TO_THE_AUTHENTICATED_USER)
    fun fetchCartById(buyerId: UUID): ViewCartQueryResult =
        query.query(ViewCart(buyerId = buyerId), ViewCartQueryResult::class.java).get()

    fun removeProductFromCart(productId: UUID): String {
        val principal = extractPrincipalDetails(authentication = authentication)
        command.send<Void>(
            RemoveProductFromCart(
                productId = productId,
                buyerId = principal.principalId
            )
        )

        return "Product removed from Cart"
    }

    @PreAuthorize(BUYER_IS_THE_AUTHENTICATED_USER)
    fun checkout(buyerId: UUID): String {
        command.send<Void>(
            Checkout(
                buyerId = buyerId
            )
        )

        return "Enjoy!"
    }
}