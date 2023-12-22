package cronparser.field;

public class FieldParsingException extends RuntimeException {

    private static final String BASE_MESSAGE_TEMPLATE = "Cannot parse field: %s";

    public FieldParsingException(String fieldString) {
        super(String.format(BASE_MESSAGE_TEMPLATE, fieldString));
    }
}
