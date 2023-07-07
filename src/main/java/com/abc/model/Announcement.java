package com.abc.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Announcement {
    private int id;
    private String title;
    private String keywords;
    private String category;
    private String urgencyLevel;
    private Date publishTime;
    private String publisher;
    private String publishObject;
    private String content;
    private String attachment;
}
