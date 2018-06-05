package ru.kjudge.common.entity

data class Message(
        val runId: Long,
        val compilerName: String,
        val code: String,
        val tests: List<Test>
)