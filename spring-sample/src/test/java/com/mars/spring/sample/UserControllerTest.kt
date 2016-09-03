package com.mars.spring.sample

import org.junit.Assert.assertEquals
import org.mockito.Matchers.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

import com.mars.spring.sample.user.User
import com.mars.spring.sample.user.UserController
import com.mars.spring.sample.user.UserService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserControllerTest {

    @Mock
    private val userService: UserService? = null

    private var userController: UserController? = null

    @Before
    fun setUp() {
        userController = UserController(userService!!)
    }

    @Test
    @Throws(Exception::class)
    fun shouldCreateUser() {
        val savedUser = stubServiceToReturnStoredUser()
        val user = User()
        val returnedUser = userController!!.createUser(user)
        // verify user was passed to UserService
        verify<UserService>(userService, Mockito.times(1)).save(user)
        assertEquals("Returned user should come from the service", savedUser, returnedUser)
    }

    private fun stubServiceToReturnStoredUser(): User {
        val user = User()
        `when`(userService!!.save(any(User::class.java))).thenReturn(user)
        return user
    }

}