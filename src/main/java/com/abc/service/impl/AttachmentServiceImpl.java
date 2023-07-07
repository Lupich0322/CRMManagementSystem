package com.abc.service.impl;

import com.abc.mapper.AttachmentMapper;
import com.abc.model.Attachment;
import com.abc.service.AttachmentService;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public Result<Attachment> createAttachment(Attachment attachment) {
        Result<Attachment> result = new Result<>();
        int rows = attachmentMapper.createAttachment(attachment);
        if (rows > 0) {
            result.setResultSuccess("创建附件成功！", attachment);
        } else {
            result.setResultFailed("创建附件失败！");
        }
        return result;
    }
}
