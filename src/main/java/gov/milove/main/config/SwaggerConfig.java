package gov.milove.main.config;

import gov.milove.main.domain.News;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up OpenAPI documentation with SpringDoc. This configuration
 * enhances API documentation by providing additional metadata such as title, version, description,
 * and contact information.
 */

@Configuration
public class SwaggerConfig {

  /**
   * Creates a custom OpenAPI configuration bean to be used by SpringDoc for generating API
   * documentation. This method sets up the basic information about the API.
   *
   * @return an instance of OpenAPI with configured metadata.
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Miloverada api service")
            .version("1.0.0")
            .summary("Provides api docs wich to serves the Miloverada project")
            .description("API documentation for Miloverada project, has a two modules: main and forum")
            .contact(new Contact()
                .name("Liashenko Andrii")
                .email("a.liashenko@e-u.edu.ua")
                .url("https://github.com/LiashenkoAndrey/miloverada.gov.ua")
            )
        );
  }


}
