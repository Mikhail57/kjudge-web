package ru.kjudge.common.entity

data class RunResult(
        val status: String,
        val statusCode: Int,
        val results: List<LaunchResult>
)