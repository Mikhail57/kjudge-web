package ru.kjudge.web.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.kjudge.web.entity.Task

interface TaskRepository : MongoRepository<Task, String>