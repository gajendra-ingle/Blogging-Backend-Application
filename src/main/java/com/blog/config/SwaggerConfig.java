package com.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Blogging Application",
        version = "1.0",
        description = "This backend service is developed by Gajendra Ingle.",
        contact = @Contact(
            name = "Gajendra Ingle",
            email = "gajendraingle01@gmail.com",
            url = "https://github.com/gajendra-ingle"
        )
    )
)
public class SwaggerConfig {
    // No extra setup needed
}
