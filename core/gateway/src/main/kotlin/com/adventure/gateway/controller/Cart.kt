package com.adventure.gateway.controller

import com.adventure.gateway.service.CartService
import com.adventure.gateway.utils.Mappings.CHECKOUT_MAPPING
import com.adventure.gateway.utils.Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING
import com.adventure.gateway.utils.Mappings.VIEW_CART_MAPPING
import com.adventure.gateway.utils.Roles.BUYER_ROLE
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class Cart(private val cartService: CartService) {

    @GetMapping(VIEW_CART_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun viewCart(@RequestParam("buyer_id") buyerId: UUID) =
        ResponseEntity.ok(cartService.fetchCartById(buyerId))

    @PostMapping(REMOVE_PRODUCT_FROM_CART_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun removeProductFromCart(@PathVariable("product_id") productId: UUID): ResponseEntity<String> =
        ResponseEntity.ok(cartService.removeProductFromCart(productId = productId))

    @PostMapping(CHECKOUT_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun checkout(@PathVariable("buyer_id") buyerId: UUID): ResponseEntity<String> =
        ResponseEntity.ok(cartService.checkout(buyerId = buyerId))
}

