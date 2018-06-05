package ru.kjudge.web.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.rabbit.AsyncRabbitTemplate
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
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

    @Bean
    fun jsonMessageConverter(): MessageConverter = Jackson2JsonMessageConverter(jacksonObjectMapper())

    @Bean
    fun asyncRabbitTemplate(rabbitTemplate: RabbitTemplate) = AsyncRabbitTemplate(rabbitTemplate)

//    @Bean
//    fun rabbitTemplate(converter: MessageConverter) = RabbitTemplate().apply { messageConverter = converter}
}