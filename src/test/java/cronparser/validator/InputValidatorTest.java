package cronparser.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class InputValidatorTest {

    private final InputValidator inputValidator = new InputValidator();

    @Test
    void validate_shouldThrowException_whenInvalidNumberOfArguments() {
        assertThatThrownBy(() -> inputValidator.validate(new String[]{}))
            .isInstanceOf(InputValidationException.class)
            .hasMessage("Invalid number of input arguments, please provide a single string");

        assertThatThrownBy(() -> inputValidator.validate(new String[]{"0 */6 10 */3 *", "/usr/bin/find"}))
            .isInstanceOf(InputValidationException.class)
            .hasMessage("Invalid number of input arguments, please provide a single string");
    }

    @Test
    void validate_shouldThrowException_whenInvalidNumberOfFields() {
        assertThatThrownBy(() -> inputValidator.validate(new String[]{"0 */6 10 */3 *"}))
            .isInstanceOf(InputValidationException.class)
            .hasMessage(
                "Cron expression should have 6 space-separated fields: minute, hour, day of month, month, day of week, command");
    }

    @Test
    void validate_shouldDoNothing_whenValidInput() {
        inputValidator.validate(new String[]{"0 */6 10 */3 * /usr/bin/find"});
    }
}
