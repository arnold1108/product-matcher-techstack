package com.adventure.gateway.steps

import com.adventure.apis.store.Requests
import com.adventure.apis.store.Requests.CreateStoreRequest
import com.adventure.apis.store.State
import com.adventure.gateway.utils.Mappings.CREATE_STORE_MAPPING
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.cucumber.java.en.And
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.*
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.util.*

@WebFluxTest(controllers = [Store::class])
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension::class)
class Store {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    @MockBean
    private lateinit var commandGateway: ReactorCommandGateway
    private lateinit var response: EntityExchangeResult<String>

    @When("the seller of id {string} sends a request to create a new store named {string} belonging to the category {string}")
    fun theSellerOfIdSendsARequestToCreateANewStoreNamedBelongingToTheCategory(
        sellerId: String, storeName: String, storeCategory: String
    ) {
         val createStoreRequest = CreateStoreRequest(
            storeName = storeName,
            category = State.StoreCategory.valueOf(storeCategory.uppercase(Locale.getDefault()))
        )
        response = webTestClient.post()
            .uri(CREATE_STORE_MAPPING, sellerId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(createStoreRequest))
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @Then("the product should be added successfully")
    fun theProductShouldBeAddedSuccessfully() {
        assertEquals(response.status, HttpStatus.OK)
    }

    @When("the seller of id {string} owning a store of id {string} adds stock with the following details:")
    fun theSellerOfIdOwningAStoreOfIdAddsStockWithTheFollowingDetails(
        sellerId: String, storeId: String, details: DataTable
    ) {
        val rows = details.asMaps(String::class.java, String::class.java)
        for (column: Map<String, String> in rows) {
            val addStockRequest = Requests.AddStockRequest(
                productName = column["product_name"]!!,
                productCategory = column["product_category"]!!,
                productDescription = column["product_description"]!!,
                quantity = column["quantity"]!!.toInt(),
                price = column["price"]!!.toDouble()
            )
            response = webTestClient.post()
                .uri("/store/$sellerId/$storeId/add-stock")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(addStockRequest))
                .exchange()
                .expectBody(String::class.java)
                .returnResult()
        }
    }

    @And("the store response body should contain the message {string}")
    fun theStoreResponseBodyShouldContainTheMessage(expectedMessage: String) {
        assertEquals(response.responseBody, expectedMessage)
    }
}