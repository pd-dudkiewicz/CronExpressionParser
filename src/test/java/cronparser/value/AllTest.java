package cronparser.value;

import static cronparser.field.Field.DAY_OF_MONTH;
import static cronparser.field.Field.DAY_OF_WEEK;
import static cronparser.field.Field.HOUR;
import static cronparser.field.Field.MINUTE;
import static cronparser.field.Field.MONTH;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class AllTest {

    @Test
    void toIntegers_shouldReturnListOfIntegers() {
        assertThat(new All(MINUTE).toIntegers()).isEqualTo(IntStream.rangeClosed(0, 59).boxed().toList());
        assertThat(new All(HOUR).toIntegers()).isEqualTo(IntStream.rangeClosed(0, 23).boxed().toList());
        assertThat(new All(DAY_OF_MONTH).toIntegers()).isEqualTo(IntStream.rangeClosed(1, 31).boxed().toList());
        assertThat(new All(MONTH).toIntegers()).isEqualTo(IntStream.rangeClosed(1, 12).boxed().toList());
        assertThat(new All(DAY_OF_WEEK).toIntegers()).isEqualTo(IntStream.rangeClosed(0, 6).boxed().toList());
    }
}
