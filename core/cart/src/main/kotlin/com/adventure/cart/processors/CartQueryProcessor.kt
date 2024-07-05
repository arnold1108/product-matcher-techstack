package com.adventure.cart.processors

import com.adventure.apis.cart.Queries.FetchCartProductDetails
import com.adventure.apis.cart.Queries.ViewCart
import com.adventure.apis.cart.QueryResults
import com.adventure.apis.cart.QueryResults.*
import com.adventure.cart.entity.CartEntity
import com.adventure.cart.entity.CartItemRepository
import com.adventure.cart.entity.CartItems
import com.adventure.cart.entity.CartRepository
import org.axonframework.queryhandling.QueryExecutionException
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class CartQueryProcessor(
    private val cartRepository: CartRepository,
    private val cartItemRepository: CartItemRepository
) {

    @QueryHandler
    fun handleQuery(query: ViewCart): ViewCartQueryResult {
        val cartEntity = cartRepository.fetchByShopperId<CartEntity>(query.shopperId)
            .orElseThrow {
                QueryExecutionException("Cart not found for shopperId: ${query.shopperId}", NoSuchElementException())
            }
        val cartItems = cartEntity.cartItems.map {
            CartItem(
                productId = it.itemId,
                productName = it.productName,
                quantity = it.quantity,
                price = it.unitPrice
            )
        }
        return ViewCartQueryResult(
            buyerId = cartEntity.shopperId,
            cartItems = cartItems,
            totalPrice = cartEntity.totalPrice,
            totalItems = cartEntity.totalItems

        )
    }

    @QueryHandler
    fun handleQuery(query: FetchCartProductDetails): CartProductDetails {
        val product = cartItemRepository.findById(query.productId)
            .orElseThrow {
                QueryExecutionException("No product identified as :: ${query.productId}", NoSuchElementException())
            }
        return CartProductDetails(
            productName = product.productName,
            quantity = product.quantity,
            productPrice = product.unitPrice
        )
    }
}