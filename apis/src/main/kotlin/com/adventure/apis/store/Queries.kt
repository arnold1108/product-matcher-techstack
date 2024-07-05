package com.adventure.apis.store

import java.util.*

class Queries {
    data class ManageStoreQuery(
        val storeId: UUID
    )
    data class DoesStoreExist(val storeId: UUID)
    data class DoesProductExist(val productId: UUID)

    data class FetchProductDetails(val productId: UUID)
}