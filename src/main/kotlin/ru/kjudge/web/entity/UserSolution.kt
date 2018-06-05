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
        var results: List<Boolean> = mutableListOf()
)