package com.itechart.contactsList.dto;

public class AddressDTO {

    private Long id;
    private String country;
    private String locality;
    private String street;
    private String house;
    private Short apartment;
    private String postcode;

    public AddressDTO(){}

    public AddressDTO(Long id, String country, String locality, String street, String house, Short apartment, String postcode) {
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

    public String print() {
        return "Country: " + this.country + '\n' +
                "Locality: " + this.locality + '\n' +
                "Street: " + this.street + '\n' +
                "House: " + this.house + '\n' +
                "Apartment: " + this.apartment + '\n' +
                "Postcode: " + this.postcode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Short getApartment() {
        return apartment;
    }

    public void setApartment(Short apartment) {
        this.apartment = apartment;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
