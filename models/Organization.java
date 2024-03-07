package models;

import exceptions.InvalidObjectFieldException;

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

    public void setName(String name) throws InvalidObjectFieldException {
        if (!name.isEmpty()){
            this.name = name;
        } else {
            throw new InvalidObjectFieldException("Organization name can't be empty");
        }
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate() {
        this.creationDate = generateDateTime();
    }

    public void setAnnualTurnover(Integer annualTurnover) throws InvalidObjectFieldException {
        if (annualTurnover > 0){
            this.annualTurnover = annualTurnover;
        } else {
            throw new InvalidObjectFieldException("Field annual turnover must be positive");
        }
    }

    public void setEmployeesCount(Integer employeesCount) throws InvalidObjectFieldException {
        if (employeesCount > 0){
            this.employeesCount = employeesCount;
        } else {
            throw new InvalidObjectFieldException("Field employees count must be positive");
        }

    }

    public void setType(OrganizationType type) {
        this.type = type;
    }

    public void setOfficialAddress(Address officialAddress) {
        this.officialAddress = officialAddress;
    }

    public Organization(String name, Coordinates coordinates, Integer annualTurnover, Integer employeesCount, OrganizationType organizationType, Address officialAddress) throws InvalidObjectFieldException {
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(organizationType);
        setOfficialAddress(officialAddress);
    }

    public Organization(String name, Coordinates coordinates, Integer employeesCount, OrganizationType organizationType, Address officialAddress) throws InvalidObjectFieldException {
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setEmployeesCount(employeesCount);
        setType(organizationType);
        setOfficialAddress(officialAddress);
    }

    public Organization(String name, Coordinates coordinates, Integer annualTurnover, Integer employeesCount, OrganizationType organizationType) throws InvalidObjectFieldException {
        setId();
        setCreationDate();
        setName(name);
        setCoordinates(coordinates);
        setAnnualTurnover(annualTurnover);
        setEmployeesCount(employeesCount);
        setType(organizationType);
    }

    public Organization(String name, Coordinates coordinates, Integer employeesCount, OrganizationType organizationType) throws InvalidObjectFieldException {
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

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public Integer getAnnualTurnover() {
        return annualTurnover;
    }

    public Integer getEmployeesCount() {
        return employeesCount;
    }

    public OrganizationType getType() {
        return type;
    }

    public Address getOfficialAddress() {
        return officialAddress;
    }

    @Override
    public String toString() {
        return "ID: "+ getId() + ", NAME: " + getName() + ", COORDINATES: (" + getCoordinates().getX() + "; " + getCoordinates().getY() + ")";
    }
}
