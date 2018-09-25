package com.lingua.start.repositories

import com.lingua.start.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserModel, Long> {
    fun findByUsername(username: String): UserModel
}