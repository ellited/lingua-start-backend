package com.lingua.start.controller

import com.lingua.start.model.Word
import com.lingua.start.repository.WordRepository
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class WordController(private val wordRepository: WordRepository) {

    @GetMapping("/words")
    fun getAllWords(): List<Word> =
            wordRepository.findAll()


    @PostMapping("/words")
    fun createNewWord(@Valid @RequestBody word: Word): Word? = wordRepository.save(word)
}