package com.adventure.gateway.steps

import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.Then
import io.cucumber.java.en.And


class Store {
    @When("the seller of id {string} sends a request to create a new store named {string} belonging to the category {string}")
    fun theSellerOfIdSendsARequestToCreateANewStoreNamedBelongingToTheCategory(
        sellerId: String, storeName: String, storeCategory: String
    ) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("the product should be added successfully")
    fun theProductShouldBeAddedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("the buyer of id {string} sends a request to remove a product of id {string} from the cart")
    fun theBuyerOfIdSendsARequestToRemoveAProductOfIdFromTheCart(arg0: String, arg1: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }


    @And("the response body should contain the message {string}productId{string}")
    fun theResponseBodyShouldContainTheMessageProductId(arg0: String, arg1: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("the seller of id {string} owning a store of id {string} adds stock with the following details:")
    fun theSellerOfIdOwningAStoreOfIdAddsStockWithTheFollowingDetails(arg0: String, arg1: String, arg2: List<*>) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @And("the response body should contain the message {string}")
    fun theResponseBodyShouldContainTheMessage(arg0: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }
}