package cronparser.parser;

import static cronparser.field.Field.MINUTE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import cronparser.mapper.OutputMapper;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CronExpressionParserTest {

    @Mock
    private ExpressionParser expressionParser;

    @Mock
    private OutputMapper outputMapper;

    @InjectMocks
    private CronExpressionParser cronExpressionParser;

    @Test
    void parse_shouldReturnString_whenValidInput() {
        var mockedFieldValuesMap = Map.of(MINUTE, List.of(0));
        var mockedOutputString = "Mocked output string";
        when(expressionParser.parse(any())).thenReturn(mockedFieldValuesMap);
        when(outputMapper.map(any(), any())).thenReturn(mockedOutputString);
        var expression = "0 */6 10 */3 * /usr/bin/find";

        var actual = cronExpressionParser.parse(expression);

        assertThat(actual).isEqualTo(mockedOutputString);
        verify(expressionParser).parse(new String[]{"0", "*/6", "10", "*/3", "*", "/usr/bin/find"});
        verify(outputMapper).map(mockedFieldValuesMap, "/usr/bin/find");
    }
}
