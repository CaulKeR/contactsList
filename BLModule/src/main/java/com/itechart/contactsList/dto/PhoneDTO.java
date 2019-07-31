package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class PhoneDTO {

    private long id;
    private long contactId;
    private short countryCode;
    private int operatorsCode;
    private int phoneNumber;
    private String type;
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deleteDate;

    public PhoneDTO(long id, long contactId, short countryCode, int operatorsCode, int phoneNumber, String type, String comment) {
        this.id = id;
        this.contactId = contactId;
        this.countryCode = countryCode;
        this.operatorsCode = operatorsCode;
        this.phoneNumber = phoneNumber;
        this.type = type;
        this.comment = comment;
    }

    public PhoneDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public short getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(short countryCode) {
        this.countryCode = countryCode;
    }

    public int getOperatorsCode() {
        return operatorsCode;
    }

    public void setOperatorsCode(int operatorsCode) {
        this.operatorsCode = operatorsCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}
