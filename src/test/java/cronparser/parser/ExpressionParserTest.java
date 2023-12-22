package cronparser.parser;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.DAY_OF_WEEK;
import static cronparser.field.Field.HOUR;
import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExpressionParserTest {

    @Mock
    private FieldParser fieldParser;

    @InjectMocks
    private ExpressionParser expressionParser;

    @Test
    void parse_shouldReturnFieldValuesMap_whenValidInput() {
        when(fieldParser.parse(any(), any()))
            .thenReturn(List.of(0))
            .thenReturn(List.of(1))
            .thenReturn(List.of(2))
            .thenReturn(List.of(3))
            .thenReturn(List.of(4));
        var expressionFields = new String[]{"0", "*/6", "10", "*/3", "*", "/usr/bin/find"};
        var expected = Map.of(
            MINUTE, List.of(0),
            HOUR, List.of(1),
            DAY_OF_MONTH, List.of(2),
            MONTH, List.of(3),
            DAY_OF_WEEK, List.of(4)
        );

        var actual = expressionParser.parse(expressionFields);

        assertThat(actual).isEqualTo(expected);
        verify(fieldParser).parse("0", MINUTE);
        verify(fieldParser).parse("*/6", HOUR);
        verify(fieldParser).parse("10", DAY_OF_MONTH);
        verify(fieldParser).parse("*/3", MONTH);
        verify(fieldParser).parse("*", DAY_OF_WEEK);
        verifyNoMoreInteractions(fieldParser);
    }
}
