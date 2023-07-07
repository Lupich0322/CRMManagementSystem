package com.abc.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Announcement {
    private Integer id;
    @NotEmpty(message = "公告标题不能为空！")
    private String title;
    private String keywords;
    private String category;
    private Integer urgencyLevel;
    private LocalDateTime publishTime;
    private Integer publisherId;
    private Integer targetId;
    private String content;
    private String publishStrategy; // 发布策略
    private String status; // 公告状态
    private List<Attachment> attachments;
}
