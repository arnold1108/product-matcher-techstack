package com.adventure.gateway.steps

import com.adventure.gateway.controller.Cart
import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.Then
import io.cucumber.spring.CucumberContextConfiguration
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(controllers = [Cart::class])
class Cart {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    @MockBean
    private lateinit var commandGateway: ReactorCommandGateway

    @When("a buyer with id {string} sends a request to view their cart")
    fun aBuyerWithIdSendsARequestToViewTheirCart(arg0: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("the response should have the following details:")
    fun theResponseShouldHaveTheFollowingDetails(arg0: List<*>) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("a buyer of id {string} sends a request to checkout")
    fun aBuyerOfIdSendsARequestToCheckout(arg0: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }
}