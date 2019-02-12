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
import com.vssekorin.cactoosmath.Vector;

/**
 * Matrix multiplication.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of elements
 * @since 0.3
 */
public final class MatrixWithColumn<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param mtx Origin matrix
     * @param vct Column
     * @param nmb Number of column
     */
    @SuppressWarnings("unchecked")
    public MatrixWithColumn(final Matrix<T> mtx, final Vector<T> vct,
        final int nmb) {
        super(() -> () -> {
            final T[][] matrix = mtx.asArray();
            final T[] vector = vct.asArray();
            final T[][] result = (T[][]) new Object[matrix.length][matrix[0].length];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (row == nmb) {
                        result[row][col] = vector[col];
                    } else {
                        result[row][col] = matrix[row][col];
                    }
                }
            }
            return result;
        });
    }
}
