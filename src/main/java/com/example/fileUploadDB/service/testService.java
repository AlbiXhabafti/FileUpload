package com.example.fileUploadDB.service;

import com.example.fileUploadDB.model.Attachment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("test")
public class testService implements AttachmentService {
    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        return null;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return null;
    }
}
