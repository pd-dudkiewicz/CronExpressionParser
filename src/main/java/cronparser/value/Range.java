package cronparser.value;

import cronparser.field.Field;
import cronparser.field.FieldParsingException;
import java.util.List;
import java.util.stream.IntStream;

public record Range(int start, int end, Field field) implements Value, StepPermittedFirstPart {

    private static final String RANGE_SEPARATOR = "-";

    public Range(int start, int end, Field field) {
        this.start = start;
        this.end = end;
        this.field = field;
        validate();
    }

    @Override
    public List<Integer> toIntegers() {
        return IntStream.rangeClosed(start, end).boxed().toList();
    }

    public static Range create(String fieldString, Field field) {
        var numbers = fieldString.split(RANGE_SEPARATOR);
        try {
            return new Range(Integer.parseInt(numbers[0]), Integer.parseInt(numbers[1]), field);
        } catch (NumberFormatException e) {
            throw new FieldParsingException(fieldString);
        }
    }

    private void validate() {
        if (start > end) {
            throw new ValueValidationException(
                String.format("Range start %s cannot be bigger than range end %s", start, end));
        }
        if (!field.allowsValue(start)) {
            throw new ValueValidationException(
                String.format("Invalid range start %d for %s field", start, field.getFormattedName()));
        }
        if (!field.allowsValue(end)) {
            throw new ValueValidationException(
                String.format("Invalid range end %d for %s field", end, field.getFormattedName()));
        }
    }
}
