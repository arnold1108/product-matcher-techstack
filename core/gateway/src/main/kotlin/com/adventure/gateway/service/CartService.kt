package com.adventure.gateway.service

import com.adventure.apis.cart.Commands.Checkout
import com.adventure.apis.cart.Commands.RemoveProductFromCart
import com.adventure.apis.cart.Queries.ViewCart
import com.adventure.apis.cart.QueryResults.*
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

    @PostAuthorize("returnObject.buyerId == principal.extractPrincipalId()")
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

    @PreAuthorize("buyerId == principal.extractPrincipalId()")
    fun checkout(buyerId: UUID): String {
        command.send<Void>(
            Checkout(
                buyerId = buyerId
            )
        )
        return "Enjoy!"
    }
}