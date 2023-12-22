package cronparser.mapper;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.DAY_OF_WEEK;
import static cronparser.field.Field.HOUR;
import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;

import cronparser.field.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class OutputMapper {

    private static final Map<Field, String> FIELD_SPACES_MAP = Map.of(
        MINUTE, "          ",
        HOUR, "            ",
        DAY_OF_MONTH, "    ",
        MONTH, "           ",
        DAY_OF_WEEK, "     "
    );

    public String map(Map<Field, List<Integer>> fieldValues, String command) {
        var fieldValuesSortedByFieldOrder = new TreeMap<>(fieldValues);
        StringBuilder stringBuilder = new StringBuilder();
        fieldValuesSortedByFieldOrder.forEach((field, values) -> appendFieldWithValues(stringBuilder, field, values));
        appendCommand(stringBuilder, command);

        return stringBuilder.toString();
    }

    private void appendFieldWithValues(StringBuilder stringBuilder, Field field, List<Integer> values) {
        stringBuilder
            .append(field.getFormattedName())
            .append(FIELD_SPACES_MAP.get(field))
            .append(listToFormattedString(values))
            .append("\n");
    }

    private void appendCommand(StringBuilder stringBuilder, String command) {
        stringBuilder
            .append("command")
            .append("         ")
            .append(command)
            .append("\n");
    }

    private String listToFormattedString(List<Integer> integerList) {
        return integerList.toString()
            .replace("[", "")
            .replace("]", "")
            .replace(",", "");
    }
}
