package com.adventure.store.processors

import com.adventure.apis.store.Queries.*
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class StoreQueryProcessor{

    @QueryHandler
    fun answer(query: DoesProductExist) {
        TODO()
    }

    @QueryHandler
    fun answer(query: ManageStoreQuery) {
        TODO()
    }

    @QueryHandler
    fun answer(query: DoesStoreExist) {
        TODO()
    }
}