package ru.kjudge.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import ru.kjudge.web.entity.Message
import ru.kjudge.web.entity.RunResult


@Service
class RunnerClient {
    private val log: Logger = LoggerFactory.getLogger(RunnerClient::class.java.name)

    @Autowired
    lateinit var template: RabbitTemplate

    @Autowired
    lateinit var exchange: DirectExchange

    fun send(message: Message) {
        val result = template.convertSendAndReceive(exchange.name, "rpc", message) as RunResult
        log.info("Result from running server: $result")
    }

}