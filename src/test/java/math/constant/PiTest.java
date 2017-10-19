package math.constant;

import org.cactoos.ScalarHasValue;
import org.hamcrest.MatcherAssert;
import org.junit.Test;

/**
 * @author VsSekorin
 */
public final class PiTest {

    @Test
    public void value() throws Exception {
        MatcherAssert.assertThat(
            new Pi(),
            new ScalarHasValue<>(Math.PI)
        );
    }
}
