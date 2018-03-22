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
package com.vssekorin.cactoosmath.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.cactoos.iterable.IterableOf;

/**
 * Set of.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Element type
 * @since 0.2
 */
public final class SetOf<T> extends SetEnvelope<T> {

    /**
     * Ctor.
     * @param src An array
     */
    @SafeVarargs
    public SetOf(final T... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src An {@link Iterator}
     */
    public SetOf(final Iterator<T> src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src An {@link Iterable}
     */
    public SetOf(final Iterable<T> src) {
        super(() -> {
            final Set<T> set = new HashSet<>();
            src.forEach(set::add);
            return set;
        });
    }
}
