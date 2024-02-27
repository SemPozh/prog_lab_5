package validators;

import exceptions.InvalidFileException;
import models.OrganizationType;

public class EnumValidator extends FieldValidator<OrganizationType>{
    public EnumValidator(boolean notNull){
        this.setNotNull(notNull);
    }

    @Override
    OrganizationType validate(String value) throws InvalidFileException {
        if (value.equals("") && isNotNull()){
            throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
        }
        try {
            return OrganizationType.valueOf(value);
        } catch (IllegalArgumentException e){
            throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
        }
    }
}
