package cronparser.value;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.DAY_OF_WEEK;
import static cronparser.field.Field.HOUR;
import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;
import static org.assertj.core.api.Assertions.assertThat;

import cronparser.field.Field;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class FieldTest {

    @Test
    void getByIndex_shouldReturnExpectedField() {
        assertThat(Field.getByIndex(0)).isEqualTo(MINUTE);
        assertThat(Field.getByIndex(1)).isEqualTo(HOUR);
        assertThat(Field.getByIndex(2)).isEqualTo(DAY_OF_MONTH);
        assertThat(Field.getByIndex(3)).isEqualTo(MONTH);
        assertThat(Field.getByIndex(4)).isEqualTo(DAY_OF_WEEK);
    }

    @ParameterizedTest
    @EnumSource(Field.class)
    void allowsValue_shouldReturnTrue_whenValueIsEqualToMinOrMax(Field field) {
        assertThat(field.allowsValue(field.getMinValue())).isTrue();
        assertThat(field.allowsValue(field.getMaxValue())).isTrue();
    }

    @ParameterizedTest
    @EnumSource(Field.class)
    void allowsValue_shouldReturnFalse_whenValueIsBelowMinOrAboveMax(Field field) {
        assertThat(field.allowsValue(field.getMinValue() - 1)).isFalse();
        assertThat(field.allowsValue(field.getMaxValue() + 1)).isFalse();
    }
}
