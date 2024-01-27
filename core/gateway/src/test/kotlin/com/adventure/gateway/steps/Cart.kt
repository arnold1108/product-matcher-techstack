package com.adventure.gateway.steps

import com.adventure.apis.cart.QueryResults
import com.adventure.apis.cart.QueryResults.*
import com.adventure.gateway.controller.Cart
import com.adventure.gateway.utils.Mappings.CHECKOUT_MAPPING
import com.adventure.gateway.utils.Mappings.REMOVE_PRODUCT_FROM_CART_MAPPING
import com.adventure.gateway.utils.Mappings.VIEW_CART_MAPPING
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.Then
import io.cucumber.spring.CucumberContextConfiguration
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.EntityExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import java.util.UUID

@AutoConfigureWebTestClient
@WebFluxTest(controllers = [Cart::class])
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Cart {
    @Autowired private lateinit var webTestClient: WebTestClient
    @MockBean private lateinit var commandGateway: ReactorCommandGateway
    private lateinit var response: EntityExchangeResult<*>
    val objectMapper = ObjectMapper()

    @When("a buyer with id {string} sends a request to view their cart")
    fun aBuyerWithIdSendsARequestToViewTheirCart(buyerId: String) {
        response = webTestClient
            .get()
            .uri(VIEW_CART_MAPPING, buyerId)
            .exchange()
            .expectBody(ViewCartQueryResult::class.java)
            .returnResult()
    }

    @Then("the response should have the following details:")
    fun theResponseShouldHaveTheFollowingDetails(details: DataTable) {
        val expectedResult = response.responseBody as ViewCartQueryResult
        val actualResult = details.asMaps(String::class.java, String::class.java)

        for (column: Map<String, String> in actualResult) {
            assertEquals(expectedResult.buyerId.toString(), column["cart_id"])
            assertEquals(expectedResult.totalItems.toString(), column["total_items"])
            assertEquals(expectedResult.totalPrice.toString(), column["total_price"])

            val cartItemJson = column["cart_items"]?: ""
            val expectedCartItem = objectMapper.readValue(cartItemJson, CartItem::class.java)
            assertEquals(expectedResult.cartItems, expectedCartItem)
        }

    }

    @When("a buyer of id {string} sends a request to checkout")
    fun aBuyerOfIdSendsARequestToCheckout(buyerId: String) {
        response = webTestClient
            .post()
            .uri(CHECKOUT_MAPPING, buyerId)
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @When("the buyer of id {string} sends a request to remove a product of id {string} from the cart")
    fun theBuyerOfIdSendsARequestToRemoveAProductOfIdFromTheCart(buyerId: String, productId: String) {
        response = webTestClient
            .post()
            .uri(REMOVE_PRODUCT_FROM_CART_MAPPING, productId, buyerId)
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @Then("the response body should contain the message {string}")
    fun theResponseBodyShouldContainTheMessage(expectedMessage: String) {
        val actualMessage = response.responseBody as String
        assertEquals(expectedMessage, actualMessage)
    }
}









