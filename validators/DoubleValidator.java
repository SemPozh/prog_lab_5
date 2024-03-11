package validators;

import exceptions.InvalidFileException;

public class DoubleValidator extends FieldValidator<Double> {
    public DoubleValidator(boolean notNull, int maxValue, int minValue){
        this.setNotNull(notNull);
        this.setMaxValue(maxValue);
        this.setMinValue(minValue);
    }

    @Override
    Double validate(String value) throws InvalidFileException {
        if (value.equals("")) {
            if (isNotNull()){
                throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
            } else {
                return 0.0;
            }
        }

        if (!(value.matches("((-?)(\\d+)\\.(\\d+))|((-?)(\\d+))"))) {
            throw new InvalidFileException("The numeric field in one of the lines of the file is incorrectly specified! The program cannot be executed!");
        }

        if (Double.parseDouble(value) < getMinValue()){
            throw new InvalidFileException("The values in the file are too small! The program cannot be executed!");
        }


        if (Double.parseDouble(value) > getMaxValue()){
            throw new InvalidFileException("The values in the file are too large! The program cannot be executed!");
        }
        return Double.parseDouble(value);
    }
}
