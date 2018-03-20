package com.vssekorin.cactoosmath.graph.directed;

import com.vssekorin.cactoosmath.graph.DirectedGraph;
import java.util.List;
import java.util.Map;

public final class DirectedGraphOf<T> extends DirectedGraphEnvelope<T> {

    public DirectedGraphOf(final Map<T, List<T>> src) {
        this(() -> src);
    }

    public DirectedGraphOf(final DirectedGraph<T> src) {
        super(() -> src);
    }
}
