package cronparser;

import cronparser.mapper.OutputMapper;
import cronparser.parser.CronExpressionParser;
import cronparser.parser.ExpressionParser;
import cronparser.parser.FieldParser;
import cronparser.validator.InputValidator;

public class Application {

    public static void main(String[] args) {
        String output;
        try {
            inputValidator().validate(args);
            output = cronExpressionParser().parse(args[0]);
        } catch (Exception e) {
            output = "Error: " + e.getMessage();
        }
        System.out.println(output);
    }

    private static InputValidator inputValidator() {
        return new InputValidator();
    }

    private static CronExpressionParser cronExpressionParser() {
        FieldParser fieldParser = new FieldParser();
        ExpressionParser expressionParser = new ExpressionParser(fieldParser);
        OutputMapper outputMapper = new OutputMapper();

        return new CronExpressionParser(expressionParser, outputMapper);
    }
}
