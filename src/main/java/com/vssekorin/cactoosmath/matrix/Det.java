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
package com.vssekorin.cactoosmath.matrix;

import com.vssekorin.cactoosmath.Matrix;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * The determinant of a matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class Det<T> implements Scalar<T> {

    /**
     * Matrix.
     */
    private final Matrix<T> matrix;

    /**
     * Multiplication.
     */
    private final Func<T, Func<T, T>> mult;

    /**
     * Addition.
     */
    private final Func<T, Func<T, T>> add;

    /**
     * Subtractive.
     */
    private final Func<T, Func<T, T>> sub;

    /**
     * Ctor.
     * @param src Matrix
     * @param mlt Multiplication
     * @param addit Addition
     * @param subtr Subtractive
     * @checkstyle ParameterNumberCheck (10 lines)
     */
    public Det(final Matrix<T> src, final Func<T, Func<T, T>> mlt,
        final Func<T, Func<T, T>> addit, final Func<T, Func<T, T>> subtr) {
        this.matrix = src;
        this.mult = mlt;
        this.add = addit;
        this.sub = subtr;
    }

    @Override
    public T value() throws Exception {
        final int cols = new NmbColumns<>(this.matrix).value();
        final T result;
        if (cols == 2) {
            result = new Det2<>(this.matrix, this.mult, this.sub).value();
        } else {
            result = new DetN<>(
                this.matrix, this.mult, this.add, this.sub
            ).value();
        }
        return result;
    }
}
