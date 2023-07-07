package com.abc.service;

import com.abc.model.Announcement;
import com.abc.util.Result;

public interface AnnouncementService {
    Result<Announcement> createAnnouncement(Announcement announcement) throws Exception;
    Result<Announcement> getAnnouncementByTitle(String title);

}
