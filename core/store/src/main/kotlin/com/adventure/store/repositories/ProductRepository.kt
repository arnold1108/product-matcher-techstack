package com.adventure.store.repositories

import com.adventure.apis.store.Commands.*
import com.adventure.store.entities.Product
import org.springframework.data.cassandra.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface ProductRepository: CrudRepository<Product, UUID> {
    @Query("INSERT INTO products (productid, sellerid, storeid, productname, productcategory, productdescription, price, remainingquantity, likes, timeadded) " +
            "VALUES (:#{#details.productId}, :#{#details.sellerId}, :#{#details.storeId}, :#{#details.productName}, " +
            ":#{#details.productCategory}, :#{#details.productDescription}, :#{#details.price}, :#{#details.remainingQuantity}, " +
            ":#{#details.likes}, :#{#details.timeAdded})")
    fun addStock(details: AddStock)
    @Query("UPDATE products SET likes = likes + 1 WHERE product_id =?0")
    fun likeProduct(productId: UUID)

    @Query("INSERT INTO products_liked (buyer_id, product_id) VALUES (?0, ?1)")
    fun addProductLike(buyerId: UUID, productId: UUID)

}