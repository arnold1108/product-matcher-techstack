package com.adventure.gateway.utils

object Mappings {
    const val ACCOUNT_CREATION_MAPPING = "/accounts/completeSignup"

    const val CREATE_STORE_MAPPING = "/stores/create"
    const val ADD_STOCK_MAPPING = "/stores/stock/add"
    const val MANAGE_STORE_MAPPING = "/stores/manage"
    const val CLOSE_STORE_MAPPING = "/stores/close"
    const val RE_OPEN_STORE_MAPPING = "/stores/reOpen"

    const val EXPLORE_PRODUCT_MAPPING = "/products"
    const val LIKE_PRODUCT_MAPPING = "/products/like"
    const val ADD_PRODUCT_TO_CART_MAPPING = "/carts/add"

    const val VIEW_CART_MAPPING = "/carts"
    const val REMOVE_PRODUCT_FROM_CART_MAPPING = "/carts/remove"
    const val CHECKOUT_MAPPING = "/carts/checkout"

}

object Authorizations {
    const val BUYER_ROLE = "hasRole('BUYER')"
    const val SELLER_ROLE = "hasRole('SELLER')"
    const val STORE_BELONGS_TO_THE_AUTHENTICATED_USER = "returnObject.sellerId == principal.extractPrincipalId()"
    const val SHOPPER_IS_THE_AUTHENTICATED_USER = "#shopperId == principal.extractPrincipalId()"
    const val CART_BELONGS_TO_THE_AUTHENTICATED_USER = "returnObject.shopperId == principal.extractPrincipalId()"
}