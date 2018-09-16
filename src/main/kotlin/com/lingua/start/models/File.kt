package com.lingua.start.models

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class File(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "wordId", nullable = false)
        var word: Word? = null,

        @get: NotBlank(message = "Please provide files's name")
        var name: String = ""
)