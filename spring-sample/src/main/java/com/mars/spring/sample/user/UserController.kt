package com.mars.spring.sample.user

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.inject.Inject
import javax.validation.Valid

@RestController
class UserController @Inject constructor(private val userService: UserService) {


    @RequestMapping(value = "/user", method = arrayOf(RequestMethod.POST))
    fun createUser(@RequestBody @Valid user: User): User {
        return userService.save(user)
    }

    val all: List<User>
        @RequestMapping(value = "/users", method = arrayOf(RequestMethod.GET))
        get() = userService.all

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    fun handleUserAlreadyExistsException(e: UserServiceImpl.UserAlreadyExistsException): String {
        return e.message!!
    }

}
