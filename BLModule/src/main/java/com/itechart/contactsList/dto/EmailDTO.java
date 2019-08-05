package com.itechart.contactsList.dto;

public class EmailDTO {

    private String[] emails;
    private String subject;
    private String text;

    public EmailDTO(String[] emails, String subject, String text) {
        this.emails = emails;
        this.subject = subject;
        this.text = text;
    }

    public EmailDTO() {
    }

    public String[] getEmails() {
        return emails;
    }

    public void setEmails(String[] emails) {
        this.emails = emails;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}