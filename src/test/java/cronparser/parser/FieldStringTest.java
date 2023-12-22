package cronparser.parser;

import static cronparser.field.Field.MINUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

import cronparser.field.FieldParsingException;
import cronparser.field.FieldString;
import cronparser.value.All;
import cronparser.field.Field;
import cronparser.value.Number;
import cronparser.value.Range;
import cronparser.value.Step;
import cronparser.value.Value;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FieldStringTest {

    private static final Field FIELD = MINUTE;

    private static Stream<Arguments> toValueTestDataProvider() {
        return Stream.of(
            of("*", new All(FIELD)),
            of("*/5", new Step(new All(FIELD), new Number(5, FIELD), FIELD)),
            of("1-5", new Range(1, 5, FIELD)),
            of("5", new Number(5, FIELD))
        );
    }

    @ParameterizedTest
    @MethodSource("toValueTestDataProvider")
    void toValue_shouldReturnAll_whenInputIsAsterisk(String input, Value expectedValue) {
        assertThat(FieldString.toValue(input, FIELD)).isEqualTo(expectedValue);
    }

    @Test
    void toValue_shouldThrowException_whenInvalidInput() {
        assertThatThrownBy(() -> FieldString.toValue("text", FIELD))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: text");
    }
}
