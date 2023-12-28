package com.adventure.store.repositories

import com.adventure.store.entities.Product
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ProductRepository: CrudRepository<Product, UUID> {
    @Query("INSERT INTO products (product_id, store_id, product_name, product_category, product_description, price)")
    fun addStock(
        storeId: UUID,
        productId: UUID,
        productName: String,
        productCategory: String,
        productDescription: String,
        price: Double
    )
    @Query("UPDATE products SET likes = likes + 1 WHERE product_id =?0")
    fun likeProduct(productId: UUID)

    @Query("INSERT INTO products_liked (buyer_id, product_id) VALUES (?0, ?1)")
    fun addProductLike(buyerId: UUID, productId: UUID)

}