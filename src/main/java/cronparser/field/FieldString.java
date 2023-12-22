package cronparser.field;

import cronparser.value.All;
import cronparser.value.Number;
import cronparser.value.Range;
import cronparser.value.Step;
import cronparser.value.Value;

public class FieldString {

    public static Value toValue(String fieldString, Field field) {
        if (isAsterisk(fieldString)) {
            return All.create(field);
        } else if (hasSlash(fieldString)) {
            return Step.create(fieldString, field);
        } else if (isNumbersSeparatedWithDash(fieldString)) {
            return Range.create(fieldString, field);
        } else if (isNumber(fieldString)) {
            return Number.create(fieldString, field);
        }
        throw new FieldParsingException(fieldString);
    }

    private static boolean isAsterisk(String string) {
        return "*" .equals(string);
    }

    private static boolean hasSlash(String string) {
        return string.contains("/");
    }

    private static boolean isNumbersSeparatedWithDash(String string) {
        return string.matches("(\\d+)-(\\d+)");
    }

    private static boolean isNumber(String string) {
        return string.matches("\\d+");
    }
}
