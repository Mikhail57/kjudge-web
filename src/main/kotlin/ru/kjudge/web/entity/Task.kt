package ru.kjudge.web.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Task(
        @Id
        var id: String? = null,
        var title: String = "",
        var description: String = "",
        var cost: Double = 0.toDouble(),
        var tests: List<Test> = mutableListOf()
)