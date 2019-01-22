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
import org.cactoos.Func;

/**
 * Mapped vector.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of source item
 * @param <Y> Type of target item
 * @since 0.3
 */
@SuppressWarnings(
    {
        "PMD.CallSuperInConstructor",
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.LooseCoupling"
    }
)
public final class MappedVector<X, Y> extends VectorEnvelope<Y> {

    /**
     * Ctor.
     * @param map Function
     * @param origin Origin array
     */
    @SafeVarargs
    public MappedVector(final Func<X, Y> map, final X... origin) {
        this(map, new VectorOf<>(origin));
    }

    /**
     * Ctor.
     * @param map Function
     * @param origin Origin vector
     */
    @SuppressWarnings("unchecked")
    public MappedVector(final Func<X, Y> map, final Vector<X> origin) {
        super(() -> () -> {
            final X[] array = origin.asArray();
            final Y[] mapped = (Y[]) new Object[array.length];
            for (int ind = 0; ind < mapped.length; ++ind) {
                mapped[ind] = map.apply(array[ind]);
            }
            return mapped;
        });
    }
}
