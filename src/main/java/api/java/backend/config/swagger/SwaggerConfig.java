package api.java.backend.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    @Bean
    public Docket apiV1(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("1.0"))
                .groupName("v1")
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.java.backend.controller.v1"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket apiV2(){
        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("2.0"))
                .groupName("v2")
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.java.backend.controller.v2"))
                .paths(PathSelectors.any())
                .build();
    }

    private SecurityContext securityContext(){
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }

    private ApiInfo  apiInfo(String apiVersion){
        return new ApiInfoBuilder()
                .title("My API")
                .version(apiVersion)
                .description("Backend")
                .termsOfServiceUrl("https://localhost/terms")
                .license("Prueba")
                .licenseUrl("Prueba")
                .contact(new Contact("Prueba","prueba","prueba"))
                .extensions(Collections.emptyList())
                .build();
    }
    private ApiKey apiKey(){
        return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

}
