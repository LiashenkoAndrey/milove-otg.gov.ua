package gov.milove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableMongoRepositories(basePackages = {
    "gov.milove.main.repository.mongo",
    "gov.milove.forum.repository.mongo"}
)
@EnableJpaRepositories(basePackages = {
    "gov.milove.main.repository.jpa",
    "gov.milove.forum.repository.jpa"}
)
public class Runner {

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
    }

}
