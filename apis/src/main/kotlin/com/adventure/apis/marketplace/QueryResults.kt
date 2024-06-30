package com.adventure.apis.marketplace

import java.util.*

class QueryResults {
    data class ExploreProductsProjection(
        val recommendedProducts: List<RecommendedProducts>
    )
    data class RecommendedProducts(
        val productId: UUID,
        val productName: String,
        val productCategory: String,
        val productDescription: String,
        val price: String
    )
}