package com.mars.spring.sample.user

interface UserService {

    fun save(user: User): User
    val all: List<User>

}