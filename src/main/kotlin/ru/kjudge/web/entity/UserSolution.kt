package ru.kjudge.web.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserSolution(
        @Id
        var id: String? = null,
        var taskId: String = "",
        var userId: String = "",
        var code: String = "",
        var status: String = "",
        var statusCode: Int = 0,
        var userOutputs: List<String> = emptyList(),
        var results: List<Boolean> = mutableListOf()
)