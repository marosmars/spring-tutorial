package com.mars.spring.sample.user

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import org.springframework.data.annotation.Id

class User {

    @Id
    @NotNull
    @Size(max = 64)
    val id: String? = null

    @NotNull
    @Size(max = 64)
    val password: String? = null
}
