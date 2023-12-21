package com.adventure.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.adventure.gateway.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfoMetaData())
    }
    private fun apiInfoMetaData(): ApiInfo {
        return ApiInfoBuilder()
            .title("Soko")
            .description("Soko API Documentation")
            .contact( Contact("ProductMatcher", "https://adventure.com/", "opiyo@adventure.com"))
            .version("1.0.0")
            .build()
    }
}