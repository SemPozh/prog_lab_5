package handlers;

import exceptions.InvalidFileException;
import models.Address;
import models.Coordinates;
import models.Organization;
import validators.*;

import java.util.HashMap;

public class ObjectsHandler {
    private String[] dataRow;
    HashMap<Integer, FieldValidator<?>> dataStructureHashMap = new HashMap<>();

    public ObjectsHandler(){
        dataStructureHashMap.put(0, new StringValidator(true, false));
        dataStructureHashMap.put(1, new IntegerValidator(true, Integer.MAX_VALUE, -392));
        dataStructureHashMap.put(2, new DoubleValidator(false, 518, Integer.MIN_VALUE));
        dataStructureHashMap.put(3, new IntegerValidator(false, Integer.MAX_VALUE, 1));
        dataStructureHashMap.put(4, new IntegerValidator(true, Integer.MAX_VALUE, 1));
        dataStructureHashMap.put(5, new EnumValidator(false));
        dataStructureHashMap.put(6, new StringValidator(false, true));
    }

    public void setDataRow(String[] dataRow){
        this.dataRow = dataRow;
    }

    public Organization createObjectFromFileRow() throws InvalidFileException {
        DataValidator dataValidator = new DataValidator(dataStructureHashMap);
        dataValidator.validateRow(dataRow);
        Coordinates coordinates = new Coordinates(dataValidator.x, dataValidator.y);
        Address address;
        if (dataValidator.zipCode.equals("")){
            address = null;
        } else {
            address = new Address(dataValidator.zipCode);
        }
        Organization organization = new Organization(dataValidator.name, coordinates, dataValidator.annualTurnover, dataValidator.employeesCount, dataValidator.organizationType, address);
        return organization;
    }
}
