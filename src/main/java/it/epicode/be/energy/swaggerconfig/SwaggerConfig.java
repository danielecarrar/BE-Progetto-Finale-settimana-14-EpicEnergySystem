package it.epicode.be.energy.swaggerconfig;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(title = "EnergySystem API", version = "v0.7.9", description = "Epic Energy System REST Service - Gestione clienti e fatture", contact = @Contact(email = "daniele._k@gmail.com", name = "Daniele Carraro", url = "https://github.com/danielecarrar/")))
@Configuration
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
public class SwaggerConfig {

}
