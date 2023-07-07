package com.abc.controller;

import com.abc.model.Announcement;
import com.abc.service.AnnouncementService;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping("/createAnnouncement")
    public Result<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        Result<Announcement> result = new Result<>();
        try {
            announcementService.createAnnouncement(announcement);
            result.setResultSuccess("公告创建成功");
        } catch (Exception e) {
            result.setResultFailed("未能成功创建公告" + e.getMessage());
        }
        return result;
    }

    @GetMapping("/getAnnouncementByTitle/{title}")
    public Result<Announcement> getAnnouncementByTitle(@PathVariable("title") String title){
        Result<Announcement> result = new Result<>();
        try {
            announcementService.getAnnouncementByTitle(title);
            result.setResultSuccess("成功获取公告");
        } catch (Exception e) {
            result.setResultFailed("未能成功创建公告" + e.getMessage());
        }
        return result;
    }

}
