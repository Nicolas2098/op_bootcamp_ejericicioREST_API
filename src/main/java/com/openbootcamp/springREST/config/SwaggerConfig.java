package com.openbootcamp.springREST.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot Api Rest Laptops",
                "Laptops api docs",
                "1.0",
                "https://github.com/Nicolas2098/op_bootcamp_ejericicioREST_API",
                new Contact("Nicolas","https://github.com/Nicolas2098/op_bootcamp_ejericicioREST_API","prueba@gmail.com"),
                "MIT",
                "https://www.google.com/",
                Collections.emptyList());
    }
}
