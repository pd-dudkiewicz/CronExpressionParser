package cronparser.parser;

import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

public class FieldParserTest {

    private final FieldParser fieldParser = new FieldParser();

    @Test
    void parse_shouldReturnListOfIntegers_whenValidInput() {
        String input = "1-5";
        var expected = List.of(1, 2, 3, 4, 5);

        var actual = fieldParser.parse(input, MINUTE);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void parse_shouldReturnSortedListOfIntegers_whenCommaSeparatedString() {
        String input = "1-5,*/10,31,32,33";
        var expected = List.of(0, 1, 2, 3, 4, 5, 10, 20, 30, 31, 32, 33, 40, 50);

        var actual = fieldParser.parse(input, MINUTE);

        assertThat(actual).containsExactlyElementsOf(expected);
    }

    @Test
    void parse_shouldReturnSortedListOfUniqueIntegers_whenCommaSeparatedStringWithOverlappingValues() {
        String input = "1-5,*/2,*/3";
        var expected = List.of(1, 2, 3, 4, 5, 7, 9, 10, 11);

        var actual = fieldParser.parse(input, MONTH);

        assertThat(actual).doesNotHaveDuplicates();
        assertThat(actual).containsExactlyElementsOf(expected);
    }
}
