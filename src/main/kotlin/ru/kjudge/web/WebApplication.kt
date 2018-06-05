package ru.kjudge.web

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*


@SpringBootApplication
class WebApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebApplication::class.java, *args)
}