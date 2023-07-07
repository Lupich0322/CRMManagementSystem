package com.abc.service.impl;

import com.abc.mapper.AnnouncementMapper;
import com.abc.model.Announcement;
import com.abc.model.Employee;
import com.abc.service.AnnouncementService;
import com.abc.util.ClassExamine;
import com.abc.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
}
