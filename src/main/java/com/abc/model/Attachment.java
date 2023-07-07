package com.abc.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Attachment {
    private Integer id;
    private Integer announcementId;
    private String fileType;
    private byte[] fileData;
}