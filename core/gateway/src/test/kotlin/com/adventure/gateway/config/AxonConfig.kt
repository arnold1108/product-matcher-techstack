package com.adventure.gateway.config

import org.axonframework.commandhandling.CommandBus
import org.axonframework.extensions.reactor.commandhandling.gateway.DefaultReactorCommandGateway
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AxonConfig {

    @Bean
    fun reactorCommandGateway(commandBus: CommandBus): DefaultReactorCommandGateway {
        return DefaultReactorCommandGateway
            .builder()
            .commandBus(commandBus)
            .build()
    }
}
