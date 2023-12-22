package cronparser.value;

import static cronparser.field.Field.MINUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cronparser.field.FieldParsingException;
import org.junit.jupiter.api.Test;

public class RangeTest {

    @Test
    void constructor_shouldThrowException_whenRangeStartIsBiggerThanRangeEnd() {
        assertThatThrownBy(() -> new Range(5, 1, MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Range start 5 cannot be bigger than range end 1");
    }

    @Test
    void constructor_shouldThrowException_whenRangeStartIsOutsideOfRangeForField() {
        assertThatThrownBy(() -> new Range(-1, 5, MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Invalid range start -1 for minute field");
    }

    @Test
    void constructor_shouldThrowException_whenRangeEndIsOutsideOfRangeForField() {
        assertThatThrownBy(() -> new Range(1, 60, MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Invalid range end 60 for minute field");
    }

    @Test
    void toIntegers_shouldReturnListOfIntegers() {
        assertThat(new Range(1, 5, MINUTE).toIntegers()).containsExactly(1, 2, 3, 4, 5);
    }

    @Test
    void create_shouldThrowException_whenInvalidStartFieldString() {
        assertThatThrownBy(() -> Range.create("1a-5", MINUTE))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: 1a-5");
    }

    @Test
    void create_shouldThrowException_whenInvalidEndFieldString() {
        assertThatThrownBy(() -> Range.create("1-5a", MINUTE))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: 1-5a");
    }

    @Test
    void create_shouldReturnNumber_whenValidInput() {
        assertThat(Range.create("1-5", MINUTE)).isEqualTo(new Range(1, 5, MINUTE));
    }

}
