package com.lingua.start.model

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
        @OneToMany(mappedBy = "lesson", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        var words: List<Word>? = null
)

