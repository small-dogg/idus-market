package com.idus.market.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Errors;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  //Todo NumberFormatException in Swagger API-DOCS

  @Bean
  public Docket docket() {
    Docket docket = new Docket(DocumentationType.SWAGGER_2);
    TypeResolver typeResolver = new TypeResolver();

    ApiInfo apiInfo = new ApiInfoBuilder()
        .title("idus Market API v1.0")
        .description("idus Market API v1.0입니다.")
        .build();

    docket.apiInfo(apiInfo)
        .ignoredParameterTypes(Errors.class)
        .securityContexts(Arrays.asList(securityContext()))
        .securitySchemes(Arrays.asList(apiKey()))
        .alternateTypeRules(AlternateTypeRules
            .newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)));

    return docket.select()
        .apis(RequestHandlerSelectors.basePackage("com.idus.market.controller"))
        .paths(PathSelectors.ant("/api/v1/**"))
        .build();
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder().securityReferences(defaultAuth()).build();
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Arrays.asList(new SecurityReference("bearer", authorizationScopes));
  }

  private ApiKey apiKey() {
    return new ApiKey("bearer", "X-Auth-Token", "header");
  }

  @Getter
  @Setter
  @ApiModel
  static class Page {

    @ApiModelProperty(value = "페이지 번호 (첫페이지 : 0, 기본값 : 0)")
    private int page;

    @ApiModelProperty(value = "한 페이지 당 출력 수 (기본값 : 20)")
    private int size;
  }
}
