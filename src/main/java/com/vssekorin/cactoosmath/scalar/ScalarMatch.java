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
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Pattern matching as Scalar.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of output.
 * @since 0.2
 */
public final class ScalarMatch<T> implements Scalar<T> {

    /**
     * Map.
     */
    private final Map<Scalar<Boolean>, Scalar<T>> map;

    /**
     * Default case.
     */
    private final Scalar<T> other;

    /**
     * Ctor.
     * @param src Map
     * @param dflt Default case
     */
    public ScalarMatch(final Map<Scalar<Boolean>, Scalar<T>> src,
        final Scalar<T> dflt) {
        this.map = src;
        this.other = dflt;
    }

    @Override
    public T value() throws Exception {
        return this.map.entrySet().stream()
            .filter(entry -> new UncheckedScalar<>(entry.getKey()).value())
            .map(Map.Entry::getValue)
            .findFirst()
            .orElse(this.other)
            .value();
    }
}
