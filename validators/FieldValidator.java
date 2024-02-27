package validators;
import exceptions.InvalidFileException;
public abstract class FieldValidator<T> {
    private boolean notNull;
    private boolean emptyString;

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    public boolean isEmptyString() {
        return emptyString;
    }

    public void setEmptyString(boolean emptyString) {
        this.emptyString = emptyString;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private int minValue;
    private int maxValue;

    abstract T validate(String value) throws InvalidFileException;
}