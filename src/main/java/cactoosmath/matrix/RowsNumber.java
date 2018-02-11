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
import org.cactoos.Scalar;

/**
 * Number of row.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class RowsNumber<T> implements Scalar<Integer> {

    /**
     * Matrix.
     */
    private final T[][] matrix;

    /**
     * Ctor.
     * @param mtrx Matrix.
     */
    public RowsNumber(final Matrix<T> mtrx) {
        this(new UncheckedMatrix<>(mtrx).asArray());
    }

    /**
     * Ctor.
     * @param mtrx Matrix.
     */
    @SuppressWarnings("PMD.UseVarargs")
    public RowsNumber(final T[][] mtrx) {
        this.matrix = mtrx.clone();
    }

    @Override
    public Integer value() throws Exception {
        return this.matrix.length;
    }
}
