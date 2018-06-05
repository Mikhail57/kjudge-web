package ru.kjudge.web.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ru.kjudge.common.entity.Limits
import ru.kjudge.common.entity.Message
import ru.kjudge.common.entity.Test
import ru.kjudge.web.RunnerClient
import ru.kjudge.web.entity.UserSolution
import ru.kjudge.web.repository.TaskRepository
import ru.kjudge.web.repository.UserSolutionRepository

@Controller
@RequestMapping("/")
class IndexController {
    private val log: Logger = LoggerFactory.getLogger(IndexController::class.java.name)

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var userSolutionRepository: UserSolutionRepository

    @Autowired
    lateinit var runnerClient: RunnerClient

    @GetMapping("")
    fun index(model: Model): String {
        with(model) {
            addAttribute("tasks", taskRepository.findAll())
//            addAttribute("")
        }
        return "index"
    }

    @GetMapping("task/{id}")
    fun task(@PathVariable id: String, model: Model): String {
        with(model) {
            addAttribute("task", taskRepository.findById(id)
                    .orElseThrow { throw NoSuchElementException("No such task...") })
            addAttribute("solutions", userSolutionRepository.findByUserId("5b16c6164afe81046cc31f8c")
                    .filter { it.taskId == id }
                    .asReversed())
        }
        return "task"
    }

    @PostMapping("task/{id}")
    fun uploadCode(@RequestParam("file") file: MultipartFile, @PathVariable("id") taskId: String,
                   redirectAttributes: RedirectAttributes): String {
        val userId = "5b16c6164afe81046cc31f8c"
        val code = file.bytes.toString(Charsets.UTF_8)
        val _tests = taskRepository.findById(taskId)
                .orElseThrow { throw IllegalArgumentException("Task ID should be valid!") }
                .tests.also(::println)
        val tests = _tests.map { Test(it.test, Limits(1000, 1000, 1000)) }.also { log.info("Tests size is ${it.size}") }
        val message = Message(1, "gcc", code, tests)
        runnerClient.send(message, {
            it ?: throw IllegalArgumentException("Something went wrong...")
            log.info("Results: ${it.results}")
            val results = it.results.mapIndexed { index, result ->
                _tests[index].answer.contentEquals(result.output)
            }
            userSolutionRepository.insert(UserSolution(taskId = taskId, userId = userId, code = code, results = results,
                    statusCode = it.statusCode, status = it.status, userOutputs = it.results.map { it.output }))
        }, {
            log.error("An error occurred during processing user's solution")
            val results = _tests.map { false }
            userSolutionRepository.insert(UserSolution(taskId = taskId, userId = userId, code = code, results = results,
                    status = "Processing error", statusCode = 501))
        })
        return "redirect:/"
    }
}