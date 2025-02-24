package com.saiDeveloper.Spring_Boot_Starter_Template.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@io.swagger.v3.oas.annotations.security.SecurityScheme(
    name = "bearerAuth",
    type = io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT",
    description = "JWT token authentication"
)
@OpenAPIDefinition(
    info = @Info(
        title = "Course Management API",
        version = "1.0",
        description = "API for managing courses, student enrollments and authentication",
        contact = @Contact(
            name = "Support Team",
            email = "support@example.com"
        )
    )
)
public class OpenApiConfig {
}