package com.com4in.itsm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class swaggerConfig {

    private static final String REFERENCE = "Authorization";

    @Bean
    public Docket api() {
        // type 은 openApi 사용시 oas_30 나머지는 똑같음
        return new Docket(DocumentationType.OAS_30)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.com4in.itsm.controller"))
                .paths(PathSelectors.any())
                .build()
                // 보안에 따라 사용
                .securityContexts(List.of(securityContext()))  //.reference("bearer").build()
//                .securitySchemes(List.of(securityScheme())); //인증 타입에 따라 정의
                .securitySchemes(List.of(bearerAuthSecurityscheme())); //인증 타입에 따라 정의
    }

    private SecurityContext securityContext() {
        return springfox.documentation
                .spi.service.contexts
                .SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .operationSelector(operationcontext -> true)
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
        return List.of(new SecurityReference(REFERENCE, authorizationScopes));
    }

    private HttpAuthenticationScheme bearerAuthSecurityscheme(){
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(REFERENCE).build();
    }

}
