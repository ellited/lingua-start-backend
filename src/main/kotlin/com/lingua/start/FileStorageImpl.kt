package com.lingua.start

import java.nio.file.Files
import java.nio.file.Paths

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class FileStorageImpl: FileStorage {

    val rootLocation = Paths.get("filestorage")

    override fun init(){
        print(rootLocation)
        Files.createDirectory(rootLocation)
    }

    override fun store(file: MultipartFile){
        Files.copy(file.inputStream, this.rootLocation.resolve(file.originalFilename))
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
}