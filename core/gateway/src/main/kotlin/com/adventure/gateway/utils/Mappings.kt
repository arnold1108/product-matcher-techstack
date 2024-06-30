package com.adventure.gateway.utils

object Mappings {
    const val ACCOUNT_CREATION_MAPPING = "/accounts/completeSignup"

    const val CREATE_STORE_MAPPING = "/stores/create"
    const val ADD_STOCK_MAPPING = "/stores/stock/add"
    const val MANAGE_STORE_MAPPING = "/stores/manage"

    const val EXPLORE_PRODUCT_MAPPING = "$/{buyer_id}/products"
    const val LIKE_PRODUCT_MAPPING = "$/{buyer_id}/products/{product_id}/like"
    const val ADD_PRODUCT_TO_CART_MAPPING = "$/{buyer_id}/carts/add/{product_id}"

    const val VIEW_CART_MAPPING = "$/{buyer_id}/carts"
    const val REMOVE_PRODUCT_FROM_CART_MAPPING = "/{buyer_id}/carts/remove/{product_id}"
    const val CHECKOUT_MAPPING = "/{buyer_id}/carts/checkout"

}

object Roles {
    const val BUYER_ROLE = "hasRole('BUYER')"
    const val SELLER_ROLE = "hasRole('SELLER')"
}