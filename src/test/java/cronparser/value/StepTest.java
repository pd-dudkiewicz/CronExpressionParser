package cronparser.value;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.MINUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import cronparser.field.Field;
import cronparser.field.FieldParsingException;
import org.junit.jupiter.api.Test;

public class StepTest {

    @Test
    void toIntegers_shouldReturnListOfIntegers_whenValidInput() {
        Field field = MINUTE;
        StepPermittedFirstPart firstPart = new All(field);
        Number secondPart = new Number(10, field);
        Step step = new Step(firstPart, secondPart, field);

        assertThat(step.toIntegers()).containsExactly(0, 10, 20, 30, 40, 50);
    }

    @Test
    void toIntegers_shouldReturnListOfIntegers_whenFieldMinValueIsNotZero() {
        Field field = DAY_OF_MONTH;
        StepPermittedFirstPart firstPart = new All(field);
        Number secondPart = new Number(3, field);
        Step step = new Step(firstPart, secondPart, field);

        assertThat(step.toIntegers()).containsExactly(1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31);
    }

    @Test
    void toIntegers_shouldReturnListOfIntegers_whenFirstPartIsRange() {
        Field field = MINUTE;
        StepPermittedFirstPart firstPart = new Range(5, 30, field);
        Number secondPart = new Number(4, field);
        Step step = new Step(firstPart, secondPart, field);

        assertThat(step.toIntegers()).containsExactly(5, 9, 13, 17, 21, 25, 29);
    }

    @Test
    void create_shouldThrowException_whenInvalidNumberOfParts() {
        assertThatThrownBy(() -> Step.create("/", MINUTE))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: /");

        assertThatThrownBy(() -> Step.create("*/1/2", MINUTE))
            .isInstanceOf(FieldParsingException.class)
            .hasMessage("Cannot parse field: */1/2");
    }

    @Test
    void create_shouldThrowException_whenInvalidFirstPart() {
        assertThatThrownBy(() -> Step.create("1/5", MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("First part of step needs to be * or number range");
    }

    @Test
    void create_shouldThrowException_whenInvalidSecondPart() {
        assertThatThrownBy(() -> Step.create("*/1-5", MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Second part of step needs to be number");

        assertThatThrownBy(() -> Step.create("*/*", MINUTE))
            .isInstanceOf(ValueValidationException.class)
            .hasMessage("Second part of step needs to be number");
    }

    @Test
    void create_shouldReturnStep_whenValidInput() {
        assertThat(Step.create("*/5", MINUTE)).isEqualTo(new Step(new All(MINUTE), new Number(5, MINUTE), MINUTE));
    }
}
