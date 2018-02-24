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
import org.cactoos.iterable.Reversed;

/**
 * Applies a function of two arguments to all elements, going right to left.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Elements type
 * @since 0.1
 */
public final class ReduceRight<T> implements Scalar<T> {

    /**
     * Source items.
     */
    private final Iterable<T> items;

    /**
     * Function of two arguments.
     */
    private final Func<T, Func<T, T>> func;

    /**
     * Ctor.
     * @param src Source items
     * @param fnc Function
     */
    public ReduceRight(final Iterable<T> src, final BiFunc<T, T, T> fnc) {
        this(src, new BiFuncFunc<>(fnc));
    }

    /**
     * Ctor.
     * @param src Source items
     * @param fnc Function
     */
    public ReduceRight(final Iterable<T> src, final Func<T, Func<T, T>> fnc) {
        this.items = src;
        this.func = fnc;
    }

    @Override
    public T value() throws Exception {
        return new ReduceLeft<>(new Reversed<>(this.items), this.func).value();
    }
}
