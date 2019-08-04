package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.*;
import java.sql.Date;

public class ContactDTO {

    private Long id;
    private String firstName;
    private String surname;
    private String patronymic;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String sex;
    private String nationality;
    private String familyStatus;
    private String website;
    private String email;
    private String currentWorkplace;
    private AddressDTO address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date deleteDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date1;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date2;

    private boolean isCustomAvatar;

    public ContactDTO(Long id, String firstName, String surname, String patronymic, String email) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.email = email;
    }

    public String print() {
        return "Id:" + this.id + '\n' +
                "First name:" + this.firstName + '\n' +
                "Surname:" + this.surname + '\n' +
                "Patronymic:" + this.patronymic + '\n' +
                "Birth date:" + this.birthDate + '\n' +
                "Sex:" + this.sex + '\n' +
                "Nationality:" + this.nationality + '\n' +
                "Family status:" + this.familyStatus + '\n' +
                "Website:" + this.website + '\n' +
                "E-mail:" + this.email + '\n' +
                "Current workplace:" + this.currentWorkplace + '\n' +
                "Address:" + this.address;
    }

    public ContactDTO() {}

    public ContactDTO(String email) {
        this.email = email;
    }

    public ContactDTO(Long id, String firstName, String surname, String patronymic, Date birthDate, String sex,
                      String nationality, String familyStatus, String website, String email, String currentWorkplace,
                      Date deleteDate) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
        this.website = website;
        this.email = email;
        this.currentWorkplace = currentWorkplace;
        this.deleteDate = deleteDate;
    }

    public ContactDTO(Long id, String firstName, String surname, String patronymic, Date birthDate,
                      String currentWorkplace) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.currentWorkplace = currentWorkplace;
    }

    public ContactDTO(String firstName, String surname, String patronymic, Date birthDate, String sex, String nationality,
                      String familyStatus, String website, String email, String currentWorkplace, AddressDTO address) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
        this.website = website;
        this.email = email;
        this.currentWorkplace = currentWorkplace;
        this.address = address;
    }

    public ContactDTO(String firstName, String surname, String patronymic, Date date1, Date date2, String sex,
                      String nationality, String familyStatus, String website, String email, String currentWorkplace,
                      AddressDTO address) {
        this.firstName = firstName;
        this.surname = surname;
        this.patronymic = patronymic;
        this.date1 = date1;
        this.date2 = date2;
        this.sex = sex;
        this.nationality = nationality;
        this.familyStatus = familyStatus;
        this.website = website;
        this.email = email;
        this.currentWorkplace = currentWorkplace;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentWorkplace() {
        return currentWorkplace;
    }

    public void setCurrentWorkplace(String currentWorkplace) {
        this.currentWorkplace = currentWorkplace;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public boolean isCustomAvatar() {
        return isCustomAvatar;
    }

    public void setCustomAvatar(boolean customAvatar) {
        isCustomAvatar = customAvatar;
    }
}