package ru.kjudge.web.config

import org.springframework.amqp.core.DirectExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.kjudge.web.RunnerClient

@Configuration
class RabbitConfig {
    @Bean
    fun exchange(): DirectExchange {
        return DirectExchange("kjudge.runner")
    }

    @Bean
    fun client() = RunnerClient()
}