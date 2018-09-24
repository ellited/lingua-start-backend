package com.lingua.start.controllers

import com.lingua.start.models.Word
import com.lingua.start.repositories.WordRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class WordController(private val repository: WordRepository) {

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/words")
    fun getAllWords(): List<Word> = repository.findAll()

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/words")
    fun createNewWord(@Valid @RequestBody word: Word): Word? = repository.save(word)

    @PreAuthorize("#oauth2.hasScope('read')")
    @GetMapping("/words/{id}")
    fun getWordById(@PathVariable(value = "id") wordId: Long): ResponseEntity<Word> {
        return repository.findById(wordId).map { word ->
            ResponseEntity.ok(word)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PutMapping("/words/{id}")
    fun updateWordById(@PathVariable(value = "id") wordId: Long,
                         @Valid @RequestBody newWord: Word): ResponseEntity<Word> {

        return repository.findById(wordId).map { existingWord ->
            val updatedWord: Word = existingWord
                    .copy(title = newWord.title, imageUrl = newWord.imageUrl)
            ResponseEntity.ok().body(repository.save(updatedWord))
        }.orElse(ResponseEntity.notFound().build())
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/words/{id}")
    fun deleteWordById(@PathVariable(value = "id") wordId: Long): ResponseEntity<Void> {

        return repository.findById(wordId).map { word  ->
            repository.delete(word)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}