package com.lingua.start.controller

import com.lingua.start.model.Lesson
import com.lingua.start.model.Word
import com.lingua.start.repository.WordRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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

    @GetMapping("/words/{id}")
    fun getWordById(@PathVariable(value = "id") wordId: Long): ResponseEntity<Word> {
        return wordRepository.findById(wordId).map { word ->
            ResponseEntity.ok(word)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/words/{id}")
    fun updateWordById(@PathVariable(value = "id") wordId: Long,
                         @Valid @RequestBody newWord: Word): ResponseEntity<Word> {

        return wordRepository.findById(wordId).map { existingWord ->
            val updatedWord: Word = existingWord
                    .copy(title = newWord.title, imageUrl = newWord.imageUrl)
            ResponseEntity.ok().body(wordRepository.save(updatedWord))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/words/{id}")
    fun deleteWordById(@PathVariable(value = "id") wordId: Long): ResponseEntity<Void> {

        return wordRepository.findById(wordId).map { word  ->
            wordRepository.delete(word)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}