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
package com.vssekorin.cactoosmath.match;

import java.util.List;
import org.cactoos.BiFunc;
import org.cactoos.Func;
import org.cactoos.Scalar;
import org.cactoos.func.BiFuncOf;
import org.cactoos.iterable.Mapped;
import org.cactoos.scalar.And;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Case of pattern matching.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <X> Type of first input
 * @param <Y> Type of second input
 * @param <Z> Type of result
 * @since 0.2
 */
public final class Case<X, Y, Z> implements MatchCase<X, Y, Z> {

    /**
     * Test.
     */
    private final BiFunc<X, Y, Boolean> test;

    /**
     * Result.
     */
    private final BiFunc<X, Y, Z> result;

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final Boolean bool, final Z res) {
        this(new BiFuncOf<>(bool), new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final List<Boolean> bool, final Z res) {
        this(new And(new Mapped<>(item -> () -> item, bool)), res);
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     * @todo: Change after https://github.com/yegor256/cactoos/issues/796
     */
    public Case(final Scalar<Boolean> bool, final Scalar<Z> res) {
        this(
            new BiFuncOf<>(new UncheckedScalar<>(bool).value()),
            new BiFuncOf<>(new UncheckedScalar<>(res).value())
        );
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final Scalar<Boolean> bool, final Z res) {
        this(bool, () -> res);
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final Func<X, Boolean> bool, final Func<X, Z> res) {
        this(new BiFuncOf<>(bool), new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final Func<X, Boolean> bool, final Z res) {
        this(new BiFuncOf<>(bool), new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final Func<X, Boolean> bool, final BiFunc<X, Y, Z> res) {
        this(new BiFuncOf<>(bool), res);
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final BiFunc<X, Y, Boolean> bool, final BiFunc<X, Y, Z> res) {
        this.test = bool;
        this.result = res;
    }

    /**
     * Ctor.
     * @param bool Test
     * @param res Result
     */
    public Case(final BiFunc<X, Y, Boolean> bool, final Z res) {
        this(bool, new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param cls Test
     * @param res Result
     */
    public Case(final Class<?> cls, final Z res) {
        this(cls, new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param cls Class
     * @param res Result
     */
    public Case(final Class<?> cls, final Scalar<Z> res) {
        this(cls, new UncheckedScalar<>(res).value());
    }

    /**
     * Ctor.
     * @param cls Class
     * @param res Result
     */
    public Case(final Class<?> cls, final Func<X, Z> res) {
        this(cls, new BiFuncOf<>(res));
    }

    /**
     * Ctor.
     * @param cls Class
     * @param res Result
     */
    public Case(final Class<?> cls, final BiFunc<X, Y, Z> res) {
        this(item -> cls.isAssignableFrom(item.getClass()), res);
    }

    @Override
    public BiFunc<X, Y, Boolean> check() {
        return this.test;
    }

    @Override
    public BiFunc<X, Y, Z> value() {
        return this.result;
    }
}
