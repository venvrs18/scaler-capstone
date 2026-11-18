package project.scaler.capstone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI capstoneOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce Web Application API documentation")
                        .description("Backend APIs for the Scaler Capstone E-Commerce Project (User Management, Product Catalog, Cart & Checkout)")
                        .version("v1.0")
                        .contact(new Contact().name("Development Team")));
    }
}