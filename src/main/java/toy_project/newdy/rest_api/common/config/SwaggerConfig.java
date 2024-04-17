package toy_project.newdy.rest_api.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {

        Info swaggerInfo = new Info()
                .title("NewDy REST API")
                .description("NewDy 토이 프로젝트")
                .version("1.0.0");

        return new OpenAPI()
                .info(swaggerInfo);
    }

    @Bean
    public GroupedOpenApi authApi() {
        return GroupedOpenApi.builder()
                .group("Authentication Group")
                .pathsToMatch("/auth/**")
                .build();
    }
}
