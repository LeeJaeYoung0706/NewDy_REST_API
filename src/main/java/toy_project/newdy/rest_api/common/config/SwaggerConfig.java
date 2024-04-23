package toy_project.newdy.rest_api.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.jwt.header}")
    private String SPRING_JWT_HEADER;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Bearer Authorization"))
                .components(new Components().addSecuritySchemes(SPRING_JWT_HEADER ,createAPIKeyScheme()))
                .info(new Info().title("NewDy REST API")
                        .version("1.0")
                );
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Authentication Group")
                .pathsToMatch("/auth/**")
                .packagesToScan("toy_project.newdy.rest_api.auth.controller")
                .build();
    }

    @Bean
    public GroupedOpenApi errorApi() {
        return GroupedOpenApi.builder()
                .group("ErrorCode Group")
                .pathsToMatch("/error_code/**")
                .packagesToScan("toy_project.newdy.rest_api.common.controller")
                .build();
    }


}
