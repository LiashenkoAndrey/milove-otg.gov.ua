package gov.milove.config;

import javax.crypto.spec.SecretKeySpec;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
@EnableAutoConfiguration
@Log4j2
public class TestConfig {


  @Bean
  @ServiceConnection(name = "postgres")
  public static PostgreSQLContainer<?> postgreSQLContainer(
      @Value("${POSTGRES_CONTAINER_VERSION}") String containerVersion,
      @Value("${POSTGRES_CONTAINER_USERNAME}") String containerUsername,
      @Value("${POSTGRES_CONTAINER_PASSWORD}") String containerPassword) {

    final PostgreSQLContainer<?> container = new PostgreSQLContainer<>(containerVersion)
        .withUsername(containerUsername)
        .withPassword(containerPassword);

    container.start();

    log.info("PostgreSQL container started on port: {}",
        container.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT));
    return container;
  }

  @Bean
  public JwtDecoder jwtDecoder() {
    String secretKey = "Q29uZ3JhdHVsYXRpb25zISBZb3UndmUgZ2VuZXJhdGVkIGEgc2VjdXJlIGtleSE=";
    return NimbusJwtDecoder.withSecretKey(new SecretKeySpec(secretKey.getBytes(), "HMACSHA256")).build();
  }

}
