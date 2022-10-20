package com.example.fileUploadDB.service;

import com.example.fileUploadDB.model.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;
    Attachment getAttachment(String fileId) throws Exception;
}
