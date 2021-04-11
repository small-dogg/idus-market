package com.idus.market.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket docket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);

    ApiInfo apiInfo = new ApiInfoBuilder()
        .title("idus Market API v1.0")
        .description("idus Market API v1.0입니다.")
        .build();

    docket.apiInfo(apiInfo)
        .ignoredParameterTypes(Errors.class)
        .securitySchemes(Arrays.asList(apiKey()));

    return docket.select()
        .apis(RequestHandlerSelectors.basePackage("com.idus.market.controller"))
        .paths(PathSelectors.ant("/api/v1/**"))
        .build();
  }


  private ApiKey apiKey() {
    return new ApiKey("Bearer {accessToken}", "X-Auth-Token", "header");
  }

}
