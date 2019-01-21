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
import com.vssekorin.cactoosmath.func.BiFuncFunc;
import org.cactoos.BiFunc;
import org.cactoos.Func;

/**
 * Matrix multiplication.
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
public final class MatrixProd<X, Y, Z> extends MatrixEnvelope<Z> {

    /**
     * Ctor.
     * @param first First matrix
     * @param second Second matrix
     * @param mult Multiplication
     * @param add Addition
     * @param zero Identity element for addition
     * @checkstyle ParameterNumberCheck (10 lines)
     */
    public MatrixProd(final Matrix<X> first, final Matrix<Y> second,
        final Func<X, Func<Y, Z>> mult, final BiFunc<Z, Z, Z> add,
        final Z zero) {
        this(first, second, mult, new BiFuncFunc<>(add), zero);
    }

    /**
     * Ctor.
     * @param first First matrix
     * @param second Second matrix
     * @param mult Multiplication
     * @param add Addition
     * @param zero Identity element for addition
     * @checkstyle ParameterNumberCheck (10 lines)
     */
    public MatrixProd(final Matrix<X> first, final Matrix<Y> second,
        final BiFunc<X, Y, Z> mult, final Func<Z, Func<Z, Z>> add,
        final Z zero) {
        this(first, second, new BiFuncFunc<>(mult), add, zero);
    }

    /**
     * Ctor.
     * @param first First matrix
     * @param second Second matrix
     * @param mult Multiplication
     * @param add Addition
     * @param zero Identity element for addition
     * @checkstyle ParameterNumberCheck (10 lines)
     */
    public MatrixProd(final Matrix<X> first, final Matrix<Y> second,
        final BiFunc<X, Y, Z> mult, final BiFunc<Z, Z, Z> add,
        final Z zero) {
        this(
            first, second,
            new BiFuncFunc<>(mult), new BiFuncFunc<>(add),
            zero
        );
    }

    /**
     * Ctor.
     * @param first First matrix
     * @param second Second matrix
     * @param mult Multiplication
     * @param add Addition
     * @param zero Identity element for addition
     * @checkstyle ParameterNumberCheck (30 lines)
     */
    @SuppressWarnings("unchecked")
    public MatrixProd(final Matrix<X> first, final Matrix<Y> second,
        final Func<X, Func<Y, Z>> mult, final Func<Z, Func<Z, Z>> add,
        final Z zero) {
        super(() -> () -> {
            final int rows = new NmbRows<>(first).value();
            final int inners = new NmbColumns<>(first).value();
            final int cols = new NmbColumns<>(second).value();
            final X[][] left = first.asArray();
            final Y[][] right = second.asArray();
            final Z[][] result = (Z[][]) new Object[rows][cols];
            //@checkstyle NestedForDepthCheck (10 lines)
            for (int row = 0; row < rows; ++row) {
                for (int col = 0; col < cols; ++col) {
                    result[row][col] = zero;
                    for (int inner = 0; inner < inners; ++inner) {
                        result[row][col] = add
                            .apply(result[row][col])
                            .apply(mult
                                .apply(left[row][inner])
                                .apply(right[inner][col])
                            );
                    }
                }
            }
            return result;
        });
    }
}
