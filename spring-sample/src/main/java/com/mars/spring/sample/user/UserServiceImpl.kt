package com.mars.spring.sample.user

import javax.inject.Inject
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl
@Inject
constructor(private val repository: UserRepository) : UserService {

    @Transactional
    override fun save(user: User): User {
        val existing = repository.findOne(user.id)
        if (existing != null) {
            throw UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", user.id))
        }
        return repository.save(user)
    }

    override val all: List<User>
        @Transactional
        get() = repository.findAll()

    inner class UserAlreadyExistsException(format: String) : RuntimeException(format)
}