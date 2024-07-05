package com.adventure.store.processors

import com.adventure.apis.cart.QueryResults.CartItem
import com.adventure.apis.store.Queries.*
import com.adventure.apis.store.QueryResults.ManageStoreProjection
import com.adventure.apis.store.QueryResults.ProductInfo
import com.adventure.store.query.repositories.ProductRepository
import com.adventure.store.query.repositories.StoreRepository
import org.axonframework.queryhandling.QueryExecutionException
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class StoreQueryProcessor(
    private val storeRepository: StoreRepository,
    private val productRepository: ProductRepository
){

    @QueryHandler
    fun answer(query: DoesProductExist): Boolean =
        productRepository.existsById(query.productId)

    @QueryHandler
    fun answer(query: DoesStoreExist): Boolean =
        storeRepository.existsById(query.storeId)

    @QueryHandler
    fun answer(query: ManageStoreQuery): ManageStoreProjection {
        val storeEntity = storeRepository.findById(query.storeId)
            .orElseThrow {
                QueryExecutionException("Store Not Found", NoSuchElementException())
            }

        val products = productRepository.findByStoreId(storeId = query.storeId).map {
            ProductInfo(
                productId = it.productId,
                productName = it.productName,
                remainingQuantity = it.remainingQuantity,
                likes = it.likes,
                price = it.price,
                timeAdded = it.timeAdded
            )
        }
        return ManageStoreProjection(
            storeId = storeEntity.storeId,
            sellerId = storeEntity.sellerId,
            storeName = storeEntity.storeName,
            products = products
        )
    }

    @QueryHandler
    fun answer(query: FetchProductDetails): CartItem {
        val product = productRepository.findById(query.productId)
            .orElseThrow {
                QueryExecutionException("Store Not Found", NoSuchElementException())
            }

        return CartItem(
            productId = product.productId,
            productName = product.productName,
            price = product.price,
            quantity = product.remainingQuantity
        )
    }
}