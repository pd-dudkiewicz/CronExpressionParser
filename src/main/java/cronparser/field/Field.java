package cronparser.field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Field {

    MINUTE("minute", 0, 59),
    HOUR("hour", 0, 23),
    DAY_OF_MONTH("day of month", 1, 31),
    MONTH("month", 1, 12),
    DAY_OF_WEEK("day of week", 0, 6);

    private static final Field[] VALUES = values();

    private final String formattedName;
    private final int minValue;
    private final int maxValue;

    public static Field getByIndex(int index) {
        return VALUES[index];
    }

    public boolean allowsValue(int number) {
        return number >= minValue && number <= maxValue;
    }
}
