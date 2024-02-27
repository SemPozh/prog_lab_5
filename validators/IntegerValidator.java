package validators;

import exceptions.InvalidFileException;

public class IntegerValidator extends FieldValidator<Integer> {
    public IntegerValidator(boolean notNull, int maxValue, int minValue){
        this.setNotNull(notNull);
        this.setMaxValue(maxValue);
        this.setMinValue(minValue);
    }

    @Override
    Integer validate(String value) throws InvalidFileException {

        if (value.equals("")) {
            if (isNotNull()){
                throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
            } else {
                return null;
            }
        }

        if (!(value.matches("[-+]?\\d+"))) {
            throw new InvalidFileException("The numeric field in one of the lines of the file is incorrectly specified! The program cannot be executed!");
        }

        if (Integer.parseInt(value) < getMinValue()){
            throw new InvalidFileException("The values in the file are too small! The program cannot be executed!");
        }


        if (Integer.parseInt(value) > getMaxValue()){
            throw new InvalidFileException("The values in the file are too large! The program cannot be executed!");
        }

        return Integer.parseInt(value);
    }
}
