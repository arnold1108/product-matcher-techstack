package com.adventure.gateway.utils

object Mappings {
    private const val SELLER_REQUEST_MAPPING = "/seller"
    const val SELLER_ACCOUNT_CREATION_MAPPING = "$SELLER_REQUEST_MAPPING/account/create"
    const val CREATE_STORE_MAPPING = "$SELLER_REQUEST_MAPPING/{sellerId}/store/create"
    const val ADD_STOCK_MAPPING = "$SELLER_REQUEST_MAPPING/store/{storeId}/stock/add"
    const val MANAGE_STORE_MAPPING = "$SELLER_REQUEST_MAPPING/store/{storeId}/manage"

    private const val BUYER_REQUEST_MAPPING = "/buyer"
    const val BUYER_ACCOUNT_CREATION_MAPPING = "$BUYER_REQUEST_MAPPING/account/create"
    const val EXPLORE_PRODUCT_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/explore"
    const val LIKE_PRODUCT_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/explore/{productId}/like"
    const val VIEW_CART_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/cart"
    const val ADD_PRODUCT_TO_CART_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/cart/add/{productId}"
    const val REMOVE_PRODUCT_FROM_CART_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/cart/remove/{productId}"
    const val CHECKOUT_MAPPING = "$BUYER_REQUEST_MAPPING/{buyerId}/cart/checkout"

}