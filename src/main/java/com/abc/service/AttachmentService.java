package com.abc.service;

import com.abc.model.Attachment;
import com.abc.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface AttachmentService {
    Result<Attachment> createAttachment(Attachment attachment);
}
