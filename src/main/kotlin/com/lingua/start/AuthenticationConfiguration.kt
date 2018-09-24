package com.lingua.start

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@Configuration
class AuthenticationConfiguration {

    @Bean
    internal fun userDetailsService(): UserDetailsService {
        val greg = User.withDefaultPasswordEncoder()
                .username("greg")
                .password("turnquist")
                .roles("read")
                .build()
        return InMemoryUserDetailsManager(greg)
    }
}
