package com.adventure.store.processors

import com.adventure.apis.cart.Events.CartCheckedOut
import com.adventure.apis.store.Events.*
import com.adventure.apis.store.State.StoreStatus.*
import com.adventure.store.query.entities.Product
import com.adventure.store.query.entities.Store
import com.adventure.store.query.repositories.ProductRepository
import com.adventure.store.query.repositories.StoreRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class StoreEventProcessor(
    private val storeRepository: StoreRepository,
    private val productRepository: ProductRepository
) {

    @EventHandler
    fun handle(event: StoreCreated) {
        val newStore = Store(
            storeId = event.storeId,
            sellerId = event.sellerId,
            category = event.category,
            storeName = event.storeName,
            storeStatus = CREATED
        )

        storeRepository.save(newStore)
    }

    @EventHandler
    fun handle(event: StockAdded) {
        // Persist stock
        val newStock = Product(
            productId = event.productId,
            sellerId = event.sellerId,
            storeId = event.storeId,
            productName = event.productName,
            productCategory = event.productCategory,
            productDescription = event.productDescription,
            price = event.price,
            remainingQuantity = event.remainingQuantity,
            timeAdded = event.timeAdded
        )
        productRepository.save(newStock)

        // Open the Store
        val store = storeRepository.findById(event.storeId)
            .getOrElse { throw NoSuchElementException("store does not exist") }
        val openedStore = store.copy(storeStatus = OPEN)
        storeRepository.save(openedStore)
    }

    @EventHandler
    fun handle(event: StoreClosed) {
        val store = storeRepository.findById(event.storeId)
            .getOrElse { throw NoSuchElementException("store does not exist") }
        val openedStore = store.copy(storeStatus = CLOSED)
        storeRepository.save(openedStore)
    }

    @EventHandler
    fun handle(event: StoreReOpened) {
        val store = storeRepository.findById(event.storeId)
            .getOrElse { throw NoSuchElementException("store does not exist") }
        val openedStore = store.copy(storeStatus = OPEN)
        storeRepository.save(openedStore)
    }

    @EventHandler
    fun handle(event: ProductLiked) {
        val product = productRepository.findById(event.productId)
            .getOrElse { throw NoSuchElementException("Product does not exist") }

        product.likes++
        productRepository.save(product)
    }

    @EventHandler
    fun handle(event: CartCheckedOut) {
        event.cartItems
            .forEach {
                val product = productRepository.findById(it)
                    .getOrElse { throw NoSuchElementException("Product does not exist") }

                if (product.remainingQuantity < 1) {
                    productRepository.delete(product)
                } else {
                    product.remainingQuantity --
                }
                productRepository.save(product)
            }
    }
}