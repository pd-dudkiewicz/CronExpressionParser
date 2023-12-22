package cronparser.parser;

import cronparser.field.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExpressionParser {

    private static final int FIRST_FIELD_INDEX = 0;
    private static final int LAST_FIELD_INDEX = 4;

    private final FieldParser fieldParser;

    public Map<Field, List<Integer>> parse(String[] expressionFields) {
        Map<Field, List<Integer>> fieldValuesMap = new HashMap<>();
        for (int i = FIRST_FIELD_INDEX; i <= LAST_FIELD_INDEX; i++) {
            String fieldString = expressionFields[i];
            Field field = Field.getByIndex(i);
            List<Integer> values = fieldParser.parse(fieldString, field);
            fieldValuesMap.put(field, values);
        }
        return fieldValuesMap;
    }
}
