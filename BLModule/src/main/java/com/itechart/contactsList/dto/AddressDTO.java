package com.itechart.contactsList.dto;

public class AddressDTO {

    private long id;
    private String country;
    private String locality;
    private String street;
    private String house;
    private short apartment;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public short getApartment() {
        return apartment;
    }

    public void setApartment(short apartment) {
        this.apartment = apartment;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
