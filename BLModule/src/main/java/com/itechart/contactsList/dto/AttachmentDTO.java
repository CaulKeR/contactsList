package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class AttachmentDTO {

    private long userId;
    private String title;
    private String type;
    private long size;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastUpdate;

    public AttachmentDTO(String title, String type, long size, Date lastUpdate) {
        this.title = title;
        this.type = type;
        this.size = size;
        this.lastUpdate = lastUpdate;
    }

    public AttachmentDTO(long userId, String title, String type, long size, Date lastUpdate) {
        this.userId = userId;
        this.title = title;
        this.type = type;
        this.size = size;
        this.lastUpdate = lastUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
