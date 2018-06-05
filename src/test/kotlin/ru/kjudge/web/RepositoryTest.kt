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
                Task(title = "Без лола и кека, нет...", description = "Нужно вывести фразу 'lol, kek, cheburek' (без кавычек)",
                        cost = 1.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("", "lol, kek, cheburek"),
                        ru.kjudge.web.entity.Test("", "lol, kek, cheburek")
                )))
        taskRepository.insert(
                Task(title = "Сложение", description = "На вход подается 2 целых числа (x, y), такие что, -10000 <= x,y <= 10000.<br> Необходимо найти их сумму и вывести ее.",
                        cost = 5.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("100 -1000", "-900"),
                        ru.kjudge.web.entity.Test("100 2000", "2100"),
                        ru.kjudge.web.entity.Test("100 1", "101"),
                        ru.kjudge.web.entity.Test("-200 1", "-199")
                )))
        taskRepository.insert(
                Task(title = "Повторюша", description = "Тебе предстоит познать дзен (вывести целочисленное значение x, поданное на вход; -1000 <= x <= 1000)",
                        cost = 2.0, tests = mutableListOf(
                        ru.kjudge.web.entity.Test("-1000", "-1000"),
                        ru.kjudge.web.entity.Test("100", "100"),
                        ru.kjudge.web.entity.Test("1000", "1000"),
                        ru.kjudge.web.entity.Test("-40", "-40")
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