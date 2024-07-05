package com.adventure.gateway.controller

import com.adventure.gateway.service.CartService
import com.adventure.gateway.utils.Authorizations.BUYER_ROLE
import com.adventure.gateway.utils.Mappings.ADD_PRODUCT_TO_CART_MAPPING
import com.adventure.gateway.utils.Mappings.CHECKOUT_MAPPING
import com.adventure.gateway.utils.Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING
import com.adventure.gateway.utils.Mappings.VIEW_CART_MAPPING
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class Cart(private val cartService: CartService) {

    @PostMapping(ADD_PRODUCT_TO_CART_MAPPING)
    fun addProductToCart(@PathVariable("product_id") productId: UUID, @PathVariable("quantity") quantity: Int): ResponseEntity<String> =
        ResponseEntity.ok(cartService.addProductToCart(productId = productId, quantity = quantity))

    @GetMapping(VIEW_CART_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun viewCart(@RequestParam("shopper_id") shopperId: UUID) =
        ResponseEntity.ok(cartService.fetchCartById(shopperId))

    @PostMapping(REMOVE_PRODUCT_FROM_CART_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun removeProductFromCart(@RequestParam("product_id") productId: UUID): ResponseEntity<String> =
        ResponseEntity.ok(cartService.removeProductFromCart(productId = productId))

    @PostMapping(CHECKOUT_MAPPING)
    @PreAuthorize(BUYER_ROLE)
    fun checkout(@RequestParam("shopper_id") shopperId: UUID): ResponseEntity<String> =
        ResponseEntity.ok(cartService.checkout(shopperId = shopperId))
}

