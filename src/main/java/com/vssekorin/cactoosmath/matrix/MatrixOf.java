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

import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;

/**
 * Array as matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class MatrixOf<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param array Array
     */
    @SuppressWarnings("PMD.UseVarargs")
    public MatrixOf(final T[][] array) {
        super(() -> () -> array);
    }

    /**
     * Ctor.
     * @param func Filling function
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public MatrixOf(final Func<Number, Func<Number, T>> func,
        final Number rows, final Number cols) {
        super(() -> () -> (T[][]) fillArray(func, rows, cols));
    }

    /**
     * Fill array.
     *
     * @param func Filling function
     * @param rows Number of rows
     * @param cols Number of columns
     * @param <R> Type of matrix
     * @return Array
     */
    @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
    private static <R> R[][] fillArray(
        final Func<Number, Func<Number, R>> func, final Number rows,
        final Number cols) {
        final R[][] result =
            (R[][]) new Object[rows.intValue()][cols.intValue()];
        for (int row = 0; row < rows.intValue(); ++row) {
            for (int col = 0; col < cols.intValue(); ++col) {
                result[row][col] = new UncheckedFunc<>(
                    new UncheckedFunc<>(func).apply(row)
                ).apply(col);
            }
        }
        return result;
    }
}
