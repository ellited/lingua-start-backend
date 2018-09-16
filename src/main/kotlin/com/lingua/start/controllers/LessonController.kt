package com.lingua.start.controllers

import com.lingua.start.models.Lesson
import com.lingua.start.repositories.LessonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class LessonController(private val repository: LessonRepository) {

    @GetMapping("/lessons")
    fun getAllLessons(): List<Lesson> =
            repository.findAll()


    @PostMapping("/lessons")
    fun createNewLesson(@Valid @RequestBody lesson: Lesson): Lesson =
            repository.save(lesson)


    @GetMapping("/lessons/{id}")
    fun getLessonById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Lesson> {
        return repository.findById(lessonId).map { lesson ->
            ResponseEntity.ok(lesson)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/lessons/{id}")
    fun updateLessonById(@PathVariable(value = "id") lessonId: Long,
                          @Valid @RequestBody newLesson: Lesson): ResponseEntity<Lesson> {

        return repository.findById(lessonId).map { existingLesson ->
            val updatedLesson: Lesson = existingLesson
                    .copy(title = newLesson.title, imageUrl = newLesson.imageUrl)
            ResponseEntity.ok().body(repository.save(updatedLesson))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/lessons/{id}")
    fun deleteLessonById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Void> {

        return repository.findById(lessonId).map { lesson  ->
            repository.delete(lesson)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}