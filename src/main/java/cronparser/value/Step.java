package cronparser.value;

import cronparser.field.Field;
import cronparser.field.FieldParsingException;
import cronparser.field.FieldString;
import java.util.List;
import java.util.stream.IntStream;

public record Step(StepPermittedFirstPart firstPart, Number secondPart, Field field) implements Value {

    private static final int ALLOWED_NUMBER_OF_STEP_PARTS = 2;
    private static final String STEP_SEPARATOR = "/";

    @Override
    public List<Integer> toIntegers() {
        return IntStream.rangeClosed(firstPart.start(), firstPart.end())
            .filter(n -> (n - firstPart.start()) % secondPart.number() == 0)
            .boxed()
            .toList();
    }

    public static Step create(String fieldString, Field field) {
        var parts = fieldString.split(STEP_SEPARATOR);
        if (parts.length != ALLOWED_NUMBER_OF_STEP_PARTS) {
            throw new FieldParsingException(fieldString);
        }

        Value firstPart = FieldString.toValue(parts[0], field);
        if (!isPermittedStepValue(firstPart)) {
            throw new ValueValidationException("First part of step needs to be * or number range");
        }

        Value secondPart = FieldString.toValue(parts[1], field);
        if (!isNumber(secondPart)) {
            throw new ValueValidationException("Second part of step needs to be number");
        }

        return new Step((StepPermittedFirstPart) firstPart, (Number) secondPart, field);
    }

    private static boolean isPermittedStepValue(Value secondPart) {
        return secondPart instanceof StepPermittedFirstPart;
    }

    private static boolean isNumber(Value secondPart) {
        return secondPart instanceof Number;
    }
}
