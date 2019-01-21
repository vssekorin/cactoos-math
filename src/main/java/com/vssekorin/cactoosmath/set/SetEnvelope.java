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
package com.vssekorin.cactoosmath.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Set envelope.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Element type
 * @since 0.2
 * @checkstyle AbstractClassNameCheck (500 lines)
 */
@SuppressWarnings(
    {
        "PMD.TooManyMethods",
        "PMD.AbstractNaming"
    }
)
public abstract class SetEnvelope<T> implements Set<T> {

    /**
     * Set.
     */
    private final UncheckedScalar<Set<T>> set;

    /**
     * Ctor.
     * @param slr The scalar
     */
    public SetEnvelope(final Scalar<Set<T>> slr) {
        this.set = new UncheckedScalar<>(slr);
    }

    @Override
    public final int size() {
        return this.set.value().size();
    }

    @Override
    public final boolean isEmpty() {
        return this.set.value().isEmpty();
    }

    @Override
    public final boolean contains(final Object obj) {
        return this.set.value().contains(obj);
    }

    @Override
    public final Iterator<T> iterator() {
        return this.set.value().iterator();
    }

    @Override
    public final Object[] toArray() {
        return this.set.value().toArray();
    }

    @Override
    @SuppressWarnings("PMD.UseVarargs")
    public final <E> E[] toArray(final E[] arr) {
        return this.set.value().toArray(arr);
    }

    @Override
    public final boolean containsAll(final Collection<?> col) {
        return this.set.value().containsAll(col);
    }

    @Override
    public final boolean add(final T elem) {
        throw new UnsupportedOperationException(
            "#add(T elem): the set is ready only"
        );
    }

    @Override
    public final boolean remove(final Object obj) {
        throw new UnsupportedOperationException(
            "#remove(): the set is ready only"
        );
    }

    @Override
    public final boolean addAll(final Collection<? extends T> col) {
        throw new UnsupportedOperationException(
            "#addAll(Collection<? extends T> col): the set is ready only"
        );
    }

    @Override
    public final boolean retainAll(final Collection<?> col) {
        throw new UnsupportedOperationException(
            "#retainAll(Collection<?> col): the set is ready only"
        );
    }

    @Override
    public final boolean removeAll(final Collection<?> col) {
        throw new UnsupportedOperationException(
            "#removeAll(Collection<?> col): the set is ready only"
        );
    }

    @Override
    public final void clear() {
        throw new UnsupportedOperationException(
            "#clear(): the set is ready only"
        );
    }
}
