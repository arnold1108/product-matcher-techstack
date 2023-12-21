package com.adventure.marketplace.ingest

import com.adventure.apis.marketplace.Queries.ExploreProducts
import org.axonframework.queryhandling.QueryHandler

class QueryIngest {
    @QueryHandler
    fun handle(query: ExploreProducts) {
        TODO()
    }
}