package cronparser.mapper;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.DAY_OF_WEEK;
import static cronparser.field.Field.HOUR;
import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class OutputMapperTest {

    OutputMapper outputMapper = new OutputMapper();

    @Test
    void map_shouldReturnStringSortedByFieldOrder_whenValidInput() {
        var fieldValuesMap = Map.of(
            DAY_OF_MONTH, List.of(10),
            HOUR, List.of(0, 6, 12, 18),
            DAY_OF_WEEK, List.of(0, 1, 2, 3, 4, 5, 6),
            MONTH, List.of(3, 6, 9, 12),
            MINUTE, List.of(0)
        );
        var command = "/usr/bin/find";
        String expected =
            """
                minute          0
                hour            0 6 12 18
                day of month    10
                month           3 6 9 12
                day of week     0 1 2 3 4 5 6
                command         /usr/bin/find
                """;

        String actual = outputMapper.map(fieldValuesMap, command);

        assertThat(actual).isEqualTo(expected);
    }
}
