/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2022 Vseslav Sekorin
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
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.vssekorin.cactoosmath.matrix;

import com.vssekorin.cactoosmath.Matrix;
import java.util.Iterator;
import org.cactoos.Func;
import org.cactoos.iterable.IterableOf;

/**
 * Matrix of.
 *
 * @param <T> Type of matrix
 * @since 0.1
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.OnlyOneConstructorShouldDoInitialization",
        "unchecked"
    }
)
public final class MatrixOf<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param rows Number of rows
     * @param cols Number of columns
     * @param src An array of some elements
     */
    public MatrixOf(final Number rows, final Number cols, final T... src) {
        this(rows, cols, new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param rows Number of rows
     * @param cols Number of columns
     * @param src An {@link Iterator}
     */
    public MatrixOf(final Number rows, final Number cols,
        final Iterator<T> src) {
        this(rows, cols, new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param rows Number of rows
     * @param cols Number of columns
     * @param src An {@link Iterable}
     */
    public MatrixOf(final Number rows, final Number cols,
        final Iterable<T> src) {
        this(() -> {
            final T[][] result =
                (T[][]) new Object[rows.intValue()][cols.intValue()];
            final Iterator<T> iterator = src.iterator();
            for (int row = 0; row < rows.intValue(); ++row) {
                for (int col = 0; col < cols.intValue(); ++col) {
                    result[row][col] = iterator.next();
                }
            }
            return result;
        });
    }

    /**
     * Ctor.
     * @param func Filling function
     * @param rows Number of rows
     * @param cols Number of columns
     */
    public MatrixOf(final Func<Number, Func<Number, T>> func,
        final Number rows, final Number cols) {
        this(() -> {
            final T[][] result =
                (T[][]) new Object[rows.intValue()][cols.intValue()];
            for (int row = 0; row < rows.intValue(); ++row) {
                for (int col = 0; col < cols.intValue(); ++col) {
                    result[row][col] = func.apply(row).apply(col);
                }
            }
            return result;
        });
    }

    /**
     * Ctor.
     * @param src Array
     */
    @SuppressWarnings("PMD.UseVarargs")
    public MatrixOf(final T[][] src) {
        this(() -> src);
    }

    /**
     * Ctor.
     * @param src Matrix
     */
    public MatrixOf(final Matrix<T> src) {
        super(src);
    }
}
