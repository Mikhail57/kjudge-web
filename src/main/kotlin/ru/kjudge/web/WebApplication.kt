package ru.kjudge.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.Bean
import ru.kjudge.web.entity.Limits
import ru.kjudge.web.entity.Message
import ru.kjudge.web.entity.Test


@SpringBootApplication
class WebApplication {
    private val log: Logger = LoggerFactory.getLogger(WebApplication::class.java.name)

    @Autowired
    lateinit var client: RunnerClient

    @Bean
    fun runner() = CommandLineRunner {
        log.info("I'm here!!!")
        val code = """
            #include <stdio.h>
            int main() {
                printf("Lol, kek, cheburek");
                return 0;
            }
        """.trimIndent()
        val message = Message(1, "gcc", code, listOf(Test("lol", Limits(1000, 1000, 1000))))
        client.send(message)
    }
}

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}