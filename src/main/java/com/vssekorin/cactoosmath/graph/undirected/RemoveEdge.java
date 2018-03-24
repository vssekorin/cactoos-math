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
package com.vssekorin.cactoosmath.graph.undirected;

import com.vssekorin.cactoosmath.graph.DirectedGraph;
import com.vssekorin.cactoosmath.graph.directed.DirectedGraphEnvelope;
import com.vssekorin.cactoosmath.set.Filtered;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.cactoos.Scalar;
import org.cactoos.scalar.UncheckedScalar;

/**
 * Undirected graph without edge.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @param <T> Type of matrix
 * @since 0.1
 */
public final class RemoveEdge<T> extends DirectedGraphEnvelope<T> {

    /**
     * Ctor.
     * @param src Undirected graph
     * @param edge Edge
     */
    public RemoveEdge(final DirectedGraph<T> src, final Map.Entry<T, T> edge) {
        this(src, edge.getKey(), edge.getValue());
    }

    /**
     * Ctor.
     * @param src Undirected graph
     * @param fst Edge node
     * @param snd Edge node
     */
    public RemoveEdge(final DirectedGraph<T> src, final Scalar<T> fst,
        final Scalar<T> snd) {
        this(
            src,
            new UncheckedScalar<>(fst).value(),
            new UncheckedScalar<>(snd).value()
        );
    }

    /**
     * Ctor.
     * @param src Undirected graph
     * @param fst Edge node
     * @param snd Edge node
     */
    public RemoveEdge(final DirectedGraph<T> src, final T fst, final T snd) {
        super(() -> () -> {
            final Map<T, Set<T>> result = new HashMap<>(src.asMap());
            if (result.containsKey(fst) && result.containsKey(snd)) {
                result.put(
                    fst,
                    new Filtered<>(result.get(fst), item -> !item.equals(snd))
                );
                result.put(
                    snd,
                    new Filtered<>(result.get(snd), item -> !item.equals(fst))
                );
            }
            return result;
        });
    }
}
