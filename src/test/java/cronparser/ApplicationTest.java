package cronparser;

import static org.mockito.Mockito.verify;

import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApplicationTest {

    @Mock
    private PrintStream printStream;

    @BeforeEach
    void setup() {
        System.setOut(printStream);
    }

    @Test
    void shouldPrintOutput_whenValidInput() {
        String input = "*/15 0 1,15 * 1-5 /usr/bin/find";
        String expected =
            """
                minute          0 15 30 45
                hour            0
                day of month    1 15
                month           1 2 3 4 5 6 7 8 9 10 11 12
                day of week     1 2 3 4 5
                command         /usr/bin/find
                """;

        Application.main(new String[]{input});

        verify(printStream).println(expected);
    }

    @Test
    void shouldPrintOutput_whenValidComplexInput() {
        String input = "13-15,*/10 */3,*/5 3-20/4 7-10 3-6/2 /usr/bin/find";
        String expected =
            """
                minute          0 10 13 14 15 20 30 40 50
                hour            0 3 5 6 9 10 12 15 18 20 21
                day of month    3 7 11 15 19
                month           7 8 9 10
                day of week     3 5
                command         /usr/bin/find
                """;

        Application.main(new String[]{input});

        verify(printStream).println(expected);
    }

    @Test
    void shouldPrintExceptionMessage_whenExceptionOccurs() {
        String input = "/usr/bin/find";
        String expected = "Error: Cron expression should have 6 space-separated fields: minute, hour, day of month, month, day of week, command";

        Application.main(new String[]{input});

        verify(printStream).println(expected);
    }
}
