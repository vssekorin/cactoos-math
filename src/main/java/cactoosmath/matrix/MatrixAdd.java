/**
 * MIT License
 *
 * Copyright (c) 2017 Vseslav Sekorin
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
package cactoosmath.matrix;

import cactoosmath.Matrix;
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
public final class MatrixAdd<X, Y, Z> implements Matrix<Z> {

    /**
     * First matrix.
     */
    private final Matrix<X> first;

    /**
     * Second matrix.
     */
    private final Matrix<Y> second;

    /**
     * Addition.
     */
    private final BiFunc<X, Y, Z> add;

    /**
     * Ctor.
     * @param fst First matrix
     * @param snd Second matrix
     * @param func Addition
     */
    public MatrixAdd(final Matrix<X> fst, final Matrix<Y> snd,
        final BiFunc<X, Y, Z> func) {
        this.first = fst;
        this.second = snd;
        this.add = func;
    }

    @Override
    public Z[][] asArray() throws Exception {
        final int rows = new RowsNumber<>(this.first).value();
        final int cols = new ColumnNumber<>(this.first).value();
        final X[][] left = this.first.asArray();
        final Y[][] right = this.second.asArray();
        final Z[][] result = (Z[][]) new Object[rows][cols];
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                result[row][col] = this.add.apply(
                    left[row][col],
                    right[row][col]
                );
            }
        }
        return result;
    }
}
