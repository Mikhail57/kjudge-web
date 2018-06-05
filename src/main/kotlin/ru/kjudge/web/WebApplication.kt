package ru.kjudge.web

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*
import org.springframework.context.annotation.Bean
import ru.kjudge.common.entity.Limits
import ru.kjudge.common.entity.Message
import ru.kjudge.common.entity.Test


@SpringBootApplication
class WebApplication {
    private val log: Logger = LoggerFactory.getLogger(WebApplication::class.java.name)

    @Autowired
    lateinit var client: RunnerClient

//    @Bean
//    fun runner() = CommandLineRunner {
//        for (i in 0..40 step 10) {
//            val code = """
//            #include <iostream>
//            int main() {
//                std::cout << "COUT";
//                std::cerr << "CERR";
//                return 0;
//            }
//            """.trimIndent()
//            val message = Message(i.toLong(), "gcc", code, listOf(Test("lol", Limits(1000, 1000, 1000))))
//            client.send(message)
//        }
//    }
}

fun main(args: Array<String>) {
    runApplication<WebApplication>(*args)
}