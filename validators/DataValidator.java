package validators;

import exceptions.InvalidFileException;
import models.OrganizationType;

import java.lang.reflect.Field;
import java.time.ZonedDateTime;
import java.util.HashMap;

public class DataValidator {
    HashMap<Integer, FieldValidator<?>> dataStructure;

    public Integer id;

    public String name;
    public Integer x;
    public double y;

    public ZonedDateTime creationDate;
    public Integer annualTurnover;
    public Integer employeesCount;
    public OrganizationType organizationType;
    public String zipCode;

    public DataValidator(HashMap<Integer, FieldValidator<?>> dataStructure) {
        this.dataStructure = dataStructure;
    }

    public void validateRow(String[] row) throws InvalidFileException{
        try {
            Field[] fields = DataValidator.class.getFields();

            for (int i = 0; i < 9; i++) {
//                System.out.println(row[i]);
                fields[i].setAccessible(true);
                fields[i].set(this, (dataStructure.get(i).validate(row[i])));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidFileException("There is not enough data in one of the lines of the file! The program cannot be executed");
        } catch (IllegalAccessException e){
            System.out.println("Something went wrong!");
        }
    }

}
