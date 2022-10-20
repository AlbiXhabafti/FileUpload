package com.example.fileUploadDB.service;
import org.springframework.util.StringUtils;

import com.example.fileUploadDB.model.Attachment;
import com.example.fileUploadDB.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("attachment")
public class AttachmentServiceImpl implements AttachmentService{
    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence "
                        + fileName);
            }
            Attachment attachment = new Attachment(fileName,
                    file.getContentType(),
                    file.getBytes());
            return attachmentRepository.save(attachment);

        } catch (Exception e) {
            throw new Exception("Could not save File: " + fileName);
        }
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception {
        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
