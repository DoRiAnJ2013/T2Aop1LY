// 代码生成时间: 2025-10-16 02:18:21
package com.example.apidoc;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Produces;
import io.micronaut.views.View;
import io.micronaut.views.Views;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Controller that provides an API documentation page.
 */
@Controller("/api-docs")
@OpenAPIDefinition(
    info = @Info(
        title = "API Documentation", 
        version = "1.0"
    ),
    servers = @Server(url = "http://localhost:8080")
)
@Introspected
public class ApiDocumentationGenerator {

    /**
     * Endpoint to display API documentation.
     * @param request The HTTP request object.
     * @return A map containing the documentation data.
     */
    @Get("/documentation")
    @Produces(MediaType.APPLICATION_JSON)
    @View(Views.SWAGGER_UI)
    public Map<String, Object> documentation(HttpRequest request) {
        Map<String, Object> docs = new HashMap<>();
        docs.put("url", request.getUri());
        // Add more documentation details as needed
        return docs;
    }

    /**
     * Endpoint to serve the Swagger UI.
     * @return The path to the Swagger UI template.
     */
    @Get("/swagger-ui/")
    public String swaggerUI() {
        return "redirect:/swagger-ui/index.html";
    }

    /**
     * Provides the Swagger UI configuration.
     * @return A map containing the Swagger UI configuration.
     */
    @Get("/swagger-ui/config")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> swaggerUIConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("url", "/api-docs/openapi");
        config.put("dom_id", "#swagger-ui");
        config.put("deepLinking", true);
        config.put("displayRequestDuration", true);
        config.put("defaultModelsExpandDepth", -1);
        return config;
    }
}
