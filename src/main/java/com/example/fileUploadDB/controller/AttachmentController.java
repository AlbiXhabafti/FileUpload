package com.example.fileUploadDB.controller;

import com.example.fileUploadDB.model.Attachment;
import com.example.fileUploadDB.model.ResponseData;
import com.example.fileUploadDB.service.AttachmentService;
import com.example.fileUploadDB.service.AttachmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.awt.*;


@RestController
@RequestMapping("file")
public class AttachmentController {
    @Autowired
    @Qualifier("attachment")
    // we use @Qualifer because we have 2 class that implements AttachmentService interface
    //added name inside () is the same as name in parentheses in Service
    private AttachmentService attachmentService;
    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment = null;
        String downloadURl = "";

            attachment = attachmentService.saveAttachment(file);
            downloadURl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/download/")
                    .path(attachment.getId())
                    .toUriString();
            return new ResponseData(attachment.getFileName(),
                    downloadURl,
                    file.getContentType(),
                    file.getSize());


    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Attachment attachment = null;
        attachment = attachmentService.getAttachment(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + attachment.getFileName()
                                + "\"")
                .body(new ByteArrayResource(attachment.getData()));
    }



}
