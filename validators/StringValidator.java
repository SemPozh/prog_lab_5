package validators;

import exceptions.InvalidFileException;

public class StringValidator extends FieldValidator<String> {
    public StringValidator(boolean notNull, boolean emptyString){
        this.setNotNull(notNull);
        this.setEmptyString(emptyString);
    }

    @Override
    String validate(String value) throws InvalidFileException {
        if (value.equals("") && (isNotNull() || !isEmptyString())) {
            throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
        }
        return value;
    }
}
