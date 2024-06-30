package com.adventure.gateway.service

import com.adventure.apis.marketplace.Commands.LikeProduct
import com.adventure.apis.marketplace.QueryResults.ExploreProductsProjection
import com.adventure.gateway.utils.SecurityUtils.extractPrincipalDetails
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*

class ExploreService(private val commandGateway: CommandGateway) {
    private val authentication = SecurityContextHolder.getContext().authentication

    fun getRecommendedProducts(buyerId: UUID): ExploreProductsProjection {
        TODO("Recommender engine needed")
    }

    fun likeProduct(productId: UUID): String {
        val principal = extractPrincipalDetails(authentication = authentication)
        commandGateway.send<Void>(
            LikeProduct(
                buyerId = principal.principalId,
                productId = productId
            )
        )

        return "Product Liked!"
    }
}