package com.abc.service.impl;

import com.abc.mapper.AnnouncementMapper;
import com.abc.model.Announcement;
import com.abc.service.AnnouncementService;
import com.abc.util.ClassExamine;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;

    @Override
    public Result<Announcement> createAnnouncement(Announcement newAnnouncement) throws Exception {
        Result<Announcement> result = new Result<>();
        Announcement existingAnnouncement = announcementMapper.getByTitle(newAnnouncement.getTitle());
        if (existingAnnouncement != null) {
            ClassExamine.objectOverlap(newAnnouncement, existingAnnouncement);
            result.setResultFailed("公告已存在！");
            return result;
        }
        announcementMapper.createAnnouncement(newAnnouncement);

        result.setResultSuccess("创建公告成功！", newAnnouncement);
        return result;
    }

    @Override
    public Result<Announcement> getAnnouncementByTitle(String title){
        Result<Announcement> result = new Result<>();
        // 去数据库查找
        Announcement announcement = announcementMapper.getByTitle(title);
        if (announcement == null) {
            result.setResultFailed("公告不存在！");
            return result;
        }
        result.setResultSuccess("查询成功！", announcement);
        return result;
    }

    @Override
    public Result<Announcement> publishAnnouncement(Integer id) {
        Result<Announcement> result = new Result<>();
        int rows = announcementMapper.updateAnnouncementStatus(id, "published");
        if (rows > 0) {
            result.setResultSuccess("公告发布成功！");
        } else {
            result.setResultFailed("公告发布失败！");
        }
        return result;
    }

    @Override
    public Result<Announcement> saveAnnouncementAsDraft(Announcement announcement) {
        Result<Announcement> result = new Result<>();
        announcement.setStatus("draft");
        int rows = announcementMapper.createAnnouncement(announcement);
        if (rows > 0) {
            result.setResultSuccess("公告保存为草稿成功！", announcement);
        } else {
            result.setResultFailed("公告保存为草稿失败！");
        }
        return result;
    }

    @Override
    public Result<Announcement> editAnnouncementContent(Integer id, String content) {
        Result<Announcement> result = new Result<>();
        int rows = announcementMapper.updateAnnouncementContent(id, content);
        if (rows > 0) {
            result.setResultSuccess("编辑公告内容成功！");
        } else {
            result.setResultFailed("编辑公告内容失败！");
        }
        return result;
    }
}
