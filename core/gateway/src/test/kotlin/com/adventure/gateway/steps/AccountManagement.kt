package com.adventure.gateway.steps

import io.cucumber.spring.CucumberContextConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.web.client.RestTemplate

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountManagement {
    @LocalServerPort
    var port: Int = 0
    val restTemplate: RestTemplate =  RestTemplate()


}