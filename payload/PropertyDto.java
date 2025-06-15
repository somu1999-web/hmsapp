package com.hmsapp_test.payload;

public class PropertyDto {
    private String name;
    private Integer noOfGuest;
    private Integer noOfBedrooms;
    private Integer noOfBathrooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNoOfGuest() {
        return noOfGuest;
    }

    public void setNoOfGuest(Integer noOfGuest) {
        this.noOfGuest = noOfGuest;
    }

    public Integer getNoOfBedrooms() {
        return noOfBedrooms;
    }

    public void setNoOfBedrooms(Integer noOfBedrooms) {
        this.noOfBedrooms = noOfBedrooms;
    }

    public Integer getNoOfBathrooms() {
        return noOfBathrooms;
    }

    public void setNoOfBathrooms(Integer noOfBathrooms) {
        this.noOfBathrooms = noOfBathrooms;
    }
}
