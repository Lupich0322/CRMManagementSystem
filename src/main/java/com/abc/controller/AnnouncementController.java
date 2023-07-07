package com.abc.controller;

import com.abc.model.Announcement;
import com.abc.model.Attachment;
import com.abc.service.AnnouncementService;
import com.abc.service.AttachmentService;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;
    @Autowired
    private AttachmentService attachmentService;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @PostMapping("/create")
    public Result<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Result<Announcement> result;
        try {
            result = announcementService.createAnnouncement(announcement); // 获取服务层传递上来的结果
        } catch (Exception e) {
            result = new Result<>();
            result.setResultFailed("未能成功创建公告: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/publish/{id}")
    public Result<Announcement> publishAnnouncement(@PathVariable Integer id) {
        Result<Announcement> result;
        try {
            result = announcementService.publishAnnouncement(id);
        } catch (Exception e) {
            result = new Result<>();
            result.setResultFailed("未能成功发布公告: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/saveAsDraft")
    public Result<Announcement> saveAnnouncementAsDraft(@RequestBody Announcement announcement) {
        Result<Announcement> result;
        try {
            result = announcementService.saveAnnouncementAsDraft(announcement);
        } catch (Exception e) {
            result = new Result<>();
            result.setResultFailed("未能成功保存公告为草稿: " + e.getMessage());
        }
        return result;
    }

    @GetMapping("/getByTitle/{title}")
    public Result<Announcement> getAnnouncementByTitle(@PathVariable("title") String title){
        Result<Announcement> result = new Result<>();
        try {
            result = announcementService.getAnnouncementByTitle(title);
        } catch (Exception e) {
            result.setResultFailed("未能成功获取公告: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/editContent/{id}")
    public Result<Announcement> editAnnouncementContent(@PathVariable("id") Integer id, @RequestBody String content) {
        Result<Announcement> result;
        try {
            result = announcementService.editAnnouncementContent(id, content);
        } catch (Exception e) {
            result = new Result<>();
            result.setResultFailed("未能成功编辑公告内容: " + e.getMessage());
        }
        return result;
    }

    @PostMapping("/uploadAttachment/{announcementId}")
    public Result<Attachment> uploadAttachment(@PathVariable Integer announcementId, @RequestParam("file") MultipartFile file) {
        Result<Attachment> result;
        try {
            // 创建文件在服务器上的路径
            File uploadFile = new File(uploadDir, file.getOriginalFilename());
            // 将上传的文件保存到服务器
            file.transferTo(uploadFile);

            Attachment attachment = new Attachment();
            attachment.setAnnouncementId(announcementId);
            attachment.setFileType(file.getContentType());
            // 保存文件的路径，而不是文件的数据
            attachment.setFilePath(uploadFile.getAbsolutePath());

            result = attachmentService.createAttachment(attachment);
        } catch (Exception e) {
            result = new Result<>();
            result.setResultFailed("未能成功上传附件: " + e.getMessage());
        }
        return result;
    }


}
