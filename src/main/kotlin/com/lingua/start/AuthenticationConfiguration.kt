package com.lingua.start

import com.lingua.start.models.UserModel
import com.lingua.start.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories


@Configuration
class AuthenticationConfiguration : UserDetailsService {

    @Autowired
    private val userRepository: UserRepository? = null

    override fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository?.findByUsername(username!!)
                ?: throw UsernameNotFoundException("UserModel not found by name: $username")

        return toUserDetails(user)
    }


    private fun toUserDetails(userObject: UserModel): UserDetails {
        return User
                .withUsername(userObject.username)
                .password(userObject.password)
                .roles(userObject.roles)
                .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }
}