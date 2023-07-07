package com.abc.mapper;



import com.abc.model.Announcement;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnnouncementMapper {
    int createAnnouncement(Announcement announcement);

    Announcement getByTitle(String title);
}
