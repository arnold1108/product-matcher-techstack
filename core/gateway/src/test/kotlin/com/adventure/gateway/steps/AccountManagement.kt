package com.adventure.gateway.steps

import io.cucumber.java.en.When
import io.cucumber.java.PendingException
import io.cucumber.java.en.Then
import io.cucumber.java.en.And


class AccountManagement {

    @When("a user sends a requets to create an account with the following details:")
    fun aUserSendsARequetsToCreateAnAccountWithTheFollowingDetails(arg0: List<*>) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @Then("the request should be successful")
    fun theRequestShouldBeSuccessful() {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    }

    @And("the response body should contain the message {string}")
    fun theResponseBodyShouldContainTheMessage(arg0: String) {
        // Write code here that turns the phrase above into concrete actions
        throw PendingException()
    } 
}