package math.constant;

import org.cactoos.Scalar;

/**
 * Pi.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Pi implements Scalar<Number> {

    @Override
    public Number value() throws Exception {
        return Math.PI;
    }
}
