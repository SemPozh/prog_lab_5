package models;

public class Address {
    private String zipCode;

    public Address(String zipCode){
        setZipCode(zipCode);
    }

    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
}
