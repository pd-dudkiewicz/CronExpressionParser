package cronparser.parser;

import cronparser.field.FieldString;
import cronparser.field.Field;
import java.util.ArrayList;
import java.util.List;

public class FieldParser {

    private static final String VALUE_LIST_SEPARATOR = ",";

    public List<Integer> parse(String fieldString, Field field) {
        if (fieldString.contains(VALUE_LIST_SEPARATOR)) {
            List<Integer> values = new ArrayList<>();
            for (String fieldStringElement : fieldString.split(VALUE_LIST_SEPARATOR)) {
                values.addAll(FieldString.toValue(fieldStringElement, field).toIntegers());
            }
            return values.stream().distinct().sorted().toList();
        }
        return FieldString.toValue(fieldString, field).toIntegers();
    }
}
