package com.lingua.start.controllers

import com.lingua.start.models.UserModel
import com.lingua.start.repositories.UserRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class UserController(private val repository: UserRepository) {

    @PreAuthorize("#oauth2.hasScope('write')")
    @GetMapping("/users")
    fun getAllUsers(): List<UserModel> = repository.findAll()


    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/users")
    fun createNewUser(@Valid @RequestBody user: UserModel): UserModel = repository.save(user)
}