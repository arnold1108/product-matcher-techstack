package com.adventure.gateway.controller

import com.adventure.apis.store.QueryResults.*
import com.adventure.apis.store.Requests.*
import com.adventure.gateway.service.StoreService
import com.adventure.gateway.utils.Mappings.ADD_STOCK_MAPPING
import com.adventure.gateway.utils.Mappings.CREATE_STORE_MAPPING
import com.adventure.gateway.utils.Mappings.MANAGE_STORE_MAPPING
import com.adventure.gateway.utils.Roles.SELLER_ROLE
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class Store(private val store: StoreService) {

    @PostMapping(CREATE_STORE_MAPPING)
    @PreAuthorize(SELLER_ROLE)
    fun createStore(@RequestBody request: CreateStoreRequest): ResponseEntity<String> =
        ResponseEntity.ok(store.addStore(request = request))


    @PostMapping(ADD_STOCK_MAPPING)
    @PreAuthorize(SELLER_ROLE)
    fun addStock(
        @RequestParam("store_id") storeId: UUID,
        @RequestBody request: AddStockRequest
    ): ResponseEntity<String>  =
        ResponseEntity.ok(store.addStock(request = request, storeId = storeId))

    @GetMapping(MANAGE_STORE_MAPPING)
    @PreAuthorize(SELLER_ROLE)
    suspend fun manageStore(@RequestParam("store_id") storeId: UUID): ResponseEntity<ManageStoreProjection> =
        ResponseEntity.ok(store.getStoreById(storeId = storeId))
}