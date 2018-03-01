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

import com.vssekorin.cactoosmath.func.BiFuncFunc;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * Fold left to right.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Elements type
 * @since 0.1
 */
public final class FoldLeft<T> implements Scalar<T> {

    /**
     * Source items.
     */
    private final Iterable<T> items;

    /**
     * Function of two arguments.
     */
    private final Func<T, Func<T, T>> func;

    /**
     * First element.
     */
    private final T first;

    /**
     * Ctor.
     * @param src Source items
     * @param fnc Function
     * @param fst First item
     */
    public FoldLeft(final Iterable<T> src, final BiFunc<T, T, T> fnc,
        final T fst) {
        this(src, new BiFuncFunc<>(fnc), fst);
    }

    /**
     * Ctor.
     * @param src Source items
     * @param fnc Function
     * @param fst First item
     */
    public FoldLeft(final Iterable<T> src, final Func<T, Func<T, T>> fnc,
        final T fst) {
        this.items = src;
        this.func = fnc;
        this.first = fst;
    }

    @Override
    public T value() throws Exception {
        T result = this.first;
        for (final T item : this.items) {
            result = this.func.apply(result).apply(item);
        }
        return result;
    }
}
