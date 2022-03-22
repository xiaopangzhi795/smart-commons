/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang.tuple;

import java.util.Map;

/**
 * @ClassName: ImmutablePair
 * @Decription:
 * @Author: rubik
 *  rubik create ImmutablePair.java of 2022/3/22 5:58 下午
 */
public final class ImmutablePair<L, R> extends Pair<L, R> {

    /**
     * An empty array.
     * <p>
     * Consider using {@link #emptyArray()} to avoid generics warnings.
     * </p>
     *
     * @since 3.10.
     */
    public static final ImmutablePair<?, ?>[] EMPTY_ARRAY = new ImmutablePair[0];

    /**
     * An immutable pair of nulls.
     * This is not defined with generics to avoid warnings in call sites.
     */
    @SuppressWarnings("rawtypes")
    private static final ImmutablePair NULL = of(null, null);

    /** Serialization version */
    private static final long serialVersionUID = 4954918890077093841L;

    /**
     * Returns the empty array singleton that can be assigned without compiler warning.
     *
     * @param <L> the left element type
     * @param <R> the right element type
     * @return the empty array singleton that can be assigned without compiler warning.
     *
     * @since 3.10.
     */
    @SuppressWarnings("unchecked")
    public static <L, R> ImmutablePair<L, R>[] emptyArray() {
        return (ImmutablePair<L, R>[]) EMPTY_ARRAY;
    }

    /**
     * Returns an immutable pair of nulls.
     *
     * @param <L> the left element of this pair. Value is {@code null}.
     * @param <R> the right element of this pair. Value is {@code null}.
     * @return an immutable pair of nulls.
     * @since 3.6
     */
    public static <L, R> ImmutablePair<L, R> nullPair() {
        return NULL;
    }

    /**
     * <p>Creates an immutable pair of two objects inferring the generic types.</p>
     *
     * <p>This factory allows the pair to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L> the left element type
     * @param <R> the right element type
     * @param left  the left element, may be null
     * @param right  the right element, may be null
     * @return a pair formed from the two parameters, not null
     */
    public static <L, R> ImmutablePair<L, R> of(final L left, final R right) {
        return new ImmutablePair<>(left, right);
    }

    /**
     * <p>Creates an immutable pair from an existing pair.</p>
     *
     * <p>This factory allows the pair to be created using inference to
     * obtain the generic types.</p>
     *
     * @param <L> the left element type
     * @param <R> the right element type
     * @param pair the existing pair.
     * @return a pair formed from the two parameters, not null
     * @since 3.10
     */
    public static <L, R> ImmutablePair<L, R> of(final Map.Entry<L, R> pair) {
        final L left;
        final R right;
        if (pair != null) {
            left = pair.getKey();
            right = pair.getValue();
        } else {
            left = null;
            right = null;
        }
        return new ImmutablePair<>(left, right);
    }

    /** Left object */
    public final L left;

    /** Right object */
    public final R right;

    /**
     * Create a new pair instance.
     *
     * @param left  the left value, may be null
     * @param right  the right value, may be null
     */
    public ImmutablePair(final L left, final R right) {
        super();
        this.left = left;
        this.right = right;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public L getLeft() {
        return left;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public R getRight() {
        return right;
    }

    /**
     * <p>Throws {@code UnsupportedOperationException}.</p>
     *
     * <p>This pair is immutable, so this operation is not supported.</p>
     *
     * @param value  the value to set
     * @return never
     * @throws UnsupportedOperationException as this operation is not supported
     */
    @Override
    public R setValue(final R value) {
        throw new UnsupportedOperationException();
    }

}