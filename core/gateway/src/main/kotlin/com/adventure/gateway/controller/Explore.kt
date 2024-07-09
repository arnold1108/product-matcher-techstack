package com.adventure.gateway.controller

import com.adventure.apis.marketplace.QueryResults.ExploreProductsProjection
import com.adventure.gateway.service.ExploreService
import com.adventure.gateway.utils.Authorizations.BUYER_ROLE
import com.adventure.gateway.utils.Mappings.EXPLORE_PRODUCT_MAPPING
import com.adventure.gateway.utils.Mappings.LIKE_PRODUCT_MAPPING
import org.springframework.http.ResponseEntity
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class Explore(private val exploreService: ExploreService) {

    @GetMapping(EXPLORE_PRODUCT_MAPPING)
//    @PreAuthorize(BUYER_ROLE)
    fun exploreProducts(@RequestParam("buyer_id") buyerId: UUID): ResponseEntity<ExploreProductsProjection> =
        ResponseEntity.ok(exploreService.getRecommendedProducts(buyerId = buyerId))

    @PostMapping(LIKE_PRODUCT_MAPPING)
    fun likeProduct(@RequestParam("product_id") productId: UUID): ResponseEntity<String> =
        ResponseEntity.ok(exploreService.likeProduct(productId = productId))
}