package com.lingua.start

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface FileStorage {
    fun init()
    fun store(file: MultipartFile)
    fun loadFile(filename: String): Resource
}