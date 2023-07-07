package com.abc.service;

import com.abc.model.Announcement;
import com.abc.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface AnnouncementService {
    Result<Announcement> createAnnouncement(Announcement announcement) throws Exception;
    Result<Announcement> editAnnouncementContent(Integer id, String content);
    Result<Announcement> publishAnnouncement(Integer id);
    Result<Announcement> saveAnnouncementAsDraft(Announcement announcement);
    Result<Announcement> getAnnouncementByTitle(String title);
}


//创建公告 --> 发布公告 --> 公告的增删改查
//       |
//       |--> 作为草稿 --> 发布公告 --> 公告增删改查