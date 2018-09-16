package com.lingua.start.controller

import com.lingua.start.model.File
import com.lingua.start.repository.FileRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class FileController(private val repository: FileRepository) {

    @GetMapping("/files")
    fun getAllFiles(): List<File> =
            repository.findAll()

    @PostMapping("/files")
    fun createNewFile(@Valid @RequestBody file: File): File =
            repository.save(file)

    @GetMapping("/files/{id}")
    fun getFileById(@PathVariable(value = "id") id: Long): ResponseEntity<File> {
        return repository.findById(id).map { file ->
            ResponseEntity.ok(file)
        }.orElse(ResponseEntity.notFound().build())
    }


    @PutMapping("/files/{id}")
    fun updateFileById(@PathVariable(value = "id") id: Long,
                       @Valid @RequestBody newFile: File): ResponseEntity<File> {

        return repository.findById(id).map { existingFile ->
            val updatedFile: File = existingFile
                    .copy(name = newFile.name)
            ResponseEntity.ok().body(repository.save(updatedFile))
        }.orElse(ResponseEntity.notFound().build())

    }

    @DeleteMapping("/files/{id}")
    fun deleteFileById(@PathVariable(value = "id") id: Long): ResponseEntity<Void> {

        return repository.findById(id).map { file  ->
            repository.delete(file)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}