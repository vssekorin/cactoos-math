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
 * Greatest common divisor.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.2
 * @checkstyle AbbreviationAsWordInNameCheck (500 lines)
 */
public final class GCD implements Scalar<Integer> {

    /**
     * First number.
     */
    private final int first;

    /**
     * Second number.
     */
    private final int second;

    /**
     * Ctor.
     * @param fst First number envelope
     * @param snd Second number envelope
     */
    public GCD(final NumberEnvelope fst, final NumberEnvelope snd) {
        this(fst.intValue(), snd.intValue());
    }

    /**
     * Ctor.
     * @param fst First scalar
     * @param snd Second scalar
     */
    public GCD(final Scalar<Integer> fst, final Scalar<Integer> snd) {
        this(
            new UncheckedScalar<>(fst).value(),
            new UncheckedScalar<>(snd).value()
        );
    }

    /**
     * Ctor.
     * @param fst First integer
     * @param snd Second integer
     */
    public GCD(final int fst, final int snd) {
        this.first = fst;
        this.second = snd;
    }

    @Override
    public Integer value() {
        int fst = this.first;
        int snd = this.second;
        while (fst != 0) {
            final int temp = fst;
            fst = snd % fst;
            snd = temp;
        }
        return snd;
    }
}
