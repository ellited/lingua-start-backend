package com.lingua.start.controllers

import com.lingua.start.interfaces.FileStorage
import com.lingua.start.models.File
import com.lingua.start.repositories.FileRepository
import com.lingua.start.repositories.WordRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class FileController(private val repository: FileRepository, private val wordRepository: WordRepository) {

    @Autowired
    lateinit var fileStorage: FileStorage

    @PreAuthorize("#oauth2.hasAnyScopeMatching('read', 'write')")
    @GetMapping("/files/{filename}")
    fun downloadFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = fileStorage.loadFile(filename)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file)
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @PostMapping("/files")
    fun createNewFile(@Valid
                      @RequestParam("uploadfile") file: MultipartFile,
                      @RequestParam("wordId") wordId: Long): ResponseEntity<File> {

        fileStorage.init()
        val name = fileStorage.store(file)
        val fileModel = File()
        fileModel.name = name
        fileModel.word = wordRepository.findById(wordId).get()
        return ResponseEntity.ok().body(repository.save(fileModel))
    }

    @PreAuthorize("#oauth2.hasScope('write')")
    @DeleteMapping("/files/{id}")
    fun deleteFileById(@PathVariable(value = "id") id: Long): ResponseEntity<Void> {

        return repository.findById(id).map { file  ->
            fileStorage.removeFile(file.name)
            repository.delete(file)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}