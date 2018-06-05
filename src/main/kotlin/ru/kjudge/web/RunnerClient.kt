package ru.kjudge.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.rabbit.AsyncRabbitTemplate
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.kjudge.common.entity.Message
import ru.kjudge.common.entity.RunResult


@Service
class RunnerClient {
    private val log: Logger = LoggerFactory.getLogger(RunnerClient::class.java.name)

    @Autowired
    lateinit var template: AsyncRabbitTemplate

    @Autowired
    lateinit var exchange: DirectExchange

    fun send(message: Message, success: (RunResult?) -> Unit, error: (Throwable) -> Unit) {
        val promise = template.convertSendAndReceive<RunResult>(exchange.name, "rpc", message)
        promise.addCallback(success, error)
    }

}