package com.adventure.gateway.utils

object Mappings {
    private const val REQUEST_MAPPING = "/soko"

    const val ACCOUNT_CREATION_MAPPING = "$REQUEST_MAPPING/accounts/create"

    const val CREATE_STORE_MAPPING = "$REQUEST_MAPPING/{seller_id}/stores/create"
    const val ADD_STOCK_MAPPING = "$REQUEST_MAPPING/{seller_id}/stores/{store_id}/stock/add"
    const val MANAGE_STORE_MAPPING = "$REQUEST_MAPPING/stores/{store_id}/manage"

    const val EXPLORE_PRODUCT_MAPPING = "$REQUEST_MAPPING/{buyer_id}/products"
    const val LIKE_PRODUCT_MAPPING = "$REQUEST_MAPPING/{buyer_id}/products/{product_id}/like"
    const val ADD_PRODUCT_TO_CART_MAPPING = "$REQUEST_MAPPING/{buyer_id}/carts/add/{product_id}"

    const val VIEW_CART_MAPPING = "$REQUEST_MAPPING/{buyer_id}/carts"
    const val REMOVE_PRODUCT_FROM_CART_MAPPING = "$REQUEST_MAPPING/{buyer_id}/carts/remove/{product_id}"
    const val CHECKOUT_MAPPING = "$REQUEST_MAPPING/{buyer_id}/carts/checkout"

}