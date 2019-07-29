package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class AttachmentDTO {

    private long id;
    private long userId;
    private String title;
    private String type;
    private long size;
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date lastUpdate;

    public AttachmentDTO(long id, long userId, String file, long size, Date lastUpdate, String comment) {
        this.id = id;
        this.userId = userId;
        if (file.contains(".")) {
            String[] nameParts = file.split("\\.");
            this.title = nameParts[0];
            this.type = nameParts[1];
        }
        this.size = size;
        this.lastUpdate = lastUpdate;
        this.comment = comment;
    }

    public AttachmentDTO() {
    }

    public AttachmentDTO(long id, String title, String type, String comment) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.comment = comment;
    }

    public AttachmentDTO(long id, long userId, String file, long size, Date lastUpdate) {
        this.id = id;
        this.userId = userId;
        if (file.contains(".")) {
            String[] nameParts = file.split("\\.");
            this.title = nameParts[0];
            this.type = nameParts[1];
        }
        this.size = size;
        this.lastUpdate = lastUpdate;
    }

    public AttachmentDTO(long id, String file, long userId, String comment) {
        this.id = id;
        this.userId = userId;
        if (file.contains(".")) {
            String[] nameParts = file.split("\\.");
            this.title = nameParts[0];
            this.type = nameParts[1];
        }
        this.comment = comment;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
