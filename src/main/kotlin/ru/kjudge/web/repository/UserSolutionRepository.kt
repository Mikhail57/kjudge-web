package ru.kjudge.web.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.kjudge.web.entity.UserSolution

interface UserSolutionRepository : MongoRepository<UserSolution, String> {

    fun findByUserId(userId: String): List<UserSolution>
    fun findByTaskId(taskId: String): List<UserSolution>

}