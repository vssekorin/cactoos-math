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

/**
 * Float inverse matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.3
 * @checkstyle AvoidInlineConditionalsCheck (500 lines)
 * @checkstyle ReturnCountCheck (500 lines)
 * @checkstyle LineLengthCheck (500 lines)
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.UseVarargs",
        "PMD.OnlyOneReturn"
    }
)
public final class FloatInverse extends MatrixEnvelope<Float> {

    /**
     * Ctor.
     * @param mtx Float matrix
     */
    public FloatInverse(final Matrix<Float> mtx) {
        super(() -> () -> {
            final Float[][] matrix = mtx.asArray();
            final Float[][] inverse = new Float[matrix.length][matrix.length];
            for (int row = 0; row < matrix.length; ++row) {
                for (int col = 0; col < matrix[row].length; ++col) {
                    inverse[row][col] = (float) (Math.pow(-1, row + col)
                        * determinant(minor(matrix, row, col)));
                }
            }
            final float det = 1.0f / determinant(matrix);
            for (int row = 0; row < inverse.length; ++row) {
                for (int col = 0; col <= row; ++col) {
                    final float temp = inverse[row][col];
                    inverse[row][col] = inverse[col][row] * det;
                    inverse[col][row] = temp * det;
                }
            }
            return inverse;
        });
    }

    /**
     * Determinant.
     *
     * @param matrix Matrix
     * @return Determinant
     */
    private static float determinant(final Float[][] matrix) {
        if (matrix.length == 1) {
            return matrix[0][0];
        }
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        float det = 0;
        for (int ind = 0; ind < matrix[0].length; ++ind) {
            det += Math.pow(-1, ind) * matrix[0][ind]
                * determinant(minor(matrix, 0, ind));
        }
        return det;
    }

    /**
     * Minor.
     *
     * @param matrix Matrix
     * @param rnm Row number
     * @param cnm Column number
     * @return Minor
     */
    private static Float[][] minor(final Float[][] matrix, final int rnm, final int cnm) {
        final Float[][] minor = new Float[matrix.length - 1][matrix.length - 1];
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; row != rnm && col < matrix[row].length; ++col) {
                if (col != cnm) {
                    minor[row < rnm ? row : row - 1][col < cnm ? col : col - 1] = matrix[row][col];
                }
            }
        }
        return minor;
    }
}
