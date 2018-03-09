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
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Matrix without one row.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class RemoveRow<T> extends MatrixEnvelope<T> {

    /**
     * Ctor.
     * @param src The source
     * @param nmb Row number
     */
    public RemoveRow(final Matrix<T> src, final Scalar<Number> nmb) {
        this(src, new UncheckedScalar<>(nmb).value());
    }

    /**
     * Ctor.
     * @param src The source
     * @param nmb Row number
     */
    public RemoveRow(final Matrix<T> src, final Number nmb) {
        super(() -> () -> {
            final int rows = new NmbRows<>(src).value();
            final int cols = new NmbColumns<>(src).value();
            final T[][] array = src.asArray();
            final T[][] result = (T[][]) new Object[rows - 1][cols];
            for (int row = 0; row < nmb.intValue(); ++row) {
                System.arraycopy(array[row], 0, result[row], 0, cols);
            }
            for (int row = nmb.intValue() + 1; row < rows; ++row) {
                System.arraycopy(array[row], 0, result[row - 1], 0, cols);
            }
            return result;
        });
    }
}
