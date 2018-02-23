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
import org.cactoos.scalar.UncheckedScalar;

/**
 * Multiplication of two boolean matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class BoolMxMult implements Matrix<Boolean> {

    /**
     * First matrix.
     */
    private final Boolean[][] first;

    /**
     * Second matrix.
     */
    private final Boolean[][] second;

    /**
     * Ctor.
     * @param frst First matrix.
     * @param scnd Second matrix.
     */
    public BoolMxMult(final Matrix<Boolean> frst, final Matrix<Boolean> scnd) {
        this(
            new UncheckedMatrix<>(frst).asArray(),
            new UncheckedMatrix<>(scnd).asArray()
        );
    }

    /**
     * Ctor.
     * @param frst First matrix.
     * @param scnd Second matrix.
     */
    @SuppressWarnings("PMD.UseVarargs")
    public BoolMxMult(final Boolean[][] frst, final Boolean[][] scnd) {
        this.first = frst.clone();
        this.second = scnd.clone();
    }

    @Override
    public Boolean[][] asArray() {
        final int size = new UncheckedScalar<>(
            new RowNumber<>(this.first)
        ).value();
        final Boolean[][] result = new Boolean[size][size];
        //@checkstyle NestedForDepthCheck (10 lines)
        for (int row = 0; row < size; ++row) {
            for (int col = 0; col < size; ++col) {
                result[row][col] = false;
                for (int inner = 0; inner < size; ++inner) {
                    result[row][col] = result[row][col]
                        || this.first[row][inner] && this.second[inner][col];
                }
            }
        }
        return result;
    }
}
