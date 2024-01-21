package com.adventure.gateway

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
	features = ["src/test/resources/features"],
	glue = ["com.adventure.gateway.steps"])
class GatewayApplicationTest
