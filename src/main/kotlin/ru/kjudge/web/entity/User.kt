package ru.kjudge.web.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class User(
        @Id
        var id: String? = null,
        var roles: List<String> = mutableListOf(),
        var displayName: String = "",
        var firstName: String = "",
        var lastName: String = "",
        var middleName: String = "",
        var group: String = ""
)