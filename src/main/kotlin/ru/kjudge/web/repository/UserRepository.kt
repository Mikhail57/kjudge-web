package ru.kjudge.web.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.kjudge.web.entity.User

interface UserRepository : MongoRepository<User, String>