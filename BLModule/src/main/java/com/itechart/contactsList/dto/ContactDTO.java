package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.*;

import java.sql.Date;

public class ContactDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("patronymic")
    private String patronymic;

    @JsonProperty("birthDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date birthDate;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("familyStatus")
    private String familyStatus;

    @JsonProperty("website")
    private String website;

    @JsonProperty("email")
    private String email;

    @JsonProperty("currentWorkplace")
    private String currentWorkplace;

    @JsonProperty("address")
    private AddressDTO address;

    @JsonProperty("deleteDate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date deleteDate;

    public void print() {
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


    @JsonGetter("id")
    public long getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonGetter("surname")
    public String getSurname() {
        return surname;
    }

    @JsonSetter("surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @JsonGetter("patronymic")
    public String getPatronymic() {
        return patronymic;
    }

    @JsonSetter("patronymic")
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @JsonGetter("birthDate")
    public Date getBirthDate() {
        return birthDate;
    }

    @JsonSetter("birthDate")
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @JsonGetter("sex")
    public String getSex() {
        return sex;
    }

    @JsonSetter("sex")
    public void setSex(String sex) {
        this.sex = sex;
    }

    @JsonGetter("nationality")
    public String getNationality() {
        return nationality;
    }

    @JsonSetter("nationality")
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @JsonGetter("familyStatus")
    public String getFamilyStatus() {
        return familyStatus;
    }

    @JsonSetter("familyStatus")
    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    @JsonGetter("website")
    public String getWebsite() {
        return website;
    }

    @JsonSetter("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @JsonGetter("email")
    public String getEmail() {
        return email;
    }

    @JsonSetter("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonGetter("currentWorkplace")
    public String getCurrentWorkplace() {
        return currentWorkplace;
    }

    @JsonSetter("currentWorkplace")
    public void setCurrentWorkplace(String currentWorkplace) {
        this.currentWorkplace = currentWorkplace;
    }

    @JsonGetter("address")
    public AddressDTO getAddress() {
        return address;
    }

    @JsonSetter("address")
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @JsonGetter("deleteDate")
    public Date getDeleteDate() {
        return deleteDate;
    }

    @JsonSetter("deleteDate")
    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }
}