/**
 * MIT License
 *
 * Copyright (c) 2017 Vseslav Sekorin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cactoosmath;

import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Returns a double value with a positive sign,
 * greater than or equal to 0.0 and less than 1.0.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Random implements Scalar<Double> {

    /**
     * Low bound.
     */
    private final double min;

    /**
     * High bound.
     */
    private final double max;

    /**
     * Ctor.
     */
    public Random() {
        this(0.0, 1.0);
    }

    /**
     * Ctor.
     * @param fst Min scalar
     * @param snd Max scalar
     */
    public Random(final Scalar<Number> fst, final Scalar<Number> snd) {
        this(
            new UncheckedScalar<>(fst).value().doubleValue(),
            new UncheckedScalar<>(snd).value().doubleValue()
        );
    }

    /**
     * Ctor.
     * @param fst Min number
     * @param snd Max number
     */
    public Random(final double fst, final double snd) {
        this.min = fst;
        this.max = snd;
    }

    @Override
    public Double value() throws Exception {
        return Math.random() * (this.max - this.min) + this.min;
    }
}
