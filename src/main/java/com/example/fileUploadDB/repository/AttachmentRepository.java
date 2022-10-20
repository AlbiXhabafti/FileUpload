package com.example.fileUploadDB.repository;

import com.example.fileUploadDB.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,String> {
}
