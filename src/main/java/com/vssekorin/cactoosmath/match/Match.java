/**
 * MIT License
 *
 * Copyright (c) 2017-2018 Vseslav Sekorin
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
package com.vssekorin.cactoosmath.match;

import java.util.List;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.func.BiFuncOf;
import org.cactoos.list.ListOf;

/**
 * Pattern matching.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of first input
 * @param <Y> Type of second input
 * @param <Z> Type of result
 * @since 0.2
 */
public final class Match<X, Y, Z> implements
    Scalar<Z>, Func<X, Z>, BiFunc<X, Y, Z> {

    /**
     * Default case.
     */
    private final BiFunc<X, Y, Z> other;

    /**
     * List of cases.
     */
    private final List<MatchCase<X, Y, Z>> cases;

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    @SafeVarargs
    public Match(final Z dflt, final MatchCase<X, Y, Z>... src) {
        this(new BiFuncOf<>(dflt), src);
    }

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    public Match(final Z dflt, final List<MatchCase<X, Y, Z>> src) {
        this(new BiFuncOf<>(dflt), src);
    }

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    @SafeVarargs
    public Match(final Func<X, Z> dflt, final MatchCase<X, Y, Z>... src) {
        this(new BiFuncOf<>(dflt), src);
    }

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    public Match(final Func<X, Z> dflt, final List<MatchCase<X, Y, Z>> src) {
        this(new BiFuncOf<>(dflt), src);
    }

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    @SafeVarargs
    public Match(final BiFunc<X, Y, Z> dflt, final MatchCase<X, Y, Z>... src) {
        this(dflt, new ListOf<>(src));
    }

    /**
     * Ctor.
     * @param dflt Default case
     * @param src Cases
     */
    public Match(final BiFunc<X, Y, Z> dflt,
        final List<MatchCase<X, Y, Z>> src) {
        this.other = dflt;
        this.cases = src;
    }

    @Override
    public Z apply(final X first, final Y second) throws Exception {
        BiFunc<X, Y, Z> result = this.other;
        for (final MatchCase<X, Y, Z> mcase : this.cases) {
            if (mcase.test().apply(first, second)) {
                result = mcase.result();
                break;
            }
        }
        return result.apply(first, second);
    }

    @Override
    public Z apply(final X input) throws Exception {
        return this.apply(input, null);
    }

    @Override
    public Z value() throws Exception {
        return this.apply(null, null);
    }
}
