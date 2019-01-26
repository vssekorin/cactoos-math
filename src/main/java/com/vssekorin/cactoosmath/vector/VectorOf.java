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
package com.vssekorin.cactoosmath.vector;

import com.vssekorin.cactoosmath.Vector;

/**
 * Vector of.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of vector
 * @since 0.3
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.LooseCoupling",
        "unchecked"
    }
)
public final class VectorOf<T> extends VectorEnvelope<T> {

    /**
     * Ctor.
     * @param origin Origin vector
     * @param length New Length
     */
    public VectorOf(final Vector<T> origin, final int length) {
        this(() -> {
            final T[] vector = origin.asArray();
            final T[] result = (T[]) new Object[length];
            System.arraycopy(vector, 0, result, 0, length);
            return result;
        });
    }

    /**
     * Ctor.
     * @param origin Origin vector
     * @param length New length
     * @param value Default value
     */
    public VectorOf(final Vector<T> origin, final int length, final T value) {
        this(() -> {
            final T[] vector = origin.asArray();
            final T[] result = (T[]) new Object[length];
            for (int ind = 0; ind < length; ++ind) {
                if (ind < vector.length) {
                    result[ind] = vector[ind];
                } else {
                    result[ind] = value;
                }
            }
            return result;
        });
    }

    /**
     * Ctor.
     * @param src Array
     */
    @SafeVarargs
    public VectorOf(final T... src) {
        this(() -> src);
    }

    /**
     * Ctor.
     * @param src Vector
     */
    public VectorOf(final Vector<T> src) {
        super(() -> src);
    }
}
