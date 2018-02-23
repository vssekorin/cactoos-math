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
package com.vssekorin.cactoosmath.iterator;

import com.vssekorin.cactoosmath.func.BiFuncFunc;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.func.UncheckedFunc;

/**
 * BiSequence.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Elements type
 * @since 0.1
 */
public final class BiSeq<T> implements Iterator<T> {

    /**
     * First element.
     */
    private final T first;

    /**
     * Second element.
     */
    private final T second;

    /**
     * Increment function.
     */
    private final UncheckedFunc<T, Func<T, T>> inc;

    /**
     * Previous element.
     */
    private T prevf;

    /**
     * Previous element.
     */
    private T prevs;

    /**
     * Ctor.
     * @param first First element
     * @param second Second element
     * @param fnc Increment function
     */
    public BiSeq(final T first, final T second, final BiFunc<T, T, T> fnc) {
        this(first, second, new BiFuncFunc<>(fnc));
    }

    /**
     * Ctor.
     * @param first First element
     * @param second Second element
     * @param fnc Increment function
     */
    public BiSeq(final T first, final T second, final Func<T, Func<T, T>> fnc) {
        this.first = first;
        this.second = second;
        this.inc = new UncheckedFunc<>(fnc);
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        final T result;
        if (this.prevf == null || this.prevs == null) {
            if (this.prevf == null) {
                result = this.first;
                this.prevf = this.first;
            } else {
                result = this.second;
                this.prevs = this.second;
            }
        } else {
            result = new UncheckedFunc<>(
                this.inc.apply(this.prevf)
            ).apply(this.prevs);
            this.prevf = this.prevs;
            this.prevs = result;
        }
        return result;
    }
}
