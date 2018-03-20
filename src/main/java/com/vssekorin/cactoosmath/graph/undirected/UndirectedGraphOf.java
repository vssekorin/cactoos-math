package com.vssekorin.cactoosmath.graph.undirected;

import com.vssekorin.cactoosmath.graph.UndirectedGraph;
import java.util.List;
import java.util.Map;

public final class UndirectedGraphOf<T> extends UndirectedGraphEnvelope<T> {

    public UndirectedGraphOf(final Map<T, List<T>> src) {
        this(() -> src);
    }

    public UndirectedGraphOf(final UndirectedGraph<T> src) {
        super(() -> src);
    }
}
