package com.adventure.store.integration

import com.datastax.oss.driver.api.core.CqlSession
import org.axonframework.test.server.AxonServerContainer
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.CassandraContainer
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import java.time.Duration

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

//        val keySpaceName = "store"
        val cassandraContainer = CassandraContainer<Nothing>("cassandra:3.11.17-jammy")
            .apply {
                withExposedPorts(9042)
                waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofMinutes(2)))

            }

        startContainers(axon, postreSQLContainer, cassandraContainer)
        println(cassandraContainer.logs)
        val keySpaceName =  "store_svc_test"

        val cqlSession = CqlSession.builder()
            .addContactPoint(cassandraContainer.contactPoint)
            .withLocalDatacenter(cassandraContainer.localDatacenter)
            .build()

        // Create the keyspace
        cqlSession.execute(
            """
            CREATE KEYSPACE IF NOT EXISTS $keySpaceName 
            WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};
            """.trimIndent()
        )

        // Close the initial session
        cqlSession.close()

        val keyspaceSession = CqlSession.builder()
            .addContactPoint(cassandraContainer.contactPoint)
            .withLocalDatacenter(cassandraContainer.localDatacenter)
            .withKeyspace(keySpaceName)
            .build()


        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
            applicationContext,
            "POSTGRES_JDBC_URL=${postreSQLContainer.jdbcUrl}",
            "POSTGRES_USERNAME=${postreSQLContainer.username}",
            "POSTGRES_PASSWORD=${postreSQLContainer.password}",
            "AXON_SERVER_ADDRESS=${axon.axonServerAddress}",
            "LOCAL_DATA_CENTER=${cassandraContainer.localDatacenter}",
            "spring.application.keyspace-name=$keySpaceName"
        )

    }

    private fun startContainers(vararg containers: GenericContainer<*>) {
        containers.forEach { it.start() }
    }


}