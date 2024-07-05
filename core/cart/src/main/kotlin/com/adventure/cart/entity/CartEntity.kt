package com.adventure.cart.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "carts")
class CartEntity {
    @Id
    @Column(name = "shopper_id") var shopperId: UUID = UUID.randomUUID()
    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL])
    @Column(name = "cart_items") var cartItems: MutableSet<CartItems> = mutableSetOf()
    @Column(name = "first_name") var totalItems: Int = 0
    @Column(name = "first_name") var totalPrice: Double = 0.00

    companion object {
        fun addItem(
            shopperId: UUID,
            productId: UUID,
            productName: String,
            quantity: Int,
            itemPrice: Double

        ): CartEntity {
            val cartEntity = CartEntity().apply {
                this.shopperId =  shopperId
                totalItems + 1
                totalPrice + itemPrice * quantity
            }
            val cartItems = CartItems().apply {
                this.itemId = productId
                this.productName = productName
                this.quantity - quantity
                this.unitPrice = itemPrice
                this.cart = cartEntity
            }
            cartEntity.cartItems.add(cartItems)

            return cartEntity

        }
    }

    fun removeItem(
        cartItem: CartItems,
        quantity: Int,
        unitPrice: Double
    ) {
        totalItems - 1
        totalPrice - (unitPrice * quantity)
        cartItems.remove(cartItem)
    }
}

@Entity
@Table(name = "cart_items")
class CartItems {
    @Id
    @Column(name = "item_id") var itemId: UUID = UUID.randomUUID()
    @Column(name = "product_name") var productName: String = ""
    @Column(name = "quantity") var quantity: Int = 0
    @Column(name = "unit_price") var unitPrice: Double = 0.00
    @ManyToOne @JoinColumn(name = "shopper_id", nullable = false) lateinit var cart: CartEntity
}