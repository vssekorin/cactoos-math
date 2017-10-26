package math;

/**
 * Matrix.
 *
 * @author Vseslav Sekorin (vssekorin@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Matrix<T> {

    /**
     * Convert it to array.
     *
     * @return Array
     */
    T[][] asArray();
}
