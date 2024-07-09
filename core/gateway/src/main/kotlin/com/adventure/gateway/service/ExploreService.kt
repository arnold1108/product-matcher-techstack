package com.adventure.gateway.service

import com.adventure.apis.marketplace.QueryResults.ExploreProductsProjection
import com.adventure.apis.store.Events.ProductLiked
import com.adventure.apis.store.Queries.DoesProductExist
import org.axonframework.eventhandling.gateway.EventGateway
import org.axonframework.queryhandling.QueryGateway
import org.springframework.stereotype.Service
//import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import kotlin.NoSuchElementException

@Service
class ExploreService(
    private val eventGateway: EventGateway,
    private val queryGateway: QueryGateway
) {
//    private val authentication = SecurityContextHolder.getContext().authentication

    fun getRecommendedProducts(buyerId: UUID): ExploreProductsProjection {
        TODO("Recommender engine needed")
    }

    fun likeProduct(productId: UUID): String {
//        val productExist = queryGateway.query(
//            DoesProductExist(productId = productId), Boolean::class.java
//        ).get()
//
//        if (productExist) {
//
//            eventGateway.publish(
//                ProductLiked(productId = productId)
//            )
//
//            return "Product Liked!"
//        } else {
//            throw NoSuchElementException("Product Out of Stock")
//        }
        return "Product $productId Liked!"

    }
}