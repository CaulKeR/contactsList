package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class AttachmentDTO {

    private Long id;
    private Long userId;
    private String title;
    private String type;
    private Long size;
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "Europe/Minsk")
    private Date lastUpdate;

    public AttachmentDTO(Long id, Long userId, String file, Long size, Date lastUpdate, String comment) {
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

    public AttachmentDTO(Long id, String title, String type, String comment) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.comment = comment;
    }

    public AttachmentDTO(Long id, Long userId, String file, Long size, Date lastUpdate) {
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

    public AttachmentDTO(Long id, String file, Long userId, String comment) {
        this.id = id;
        this.userId = userId;
        if (file.contains(".")) {
            String[] nameParts = file.split("\\.");
            this.title = nameParts[0];
            this.type = nameParts[1];
        }
        this.comment = comment;
    }

    public String print() {
        return "Title: " + this.title + '\n' +
                "Type: " + this.type + '\n' +
                "Comment" + this.comment;
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

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}