package com.adventure.gateway.steps

import com.adventure.apis.marketplace.QueryResults.*
import com.adventure.apis.marketplace.Requests.AddItemToProductRequest
import com.adventure.gateway.utils.Mappings
import com.adventure.gateway.utils.Mappings.ADD_PRODUCT_TO_CART_MAPPING
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.And
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.junit.Assert.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.*
import org.springframework.web.reactive.function.BodyInserters


@WebFluxTest(controllers = [Explore::class])
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Explore {
    @Autowired private lateinit var webTestClient: WebTestClient
    @MockBean private lateinit var commandGateway: ReactorCommandGateway
    private lateinit var response: EntityExchangeResult<*>


    @When("the buyer of id {string} sends a request to explore products in the marketplace")
    fun theBuyerOfIdSendsARequestToExploreProductsInTheMarketplace(buyerId: String) {
        response = webTestClient
            .get()
            .uri(Mappings.EXPLORE_PRODUCT_MAPPING, buyerId)
            .exchange()
            .expectBody(ExploreProductsProjection::class.java)
            .returnResult()
    }

    @When("a buyer of id {string} sends a request to like a product of id {string}")
    fun aBuyerOfIdSendsARequestToLikeAProductOfId(buyerId: String, productId: String) {
        response = webTestClient
            .post()
            .uri(Mappings.LIKE_PRODUCT_MAPPING, productId, buyerId)
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @When("the buyer of id {string} sends a request to add {string} items of a product of id <product_id> to cart")
    fun theBuyerOfIdSendsARequestToAddItemsOfAProductOfIdProduct_idToCart(buyerId: String, productId: String) {
        response = webTestClient
            .post()
            .uri(ADD_PRODUCT_TO_CART_MAPPING, buyerId, productId)
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @When("the buyer of id {string} sends a request to add {int} items of a product of id {string} to cart")
    fun theBuyerOfIdSendsARequestToAddItemsOfAProductOfIdToCart(buyerId: String, quantity: Int, productId: String) {
        val addProductToItemToProductRequest = AddItemToProductRequest(
            quantity = quantity
        )
        response = webTestClient
            .post()
            .uri(ADD_PRODUCT_TO_CART_MAPPING, buyerId, productId)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(addProductToItemToProductRequest))
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @Then("the explore request should be successful")
    fun theExploreRequestShouldBeSuccessful() {
        assertEquals(response.status, HttpStatus.OK)
    }

    @And("the explore response body should contain the message {string}")
    fun theResponseBodyShouldContainTheMessage(expectedMessage: String) {
        assertEquals(response.responseBody, expectedMessage)
    }


    @And("the explore response should have the following details:")
    fun theExploreResponseShouldHaveTheFollowingDetails(table: DataTable) {
        val actualResult = response.responseBody as ExploreProductsProjection
        val expectedResult = table.asMaps(String::class.java, String::class.java)

        for (column: Map<String, String> in expectedResult) {
            assertEquals(actualResult.recommendedProducts[0].productName, column["product_name"])
            assertEquals(actualResult.recommendedProducts[0].productCategory, column["product_category"])
            assertEquals(actualResult.recommendedProducts[0].productDescription, column["product_description"])
            assertEquals(actualResult.recommendedProducts[0].price, column["price"]!!.toDouble(), 0.0)

        }
    }
}