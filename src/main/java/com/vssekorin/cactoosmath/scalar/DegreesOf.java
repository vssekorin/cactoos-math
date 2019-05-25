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
 * Converts an angle measured in radians to an
 * approximately equivalent angle measured in degrees.
 * <p> The conversion from  radians to degrees is generally inexact.
 * Do <i>not</i> expect perfect accuracy. </p>
 *
 * @author Alexandru Stoica (stoica.alexandru20000@gmail.com)
 * @version $Id$
 * @since 0.3
 */
public final class DegreesOf extends NumberEnvelope {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = 1047352930537306205L;

    /**
     * Ctor.
     *
     * @param scl Scalar
     */
    public DegreesOf(final Scalar<Number> scl) {
        this(new UncheckedScalar<>(scl).value());
    }

    /**
     * Ctor.
     *
     * @param nmb Number
     */
    public DegreesOf(final Number nmb) {
        super(() -> Math.toDegrees(nmb.doubleValue()));
    }
}
