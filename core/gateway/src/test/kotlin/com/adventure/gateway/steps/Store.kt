package com.adventure.gateway.steps

import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.Then


class Store {

    @When("the seller of id <seller_id> sends a request to create a new store named <MyStore> belonging to the category <Sports>")
    fun theSellerOfIdSeller_idSendsARequestToCreateANewStoreNamedMyStoreBelongingToTheCategorySports() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @When("the seller of id <seller_id> owning a store of id <store_id> adds stock with the following details:")
    fun theSellerOfIdSeller_idOwningAStoreOfIdStore_idAddsStockWithTheFollowingDetails(arg0: List<*>) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("the product should be added successfully")
    fun theProductShouldBeAddedSuccessfully() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    } 
}