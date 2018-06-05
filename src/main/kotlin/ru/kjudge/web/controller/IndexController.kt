package ru.kjudge.web.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.kjudge.web.repository.TaskRepository
import ru.kjudge.web.repository.UserSolutionRepository

@Controller
@RequestMapping("/")
class IndexController {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var userSolutionRepository: UserSolutionRepository

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
            addAttribute("solutions", userSolutionRepository)
        }
        return "task"
    }
}