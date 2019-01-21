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

import com.vssekorin.cactoosmath.Vector;
import com.vssekorin.cactoosmath.func.BiFuncFunc;
import org.cactoos.BiFunc;
import org.cactoos.Func;

/**
 * Vector subtraction.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of first vector.
 * @param <Y> Type of second vector.
 * @param <Z> Type of result vector.
 * @since 0.3
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.LooseCoupling"
    }
)
public final class VectorSub<X, Y, Z> extends VectorEnvelope<Z> {

    /**
     * Ctor.
     * @param first First vector
     * @param second Second vector
     * @param subt Subtraction
     */
    public VectorSub(final Vector<X> first, final Vector<Y> second,
        final BiFunc<X, Y, Z> subt) {
        this(first, second, new BiFuncFunc<>(subt));
    }

    /**
     * Ctor.
     * @param first First vector
     * @param second Second vector
     * @param subt Subtraction
     */
    @SuppressWarnings("unchecked")
    public VectorSub(final Vector<X> first, final Vector<Y> second,
        final Func<X, Func<Y, Z>> subt) {
        super(() -> () -> {
            final X[] fst = first.asArray();
            final Y[] snd = second.asArray();
            final Z[] result = (Z[]) new Object[fst.length];
            for (int ind = 0; ind < result.length; ++ind) {
                result[ind] = subt.apply(fst[ind]).apply(snd[ind]);
            }
            return result;
        });
    }
}
