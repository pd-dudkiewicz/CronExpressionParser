package cronparser.parser;

import cronparser.mapper.OutputMapper;
import cronparser.field.Field;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CronExpressionParser {

    private static final String FIELDS_SEPARATOR = " ";
    private static final int COMMAND_FIELD_INDEX = 5;

    private final ExpressionParser expressionParser;
    private final OutputMapper outputMapper;

    public String parse(String expression) {
        String[] expressionFields = expression.split(FIELDS_SEPARATOR);
        Map<Field, List<Integer>> fieldValues = expressionParser.parse(expressionFields);
        String command = expressionFields[COMMAND_FIELD_INDEX];

        return outputMapper.map(fieldValues, command);
    }
}
