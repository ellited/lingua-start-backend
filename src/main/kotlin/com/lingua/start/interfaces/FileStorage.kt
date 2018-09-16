package com.lingua.start.interfaces

import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile

interface FileStorage {
    fun init()
    fun store(file: MultipartFile): String
    fun loadFile(filename: String): Resource
    fun removeFile(filename: String)
}