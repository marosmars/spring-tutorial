package com.mars.spring.sample.user

import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String>
