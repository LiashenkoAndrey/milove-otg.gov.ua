package gov.milove.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/*
Configure the base packages for the repositories separately to isolate the forum completely
Allows to exclude the package anytime
 */
@Configuration
@EnableMongoRepositories(basePackages = "gov.milove.forum.repositories.mongo")
@EnableJpaRepositories(basePackages = {"gov.milove.forum.repositories.jpa"})
public class ForumConfig {
}
