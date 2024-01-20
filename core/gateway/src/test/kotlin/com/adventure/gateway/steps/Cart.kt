package com.adventure.gateway.steps

import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.And


class Cart {

    @When("a buyer with id <buyer_id> sends a request to view their cart")
    fun aBuyerWithIdBuyer_idSendsARequestToViewTheirCart() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @And("the response should have the following details:")
    fun theResponseShouldHaveTheFollowingDetails(arg0: List<*>) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("the buyer of id <buyerId> sends a request to remove a product of id <productId> from the cart")
    fun theBuyerOfIdBuyerIdSendsARequestToRemoveAProductOfIdProductIdFromTheCart() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("a buyer of id <buyer_id> sends a request to checkout")
    fun aBuyerOfIdBuyer_idSendsARequestToCheckout() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    } 
}