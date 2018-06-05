package ru.kjudge.web.entity

data class Message(
        val runId: Long,
        val compilerName: String,
        val code: String,
        val tests: List<Test>
)