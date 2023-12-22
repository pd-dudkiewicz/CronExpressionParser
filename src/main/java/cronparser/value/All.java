package cronparser.value;

import cronparser.field.Field;
import java.util.List;
import java.util.stream.IntStream;

public record All(Field field) implements Value, StepPermittedFirstPart {

    @Override
    public List<Integer> toIntegers() {
        return IntStream.rangeClosed(field.getMinValue(), field.getMaxValue()).boxed().toList();
    }

    @Override
    public int start() {
        return field.getMinValue();
    }

    @Override
    public int end() {
        return field().getMaxValue();
    }

    public static All create(Field field) {
        return new All(field);
    }
}
