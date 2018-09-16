package com.lingua.start.models

import com.fasterxml.jackson.annotation.JsonManagedReference
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Lesson(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @get: NotBlank(message = "Please provide lesson's title")
        val title: String = "",

        @get: NotBlank(message = "Please provide lesson's image")
        val imageUrl: String = "",

        @JsonManagedReference
        @OneToMany(mappedBy = "lesson", cascade = [CascadeType.REMOVE], fetch = FetchType.EAGER, orphanRemoval = true)
        var words: List<Word>? = null
)

