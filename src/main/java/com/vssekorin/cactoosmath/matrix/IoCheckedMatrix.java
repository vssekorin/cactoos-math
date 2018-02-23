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
import java.io.IOException;

/**
 * Matrix that doesn't throw checked {@link Exception},
 * but throws {@link IOException} instead.
 *
 * <p> There is no thread-safety guarantee.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class IoCheckedMatrix<T> implements Matrix<T> {

    /**
     * Origin matrix.
     */
    private final Matrix<T> origin;

    /**
     * Ctor.
     * @param matrix Origin matrix
     */
    public IoCheckedMatrix(final Matrix<T> matrix) {
        this.origin = matrix;
    }

    @Override
    @SuppressWarnings
        (
            {
                "PMD.AvoidCatchingGenericException",
                "PMD.AvoidRethrowingException"
            }
        )
    public T[][] asArray() throws IOException {
        try {
            return this.origin.asArray();
            //@checkstyle IllegalCatchCheck (1 line)
        } catch (final IOException | RuntimeException exc) {
            throw exc;
        } catch (final InterruptedException exc) {
            Thread.currentThread().interrupt();
            throw new IOException(exc);
            //@checkstyle IllegalCatchCheck (1 line)
        } catch (final Exception exc) {
            throw new IOException(exc);
        }
    }
}
