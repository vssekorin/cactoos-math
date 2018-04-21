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
package com.vssekorin.cactoosmath.scalar;

import java.util.Map;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;

/**
 * Pattern matching.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of input.
 * @param <Y> Type of output.
 * @since 0.2
 */
public final class Match<X, Y> implements Func<X, Y> {

    /**
     * Map.
     */
    private final Map<Func<X, Boolean>, Func<X, Y>> map;

    /**
     * Default case.
     */
    private final Func<X, Y> other;

    /**
     * Ctor.
     * @param src Map
     * @param dflt Default case
     */
    public Match(final Map<Func<X, Boolean>, Func<X, Y>> src,
        final Func<X, Y> dflt) {
        this.map = src;
        this.other = dflt;
    }

    @Override
    public Y apply(final X input) throws Exception {
        return this.map.entrySet().stream()
            .filter(item -> new UncheckedFunc<>(item.getKey()).apply(input))
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(this.other)
            .apply(input);
    }
}
