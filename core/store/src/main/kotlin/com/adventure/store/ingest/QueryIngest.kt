package com.adventure.store.ingest

import com.adventure.apis.store.Queries.ManageStore
import org.axonframework.queryhandling.QueryHandler

class QueryIngest {
    @QueryHandler
    fun handle(query: ManageStore) {
        TODO()
    }
}