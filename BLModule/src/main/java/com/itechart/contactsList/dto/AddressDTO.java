package com.itechart.contactsList.dto;

import com.fasterxml.jackson.annotation.*;

public class AddressDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("country")
    private String country;

    @JsonProperty("locality")
    private String locality;

    @JsonProperty("street")
    private String street;

    @JsonProperty("house")
    private String house;

    @JsonProperty("apartment")
    private short apartment;

    @JsonProperty("postcode")
    private String postcode;

    public AddressDTO(){}

    public AddressDTO(long id, String country, String locality, String street, String house, short apartment, String postcode) {
        this.id = id;
        this.country = country;
        this.locality = locality;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.postcode = postcode;
    }

    public AddressDTO(String country, String locality, String street, String house, short apartment, String postcode) {
        this.country = country;
        this.locality = locality;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
        this.postcode = postcode;
    }

    @JsonGetter("id")
    public long getId() {
        return id;
    }

    @JsonSetter("id")
    public void setId(long id) {
        this.id = id;
    }

    @JsonGetter("country")
    public String getCountry() {
        return country;
    }

    @JsonSetter("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonGetter("locality")
    public String getLocality() {
        return locality;
    }

    @JsonSetter("locality")
    public void setLocality(String locality) {
        this.locality = locality;
    }

    @JsonGetter("street")
    public String getStreet() {
        return street;
    }

    @JsonSetter("street")
    public void setStreet(String street) {
        this.street = street;
    }

    @JsonGetter("house")
    public String getHouse() {
        return house;
    }

    @JsonSetter("house")
    public void setHouse(String house) {
        this.house = house;
    }

    @JsonGetter("apartment")
    public short getApartment() {
        return apartment;
    }

    @JsonSetter("apartment")
    public void setApartment(short apartment) {
        this.apartment = apartment;
    }

    @JsonGetter("postcode")
    public String getPostcode() {
        return postcode;
    }

    @JsonSetter("postcode")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
