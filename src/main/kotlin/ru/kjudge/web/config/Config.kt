package ru.kjudge.web.config

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["ru.kjudge.web.repository"])
class Config