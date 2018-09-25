package com.lingua.start.controllers

import com.lingua.start.models.Lesson
import com.lingua.start.repositories.LessonRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class LessonController(private val repository: LessonRepository) {

    @PreAuthorize("#oauth2.hasAnyScopeMatching('read', 'write')")
    @GetMapping("/lessons")
    fun getAllLessons(): List<Lesson> =
            repository.findAll()


    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/lessons")
    fun createNewLesson(@Valid @RequestBody lesson: Lesson): Lesson =
            repository.save(lesson)


    @PreAuthorize("#oauth2.hasAnyScopeMatching('read', 'write')")
    @GetMapping("/lessons/{id}")
    fun getLessonById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Lesson> {
        return repository.findById(lessonId).map { lesson ->
            ResponseEntity.ok(lesson)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PutMapping("/lessons/{id}")
    fun updateLessonById(@PathVariable(value = "id") lessonId: Long,
                          @Valid @RequestBody newLesson: Lesson): ResponseEntity<Lesson> {

        return repository.findById(lessonId).map { existingLesson ->
            val updatedLesson: Lesson = existingLesson
                    .copy(title = newLesson.title, imageUrl = newLesson.imageUrl)
            ResponseEntity.ok().body(repository.save(updatedLesson))
        }.orElse(ResponseEntity.notFound().build())

    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/lessons/{id}")
    fun deleteLessonById(@PathVariable(value = "id") lessonId: Long): ResponseEntity<Void> {

        return repository.findById(lessonId).map { lesson  ->
            repository.delete(lesson)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}