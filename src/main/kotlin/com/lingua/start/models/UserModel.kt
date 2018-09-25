package com.lingua.start.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity
data class UserModel(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank(message = "Please provide user's title")
        val username: String = "",

        @get: NotBlank(message = "Please provide user's password")
        val password: String = "",

        @get: NotBlank(message = "Please provide user's roles")
        val roles: String = ""
)