package com.abc.mapper;



import com.abc.model.Announcement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {
    int createAnnouncement(Announcement announcement);
    int updateAnnouncementStatus(Integer id, String status);
    int updateAnnouncementContent(Integer id, String content);
    Announcement getByTitle(String title);
}
