package com.lingua.start.model

import com.fasterxml.jackson.annotation.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Word(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "lessonId", nullable = false)
        var lesson: Lesson? = null,

        @get: NotBlank(message = "Please provide word's title")
        val title: String = "",

        @get: NotBlank(message = "Please provide word's image")
        val imageUrl: String = ""
)