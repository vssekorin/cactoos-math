package com.vssekorin.cactoosmath.matrix;

import com.vssekorin.cactoosmath.Matrix;
import org.cactoos.Func;

public final class MappedRow<T> extends MatrixEnvelope<T> {

    @SuppressWarnings("unchecked")
    public MappedRow(final Matrix<T> src, final int number,
        final Func<T, T> map) {
        super(() -> () -> {
            final int rows = new NmbRows<>(src).value();
            final int cols = new NmbColumns<>(src).value();
            final T[][] result = (T[][]) new Object[rows][cols];
            final T[][] array = src.asArray();
            for (int row = 0; row < rows; row++) {
                if (row == number) {
                    for (int col = 0; col < cols; col++) {
                        result[row][col] = map.apply(array[row][col]);
                    }
                } else {
                    System.arraycopy(array[row], 0, result[row], 0, cols);
                }
            }
            return result;
        });
    }
}
