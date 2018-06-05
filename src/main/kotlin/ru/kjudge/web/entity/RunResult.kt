package ru.kjudge.web.entity

data class RunResult(
        val status: String,
        val statusCode: Int,
        val results: List<LaunchResult>
)