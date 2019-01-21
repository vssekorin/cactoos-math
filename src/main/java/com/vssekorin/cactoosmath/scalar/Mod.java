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

/**
 * Returns the modulus of two integer numbers.
 *
 * @author Sorin Florea (sorin.florea.v@gmail.com)
 * @version $Id$
 * @since 0.2
 */
public final class Mod implements Scalar<Integer> {

    /**
     * Serialization marker.
     */
    private static final long serialVersionUID = -9011457834516765608L;

    /**
     * The mod value.
     */
    private final Scalar<Integer> scl;

    /**
     * Ctor.
     *
     * @param dividend Integer
     * @param divisor Integer
     */
    public Mod(final Integer dividend, final Integer divisor) {
        this(() -> dividend, () -> divisor);
    }

    /**
     * Ctor.
     *
     * @param dividend Scalar
     * @param divisor Scalar
     */
    public Mod(final Scalar<Integer> dividend, final Scalar<Integer> divisor) {
        this.scl = () -> Math.floorMod(dividend.value(), divisor.value());
    }

    @Override
    public Integer value() throws Exception {
        return this.scl.value();
    }
}
