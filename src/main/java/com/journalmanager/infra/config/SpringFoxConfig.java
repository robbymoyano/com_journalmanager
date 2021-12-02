package com.journalmanager.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {
	/**
     * Publish a bean to generate swagger2 endpoints
     * @return a swagger configuration bean
     */
    @Bean
    public Docket usersApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.journalmanager.infra.controller"))
                .build()
                .useDefaultResponseMessages(false);
    }

    /**
     * Access url:port/swagger-ui.html, url:port/v2/api-docs
     * @return
     */
   private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Api Journal")
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }
}
