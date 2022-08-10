package com.sparta.spartafinalproject.documents;

public class Address {
    private String city;
    private String state;
    private String postcode;
    private String street1;

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString()
    {
        StringBuilder sb= new StringBuilder();
        sb.append(street1);
        sb.append(" ");
        sb.append(city);
        sb.append(" ");
        sb.append(state);
        sb.append(" ");
        sb.append(postcode);
        return sb.toString();
    }

}
