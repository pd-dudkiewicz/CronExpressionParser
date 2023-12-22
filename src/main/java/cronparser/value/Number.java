package cronparser.value;

import cronparser.field.Field;
import cronparser.field.FieldParsingException;
import java.util.List;

public record Number(int number, Field field) implements Value {

    public Number(int number, Field field) {
        this.number = number;
        this.field = field;
        validate();
    }

    @Override
    public List<Integer> toIntegers() {
        return List.of(number);
    }

    public static Number create(String fieldString, Field field) {
        try {
            return new Number(Integer.parseInt(fieldString), field);
        } catch (NumberFormatException e) {
            throw new FieldParsingException(fieldString);
        }
    }

    private void validate() {
        if (!field.allowsValue(number)) {
            throw new ValueValidationException(
                String.format("Number %d is outside of range for %s field", number, field.getFormattedName()));
        }
    }
}
