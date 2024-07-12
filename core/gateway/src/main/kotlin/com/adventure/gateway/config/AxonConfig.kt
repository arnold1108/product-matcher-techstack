package com.adventure.gateway.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.json.JacksonSerializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AxonConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule.Builder().build())
    }
    @Bean
    fun eventSerializer(objectMapper: ObjectMapper): Serializer {
        return JacksonSerializer.builder().objectMapper(objectMapper).build()
    }

    @Bean
    fun messageSerializer(objectMapper: ObjectMapper): Serializer {
        return JacksonSerializer.builder().objectMapper(objectMapper).build()
    }
}