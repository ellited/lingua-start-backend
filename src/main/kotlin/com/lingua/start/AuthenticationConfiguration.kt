package com.lingua.start
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import java.util.ArrayList
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import java.util.logging.Logger


@Configuration
class AuthenticationConfiguration: UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {

        if (users.count() == 0) {
            MyUserDetailsService()
        }
        val user = users.stream()
                .filter { u -> u.name == username }
                .findAny()
        if (!user.isPresent) {
            throw UsernameNotFoundException("User not found by name: $username")
        }
        return toUserDetails(user.get())
    }

    private val users = ArrayList<UserObject>()

    fun MyUserDetailsService() {
        //in a real application, instead of using local data,
        // we will find user details by some other means e.g. from an external system

        val pass1 = "{bcrypt}\$2a\$10\$0H0DahrWqrwOc/7wklY1EeMJQyB8XVel6PZ6.WilQQZLxKdy7675q"
        val pass2 = "{bcrypt}\$2a\$10\$QzwRfpohuJhOCG0SV9acm.QSz3WYSFWJh9CYbOEOoYhDcwuSMvXue"
        users.add(UserObject("erin", pass1, "read"))
        users.add(UserObject("mike", pass2, "read"))
    }

    private fun toUserDetails(userObject: UserObject): UserDetails {
        Logger.getLogger("Password").warning( passwordEncoder().encode("234"))
        return User
                .withUsername(userObject.name)
                .password(userObject.password)
                .roles(userObject.role)
                .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }


//    @Bean
//    internal fun userDetailsService(): UserDetailsService {
//        val greg = User.withDefaultPasswordEncoder()
//                .username("erin")
//                .password("123")
//                .roles("read")
//                .build()
//        return InMemoryUserDetailsManager(greg)
//    }
}

class UserObject(val name: String, val password: String, val role: String)