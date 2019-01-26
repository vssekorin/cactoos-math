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
package com.vssekorin.cactoosmath.vector;

import com.vssekorin.cactoosmath.Matrix;
import com.vssekorin.cactoosmath.Vector;

/**
 * Vector is result of multiplication.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.3
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.OnlyOneConstructorShouldDoInitialization",
        "PMD.LooseCoupling"
    }
)
public final class FloatVectorMult extends VectorEnvelope<Float> {

    /**
     * Ctor.
     * @param mtx Float matrix
     * @param vct Float vector
     */
    public FloatVectorMult(final Matrix<Float> mtx, final Vector<Float> vct) {
        super(() -> () -> {
            final Float[][] matrix = mtx.asArray();
            final Float[] vector = vct.asArray();
            final Float[] result = new Float[matrix.length];
            for (int row = 0; row < matrix.length; ++row) {
                result[row] = 0f;
                for (int col = 0; col < vector.length; ++col) {
                    result[row] += matrix[row][col] * vector[col];
                }
            }
            return result;
        });
    }

    /**
     * Ctor.
     * @param vct Float vector
     * @param number Float number
     */
    public FloatVectorMult(final Vector<Float> vct, final Float number) {
        super(() -> () -> {
            final Float[] vector = vct.asArray();
            final Float[] result = new Float[vector.length];
            for (int ind = 0; ind < vector.length; ++ind) {
                result[ind] = vector[ind] * number;
            }
            return result;
        });
    }
}
