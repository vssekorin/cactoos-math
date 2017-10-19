package math.constant;

import org.cactoos.Scalar;

/**
 * Euler's number. e
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class E implements Scalar<Number> {

    @Override
    public Number value() throws Exception {
        return Math.E;
    }
}
