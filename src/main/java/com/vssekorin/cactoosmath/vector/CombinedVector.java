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
 * Combined vector.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of first vector items
 * @param <Y> Type of second vector items
 * @param <Z> Type of result vector items
 * @since 0.3
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.LooseCoupling"
    }
)
public final class CombinedVector<X, Y, Z> extends VectorEnvelope<Z> {

    /**
     * Ctor.
     * @param fst First vector
     * @param snd Second vector
     * @param func Combined binary function
     */
    public CombinedVector(final Vector<X> fst, final Vector<Y> snd,
        final BiFunc<X, Y, Z> func) {
        this(fst, snd, new BiFuncFunc<>(func));
    }

    /**
     * Ctor.
     * @param fst First vector
     * @param snd Second vector
     * @param func Combined function
     */
    @SuppressWarnings("unchecked")
    public CombinedVector(final Vector<X> fst, final Vector<Y> snd,
        final Func<X, Func<Y, Z>> func) {
        super(() -> () -> {
            final X[] first = fst.asArray();
            final Y[] second = snd.asArray();
            final Z[] result = (Z[]) new Object[first.length];
            for (int ind = 0; ind < first.length; ++ind) {
                result[ind] = func.apply(first[ind]).apply(second[ind]);
            }
            return result;
        });
    }
}
