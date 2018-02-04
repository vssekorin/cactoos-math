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
import org.cactoos.scalar.NumberEnvelope;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Returns the absolute value.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Abs extends NumberEnvelope {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = -638237816761275732L;

    /**
     * Ctor.
     * @param lnm Long scalar
     * @param inm Int scalar
     * @param fnm Float scalar
     * @param dnm Double scalar
     * @checkstyle ParameterNumberCheck (10 lines)
     * @checkstyle IndentationCheck (10 lines)
     */
    public Abs(final Scalar<Long> lnm, final Scalar<Integer> inm,
        final Scalar<Float> fnm, final Scalar<Double> dnm) {
        super(
            () -> Math.abs(new UncheckedScalar<>(lnm).value()),
            () -> Math.abs(new UncheckedScalar<>(inm).value()),
            () -> Math.abs(new UncheckedScalar<>(fnm).value()),
            () -> Math.abs(new UncheckedScalar<>(dnm).value())
        );
    }
}
