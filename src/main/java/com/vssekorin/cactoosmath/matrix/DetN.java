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
package com.vssekorin.cactoosmath.matrix;

import com.vssekorin.cactoosmath.Matrix;
import org.cactoos.Func;
import org.cactoos.Scalar;

/**
 * The determinant of a N x N matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class DetN<T> implements Scalar<T> {

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
    public DetN(final Matrix<T> src, final Func<T, Func<T, T>> mlt,
        final Func<T, Func<T, T>> addit, final Func<T, Func<T, T>> subtr) {
        this.matrix = src;
        this.mult = mlt;
        this.add = addit;
        this.sub = subtr;
    }

    @Override
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    public T value() throws Exception {
        final int cols = new NmbColumns<>(this.matrix).value();
        final T[][] array = this.matrix.asArray();
        T result = this.mult
            .apply(array[0][0])
            .apply(
                new Det<>(
                    new Minor<>(this.matrix, 0, 0),
                    this.mult, this.add, this.sub
                ).value()
            );
        for (int col = 1; col < cols; ++col) {
            //@checkstyle AvoidInlineConditionalsCheck (1 lines)
            result = (col % 2 == 0 ? this.add : this.sub)
                .apply(result)
                .apply(
                    this.mult
                        .apply(array[0][col])
                        .apply(
                            new Det<>(
                                new Minor<>(this.matrix, 0, col),
                                this.mult, this.add, this.sub
                            ).value()
                        )
                );
        }
        return result;
    }
}
