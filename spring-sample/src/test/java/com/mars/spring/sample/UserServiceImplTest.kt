package com.mars.spring.sample

import org.junit.Assert.assertEquals
import org.mockito.Matchers.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

import com.mars.spring.sample.user.User
import com.mars.spring.sample.user.UserRepository
import com.mars.spring.sample.user.UserService
import com.mars.spring.sample.user.UserServiceImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserServiceImplTest {

    @Mock
    private val userRepository: UserRepository? = null

    private var userService: UserService? = null

    @Before
    fun setUp() {
        userService = UserServiceImpl(userRepository!!)
    }

    @Test
    fun shouldSaveNewUser() {
        val savedUser = stubRepositoryToReturnUserOnSave()
        val user = User()
        val returnedUser = userService!!.save(user)
        // verify repository was called with user
        verify<UserRepository>(userRepository, times(1)).save(user)
        assertEquals("Returned user should come from the repository", savedUser, returnedUser)
    }

    private fun stubRepositoryToReturnUserOnSave(): User {
        val user = User()
        `when`(userRepository!!.save(any(User::class.java))).thenReturn(user)
        return user
    }

}
