package ru.kjudge.web

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import ru.kjudge.web.entity.Task
import ru.kjudge.web.entity.User
import ru.kjudge.web.entity.UserSolution
import ru.kjudge.web.repository.TaskRepository
import ru.kjudge.web.repository.UserRepository
import ru.kjudge.web.repository.UserSolutionRepository

@RunWith(SpringRunner::class)
@SpringBootTest
class RepositoryTest {

    @Autowired
    lateinit var taskRepository: TaskRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userSolutionRepository: UserSolutionRepository

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

    @Test
    fun `Users repository insertion`() {
        userRepository.deleteAll()

        userRepository.insert(User(roles = listOf("admin", "user"), displayName = "Mikhail", firstName = "Mikhail",
                lastName = "Mustakimov", middleName = "Fidailovich", group = "14121-ДБ",
                email = "mikhail@mustakimov.ru", passwordHash = "Testing"))

        assert(userRepository.count() == 1L)
        userRepository.findAll().forEach(::println)
    }

    @Test
    fun `UserSolution repository insertion`() {
        userSolutionRepository.deleteAll()

        val code = "test".trimIndent()

        userSolutionRepository.insert(UserSolution(taskId = "5b16c6174afe81046cc31f8d",
                userId = "5b16c6164afe81046cc31f8c", code = code, results = listOf(true, false, true)))

        assert(userSolutionRepository.count() == 1L)
        userSolutionRepository.findAll().forEach(::println)

        userSolutionRepository.deleteAll()
        assert(userSolutionRepository.count() == 0L)
    }
}