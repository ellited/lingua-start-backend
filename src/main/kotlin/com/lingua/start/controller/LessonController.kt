package com.lingua.start.controller

import com.lingua.start.model.Lesson
import com.lingua.start.repository.LessonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class LessonController(private val lessonRepository: LessonRepository) {

    @GetMapping("/lessons")
    fun getAllArticles(): List<Lesson> =
            lessonRepository.findAll()


    @PostMapping("/lessons")
    fun createNewArticle(@Valid @RequestBody lesson: Lesson): Lesson =
            lessonRepository.save(lesson)


    @GetMapping("/lessons/{id}")
    fun getLessonById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Lesson> {
        return lessonRepository.findById(lessonId).map { lesson ->
            ResponseEntity.ok(lesson)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/lessons/{id}")
    fun updateLessonById(@PathVariable(value = "id") lessonId: Long,
                          @Valid @RequestBody newLesson: Lesson): ResponseEntity<Lesson> {

        return lessonRepository.findById(lessonId).map { existingLesson ->
            val updatedLesson: Lesson = existingLesson
                    .copy(title = newLesson.title, imageUrl = newLesson.imageUrl)
            ResponseEntity.ok().body(lessonRepository.save(updatedLesson))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/lessons/{id}")
    fun deleteArticleById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Void> {

        return lessonRepository.findById(lessonId).map { lesson  ->
            lessonRepository.delete(lesson)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}