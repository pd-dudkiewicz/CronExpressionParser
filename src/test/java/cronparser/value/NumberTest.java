package cronparser.value;

import static cronparser.field.Field.MINUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cronparser.field.FieldParsingException;
import org.junit.jupiter.api.Test;

public class NumberTest {

    @Test
    void constructor_shouldThrowException_whenNumberIsOutsideOfRangeForField() {
        assertThatThrownBy(() -> new Number(-1, MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Number -1 is outside of range for minute field");
    }

    @Test
    void toIntegers_shouldReturnListWithSingleInteger() {
        assertThat(new Number(1, MINUTE).toIntegers()).containsOnly(1);
    }

    @Test
    void create_shouldThrowException_whenInvalidFieldString() {
        assertThatThrownBy(() -> Number.create("1*", MINUTE))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: 1*");
    }

    @Test
    void create_shouldReturnNumber_whenValidInput() {
        assertThat(Number.create("1", MINUTE)).isEqualTo(new Number(1, MINUTE));
    }
}
