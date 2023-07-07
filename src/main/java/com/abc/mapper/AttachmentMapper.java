package com.abc.mapper;

import com.abc.model.Attachment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttachmentMapper {
    int createAttachment(Attachment attachment);
}
