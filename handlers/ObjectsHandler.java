package handlers;

import exceptions.InvalidFileException;
import exceptions.InvalidObjectFieldException;
import models.Address;
import models.Coordinates;
import models.Organization;
import validators.*;

import java.util.HashMap;

public class ObjectsHandler {
    private String[] dataRow;
    HashMap<Integer, FieldValidator<?>> dataStructureHashMap = new HashMap<>();

    public ObjectsHandler(){
        dataStructureHashMap.put(0, new IntegerValidator(true, Integer.MAX_VALUE, Integer.MIN_VALUE));
        dataStructureHashMap.put(1, new StringValidator(true, false));
        dataStructureHashMap.put(2, new IntegerValidator(true, Integer.MAX_VALUE, -392));
        dataStructureHashMap.put(3, new DoubleValidator(true, 518, Integer.MIN_VALUE));
        dataStructureHashMap.put(4, new ZonedDateTimeValidator(true));
        dataStructureHashMap.put(5, new IntegerValidator(false, Integer.MAX_VALUE, 1));
        dataStructureHashMap.put(6, new IntegerValidator(true, Integer.MAX_VALUE, 1));
        dataStructureHashMap.put(7, new EnumValidator(false));
        dataStructureHashMap.put(8, new StringValidator(false, true));
    }

    public void setDataRow(String[] dataRow){
        this.dataRow = dataRow;
    }

    public Organization createObjectFromFileRow() throws InvalidFileException, InvalidObjectFieldException {
        DataValidator dataValidator = new DataValidator(dataStructureHashMap);
        dataValidator.validateRow(dataRow);
        Coordinates coordinates = new Coordinates(dataValidator.x, dataValidator.y);
        Address address;
        if (dataValidator.zipCode.isEmpty()){
            address = null;
        } else {
            address = new Address(dataValidator.zipCode);
        }
        
        Organization organization;
        
        if (dataValidator.annualTurnover==0){
            organization = new Organization(dataValidator.id, dataValidator.name, coordinates, dataValidator.creationDate, dataValidator.employeesCount, dataValidator.organizationType, address);
        } else {
            organization = new Organization(dataValidator.id, dataValidator.name, coordinates, dataValidator.creationDate, dataValidator.annualTurnover, dataValidator.employeesCount, dataValidator.organizationType, address);
        }
        return organization;
    }
}