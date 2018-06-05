package ru.kjudge.web

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import ru.kjudge.web.entity.Task
import ru.kjudge.web.repository.TaskRepository

@RunWith(SpringRunner::class)
@SpringBootTest
class RepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Test
    fun `Task repository insertion`() {
        taskRepository.deleteAll()

        taskRepository.insert(
                Task(title = "Первая задача", description = "Одна из самых важный задач в вашей жизни!",
                        cost = 15.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("test1", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test2", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test3", "lol, kek, cheburek")
                )))
        taskRepository.insert(
                Task(title = "Вторая задача", description = "Что ты еще тут ожидал увидеть? Ты что, упоролся?",
                        cost = 15.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("test1", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test2", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test3", "lol, kek, cheburek")
                )))
        taskRepository.insert(
                Task(title = "Третья задача", description = "Да, опять лолы, кеки и чебуреки. Тебе не надоело еще смотреть на описание?",
                        cost = 15.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("test1", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test2", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("test3", "lol, kek, cheburek")
                )))

        assert(taskRepository.count() == 3L)
        taskRepository.findAll().forEach(::println)
    }
}