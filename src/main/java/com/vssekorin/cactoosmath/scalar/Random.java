/**
 * MIT License
 *
 * Copyright (c) 2017-2019 Vseslav Sekorin
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
package com.vssekorin.cactoosmath.scalar;

import org.cactoos.Scalar;
import org.cactoos.scalar.NumberEnvelope;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Returns a double value with a positive sign,
 * greater than or equal to 0.0 and less than 1.0.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Random extends NumberEnvelope {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = -2140462367459861873L;

    /**
     * Ctor.
     */
    public Random() {
        this(0.0, 1.0);
    }

    /**
     * Ctor.
     * @param min Min scalar
     * @param max Max scalar
     */
    public Random(final Scalar<Number> min, final Scalar<Number> max) {
        this(
            new UncheckedScalar<>(min).value().doubleValue(),
            new UncheckedScalar<>(max).value().doubleValue()
        );
    }

    /**
     * Ctor.
     * @param min Min number
     * @param max Max number
     */
    public Random(final double min, final double max) {
        super(() -> Math.random() * (max - min) + min);
    }
}
