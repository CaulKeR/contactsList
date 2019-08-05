package com.itechart.contactsList.dto;

public class MessageTemplateDTO {

    private String title;
    private String text;

    public MessageTemplateDTO(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public MessageTemplateDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}