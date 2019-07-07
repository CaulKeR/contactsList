package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.*;

import java.sql.Date;

public class ContactDTO {

    private long id;
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

    public void print() {
        System.out.println("Id:" + id);
        System.out.println("First name:" + firstName);
        System.out.println("Surname:" + surname);
        System.out.println("Patronymic:" + patronymic);
        System.out.println("Birth date:" + birthDate);
        System.out.println("Sex:" + sex);
        System.out.println("Nationality:" + nationality);
        System.out.println("Family status:" + familyStatus);
        System.out.println("Website:" + website);
        System.out.println("E-mail:" + email);
        System.out.println("Current workplace:" + currentWorkplace);
        System.out.println("Address:" + address);
    }

    public ContactDTO() {}

    public ContactDTO(long id, String firstName, String surname, String patronymic, Date birthDate, String sex,
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

    public ContactDTO(long id, String firstName, String surname, String patronymic, Date birthDate,
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}