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
import org.cactoos.BiFunc;

/**
 * Matrix addition.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of first matrix
 * @param <Y> Type of second matrix
 * @param <Z> Type of result matrix
 * @since 0.1
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors"
    }
)
public final class MatrixAdd<X, Y, Z> extends MatrixEnvelope<Z> {

    /**
     * Ctor.
     * @param first First matrix
     * @param second Second matrix
     * @param add Addition
     */
    public MatrixAdd(final Matrix<X> first, final Matrix<Y> second,
        final BiFunc<X, Y, Z> add) {
        super(() -> {
            final int rows = new RowNumber<>(first).value();
            final int cols = new ColumnNumber<>(first).value();
            final X[][] left = first.asArray();
            final Y[][] right = second.asArray();
            final Z[][] result = (Z[][]) new Object[rows][cols];
            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    result[row][col] = add.apply(
                        left[row][col],
                        right[row][col]
                    );
                }
            }
            return new MatrixOf<>(result);
        });
    }
}
