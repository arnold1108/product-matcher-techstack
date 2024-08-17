package com.adventure.gateway.service

import com.adventure.apis.cart.Commands.*
import com.adventure.apis.cart.Queries.FetchCartProductDetails
import com.adventure.apis.cart.Queries.ViewCart
import com.adventure.apis.cart.QueryResults.*
import com.adventure.apis.store.Queries.*
import com.adventure.gateway.utils.Authorizations.SHOPPER_IS_THE_AUTHENTICATED_USER
import com.adventure.gateway.utils.Authorizations.CART_BELONGS_TO_THE_AUTHENTICATED_USER
//import com.adventure.gateway.utils.SecurityUtils.extractPrincipalDetails
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.queryhandling.QueryGateway
//import org.springframework.security.access.prepost.PostAuthorize
//import org.springframework.security.access.prepost.PreAuthorize
//import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.util.*
import kotlin.NoSuchElementException

@Service
class CartService(
    private val query: QueryGateway,
    private val command: CommandGateway
) {
//    private val authentication = SecurityContextHolder.getContext().authentication

    fun addProductToCart(productId: UUID, quantity: Int): String {
//        val principal = extractPrincipalDetails(authentication = authentication)
//
//        val productExist = query.query(
//            DoesProductExist(productId = productId), Boolean::class.java
//        ).get()
//
//        if (!productExist) {
//            throw NoSuchElementException("Product out of stock!")
//        } else {
//
//            val productDetails = query.query(
//                FetchProductDetails(productId = productId), CartItem::class.java
//            ).get()
//
//            command.send<Void>(
//                AddProductToCart(
//                    shopperId = principal.principalId,
//                    productId = productId,
//                    productName = productDetails.productName,
//                    quantity = quantity,
//                    unitPrice = productDetails.price
//                )
//            )
//
//            return "You have added $quantity products to cart"
//        }
        return "You have added $quantity products to cart"
    }

//    @PostAuthorize(CART_BELONGS_TO_THE_AUTHENTICATED_USER)
    fun fetchCartById(buyerId: UUID): ViewCartQueryResult =
        query.query(ViewCart(shopperId = buyerId), ViewCartQueryResult::class.java)
        .get()

    fun removeProductFromCart(productId: UUID): String {
//        val principal = extractPrincipalDetails(authentication = authentication)

//        val cartProduct = query
//            .query(FetchCartProductDetails(productId = productId), CartProductDetails::class.java)
//            .get()
//        command.send<Void>(
//            RemoveProductFromCart(
//                productId = productId,
//                shopperId = principal.principalId,
//                quantity = cartProduct.quantity,
//                unitPrice = cartProduct.productPrice
//            )
//        )

        return "Product $productId removed from Cart"
    }

//    @PreAuthorize(SHOPPER_IS_THE_AUTHENTICATED_USER)
    fun checkout(shopperId: UUID): String {
//        command.send<Void>(
//            Checkout(
//                shopperId = shopperId
//            )
//        )

        return "Enjoy!"
    }
}