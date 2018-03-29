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

/**
 * Matrix with mapped column.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Elements type
 * @since 0.2
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors"
    }
)
public final class MappedColumn<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param src Origin matrix
     * @param number Number of column
     * @param map Map function
     */
    @SuppressWarnings("unchecked")
    public MappedColumn(final Matrix<T> src, final int number,
        final Func<T, T> map) {
        super(() -> () -> {
            final int rows = new NmbRows<>(src).value();
            final int cols = new NmbColumns<>(src).value();
            final T[][] result = (T[][]) new Object[rows][cols];
            final T[][] array = src.asArray();
            for (int col = 0; col < cols; ++col) {
                if (col == number) {
                    for (int row = 0; row < rows; ++row) {
                        result[row][col] = map.apply(array[row][col]);
                    }
                } else {
                    for (int row = 0; row < rows; ++row) {
                        result[row][col] = array[row][col];
                    }
                }
            }
            return result;
        });
    }
}
