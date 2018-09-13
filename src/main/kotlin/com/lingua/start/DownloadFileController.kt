package com.lingua.start

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class DownloadFileController {
    @Autowired
    lateinit var fileStorage: FileStorage

//    /*
//     * Retrieve Files' Information
//     */
//    @GetMapping("/files")
//    fun getListFiles(model: Model): String {
//        val fileInfos: List<FileInfo> = fileStorage.loadFiles().map{
//            path -> FileInfo(path.getFileName().toString(),
//                MvcUriComponentsBuilder.fromMethodName(DownloadFileController::class.java,
//                        "downloadFile", path.getFileName().toString()).build().toString())
//        }
//                .collect(Collectors.toList())
//
//        model.addAttribute("files", fileInfos)
//        return "multipartfile/listfiles"
//    }

    /*
     * Download Files
     */
    @GetMapping("/files/{filename}")
    fun downloadFile(@PathVariable filename: String): ResponseEntity<Resource> {
        val file = fileStorage.loadFile(filename)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
                .contentLength(file.contentLength())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(file)
    }
}