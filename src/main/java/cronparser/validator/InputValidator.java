package cronparser.validator;

public class InputValidator {

    private static final String FIELDS_SEPARATOR = " ";
    private static final int ALLOWED_NUMBER_OF_INPUT_ARGUMENTS = 1;
    private static final int ALLOWED_NUMBER_OF_FIELDS = 6;

    public void validate(String[] args) {
        if (args.length != ALLOWED_NUMBER_OF_INPUT_ARGUMENTS) {
            throw new InputValidationException("Invalid number of input arguments, please provide a single string");
        }

        String[] expressionFields = args[0].split(FIELDS_SEPARATOR);
        if (expressionFields.length != ALLOWED_NUMBER_OF_FIELDS) {
            throw new InputValidationException(
                "Cron expression should have 6 space-separated fields: minute, hour, day of month, month, day of week, command");
        }
    }
}
