package com.lingua.start.services

import com.lingua.start.interfaces.FileStorage
import java.nio.file.Files
import java.nio.file.Paths

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class FileStorageService: FileStorage {

    val rootLocation = Paths.get("filestorage")

    override fun init(){
        if (!Files.exists(rootLocation)) {
            Files.createDirectory(rootLocation)
        }
    }

    override fun store(file: MultipartFile): String {
        val uuid = UUID.randomUUID().toString()
        val extension =  file.originalFilename!!.substringAfterLast('.', "")
        val name = "$uuid.$extension"
        Files.copy(file.inputStream, this.rootLocation.resolve(name))
        return name
    }

    override fun loadFile(filename: String): Resource{
        val file = rootLocation.resolve(filename)
        val resource = UrlResource(file.toUri())

        if(resource.exists() || resource.isReadable) {
            return resource
        }else{
            throw RuntimeException("FAIL!")
        }
    }

    override fun removeFile(filename: String) {
        val file = rootLocation.resolve(filename)
        Files.deleteIfExists(file)
    }
}