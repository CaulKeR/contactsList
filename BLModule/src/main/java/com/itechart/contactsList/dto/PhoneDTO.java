package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Date;

public class PhoneDTO {

    private Long id;
    private Long contactId;
    private Short countryCode;
    private Integer operatorsCode;
    private Integer phoneNumber;
    private String type;
    private String comment;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deleteDate;

    public PhoneDTO(Long id, Long contactId, Short countryCode, Integer operatorsCode, Integer phoneNumber, String type, String comment) {
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

    public String print() {
        return "Contact id: " + this.contactId + '\n' +
                "Country code: " + this.countryCode + '\n' +
                "Operator's code: " + this.operatorsCode + '\n' +
                "Phone number: " + this.phoneNumber + '\n' +
                "Type: " + this.type + '\n' +
                "Comment: " + this.comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Short getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Short countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getOperatorsCode() {
        return operatorsCode;
    }

    public void setOperatorsCode(Integer operatorsCode) {
        this.operatorsCode = operatorsCode;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
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
