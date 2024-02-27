package models;

import java.time.ZonedDateTime;

public class Organization {
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.ZonedDateTime creationDate;
    private Integer annualTurnover;
    private Integer employeesCount;
    private OrganizationType type;
    private Address officialAddress;

    public void setId() {
        this.id = generateID();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate() {
        this.creationDate = generateDateTime();
    }

    public void setAnnualTurnover(Integer annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setEmployeesCount(Integer employeesCount) {
        this.employeesCount = employeesCount;
    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public Organization(String name, Coordinates coordinates, Integer annualTurnover, Integer employeesCount, OrganizationType organizationType, Address officialAddress){
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(organizationType);
        setOfficialAddress(officialAddress);
    }

    public Organization(String name, Coordinates coordinates, Integer employeesCount, OrganizationType organizationType, Address officialAddress){
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setEmployeesCount(employeesCount);
        setType(organizationType);
        setOfficialAddress(officialAddress);
    }

    public Organization(String name, Coordinates coordinates, Integer annualTurnover, Integer employeesCount, OrganizationType organizationType){
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(organizationType);
    }

    public Organization(String name, Coordinates coordinates, Integer employeesCount, OrganizationType organizationType) {
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setEmployeesCount(employeesCount);
        setType(organizationType);
    }

    private Integer generateID(){
        return Math.abs(generateDateTime().hashCode() * 150 - 500);
    }

    private ZonedDateTime generateDateTime(){
        return ZonedDateTime.now();
    }
}
