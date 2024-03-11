package validators;

import exceptions.InvalidFileException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeValidator extends FieldValidator<ZonedDateTime>{
    public ZonedDateTimeValidator(boolean notNull){
        this.setNotNull(notNull);
    }
    @Override
    ZonedDateTime validate(String value) throws InvalidFileException {
        if (value.equals("")) {
            if (isNotNull()){
                throw new InvalidFileException("Incorrect data in the file! The program cannot be executed!");
            } else {
                return null;
            }
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate date = LocalDate.parse(value, formatter);
            ZonedDateTime res = date.atStartOfDay(ZoneId.systemDefault());
            return res;
        } catch (DateTimeParseException e){
            throw new InvalidFileException("Date in file is invalid. It must be in format of dd.MM.yyyy");
        }



    }
}
