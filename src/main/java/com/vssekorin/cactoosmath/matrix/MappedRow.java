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
 * Matrix with mapped row.
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
public final class MappedRow<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param src Origin matrix
     * @param number Number of row
     * @param map Map function
     */
    @SuppressWarnings("unchecked")
    public MappedRow(final Matrix<T> src, final int number,
        final Func<T, T> map) {
        super(() -> () -> {
            final int rows = new NmbRows<>(src).value();
            final int cols = new NmbColumns<>(src).value();
            final T[][] result = (T[][]) new Object[rows][cols];
            final T[][] array = src.asArray();
            for (int row = 0; row < rows; ++row) {
                if (row == number) {
                    for (int col = 0; col < cols; ++col) {
                        result[row][col] = map.apply(array[row][col]);
                    }
                } else {
                    System.arraycopy(array[row], 0, result[row], 0, cols);
                }
            }
            return result;
        });
    }
}
