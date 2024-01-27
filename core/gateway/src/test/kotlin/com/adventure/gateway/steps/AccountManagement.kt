package com.adventure.gateway.steps

import com.adventure.apis.accounts.Requests.*
import com.adventure.gateway.controller.Account
import com.adventure.gateway.utils.Mappings.ACCOUNT_CREATION_MAPPING
import io.cucumber.datatable.DataTable
import io.cucumber.java.en.When
import io.cucumber.java.en.Then
import io.cucumber.java.en.And
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.time.LocalDate
import io.cucumber.java.PendingException
import org.junit.Assert
import org.springframework.http.HttpStatus
import org.springframework.test.web.reactive.server.EntityExchangeResult

@WebFluxTest(controllers = [Account::class])
class AccountManagement {
    @Autowired
    private lateinit var webTestClient: WebTestClient
    @MockBean
    private lateinit var commandGateway: ReactorCommandGateway
    private var createAccountRequest: CreateAccountRequest? = null
    private lateinit var response: EntityExchangeResult<String>

    @When("a user sends a requests to create an account with the following details:")
    fun aUserSendsARequestsToCreateAnAccountWithTheFollowingDetails(details: DataTable) {
        val rows = details.asMaps(String::class.java, String::class.java)
        for (column: Map<String, String> in rows) {
            createAccountRequest = CreateAccountRequest(
                firstName = column["first_name"]!!,
                lastName = column["last_name"]!!,
                dob = LocalDate.parse(column["date_of_birth"]!!),
                email = column["email_address"]!!,
                gender = column["gender"]!!,
                country = column["country"]!!,
                role = column["role"]!!,
            )
        }
    }

    @Then("the request should be successful")
    fun theRequestShouldBeSuccessful() {
        response = webTestClient.post()
            .uri(ACCOUNT_CREATION_MAPPING)
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(createAccountRequest!!))
            .exchange()
            .expectBody(String::class.java)
            .returnResult()
    }

    @Then("the account management request should be successful")
    fun theAccountManagementRequestShouldBeSuccessful() {
        Assert.assertEquals(response.status, HttpStatus.OK)

    }

    @And("the account management response body should contain the message {string}")
    fun theAccountManagementResponseBodyShouldContainTheMessage(expectedMessage: String) {
        assertEquals(response.responseBody, expectedMessage)
        throw PendingException()
    }
}