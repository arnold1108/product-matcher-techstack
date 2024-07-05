package com.adventure.cart.processors

import com.adventure.apis.cart.Queries.FetchCartProductDetails
import com.adventure.apis.cart.Queries.ViewCart
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class CartQueryProcessor {

    @QueryHandler
    fun handleQuery(query: ViewCart) {
        TODO()
    }

    @QueryHandler
    fun handleQuery(query: FetchCartProductDetails) {
        TODO()
    }
}