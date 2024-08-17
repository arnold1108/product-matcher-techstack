package com.adventure.accounts.integration

import org.axonframework.test.server.AxonServerContainer
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait

class TestContainerInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    override fun initialize(applicationContext: ConfigurableApplicationContext) {
        val axon = AxonServerContainer().apply {
            dockerImageName = "axoniq/axonserver:2023.2.2-jdk-17"
            withAxonServerName("axon-server-test")
            withDevMode(true)
            setWaitStrategy(Wait.forListeningPort())
        }

        val postreSQLContainer = PostgreSQLContainer<Nothing>("postgres:15.5-alpine3.17")
            .apply {
                withDatabaseName("accounts_db_test")
                withUsername("postgres")
                withPassword("postgres")
                withExposedPorts(5432)
            }

        startContainers(axon, postreSQLContainer)
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
            applicationContext,
            "POSTGRES_JDBC_URL=${postreSQLContainer.jdbcUrl}",
            "POSTGRES_USERNAME=${postreSQLContainer.username}",
            "POSTGRES_PASSWORD=${postreSQLContainer.password}",
            "AXON_SERVER_ADDRESS=${axon.axonServerAddress}"

        )

    }

    private fun startContainers(vararg containers: GenericContainer<*>) {
        containers.forEach { it.start() }
    }


}