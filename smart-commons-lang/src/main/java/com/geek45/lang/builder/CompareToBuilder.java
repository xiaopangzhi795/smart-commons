/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang.builder;

import java.util.Comparator;

/**
 * @ClassName: CompareToBuilder
 * @Decription:
 * @Author: rubik
 *  rubik create CompareToBuilder.java of 2022/3/22 5:59 下午
 */
public class CompareToBuilder {

    private int comparison;

    public CompareToBuilder() {
        super();
        comparison = 0;
    }

    public CompareToBuilder append(final Object lhs, final Object rhs) {
        return append(lhs, rhs, null);
    }

    public CompareToBuilder append(final Object lhs, final Object rhs, final Comparator<?> comparator) {
        if (comparison != 0) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null) {
            comparison = -1;
            return this;
        }
        if (rhs == null) {
            comparison = 1;
            return this;
        }
        if (lhs.getClass().isArray()) {
            // factor out array case in order to keep method small enough to be inlined
            appendArray(lhs, rhs, comparator);
        } else {
            // the simple case, not an array, just test the element
            if (comparator == null) {
                @SuppressWarnings("unchecked") // assume this can be done; if not throw CCE as per Javadoc
                final Comparable<Object> comparable = (Comparable<Object>) lhs;
                comparison = comparable.compareTo(rhs);
            } else {
                @SuppressWarnings("unchecked") // assume this can be done; if not throw CCE as per Javadoc
                final Comparator<Object> comparator2 = (Comparator<Object>) comparator;
                comparison = comparator2.compare(lhs, rhs);
            }
        }
        return this;
    }

    private void appendArray(final Object lhs, final Object rhs, final Comparator<?> comparator) {
        // switch on type of array, to dispatch to the correct handler
        // handles multi dimensional arrays
        // throws a ClassCastException if rhs is not the correct array type
        if (lhs instanceof long[]) {
            append((long[]) lhs, (long[]) rhs);
        } else if (lhs instanceof int[]) {
            append((int[]) lhs, (int[]) rhs);
        } else if (lhs instanceof short[]) {
            append((short[]) lhs, (short[]) rhs);
        } else if (lhs instanceof char[]) {
            append((char[]) lhs, (char[]) rhs);
        } else if (lhs instanceof byte[]) {
            append((byte[]) lhs, (byte[]) rhs);
        } else if (lhs instanceof double[]) {
            append((double[]) lhs, (double[]) rhs);
        } else if (lhs instanceof float[]) {
            append((float[]) lhs, (float[]) rhs);
        } else if (lhs instanceof boolean[]) {
            append((boolean[]) lhs, (boolean[]) rhs);
        } else {
            // not an array of primitives
            // throws a ClassCastException if rhs is not an array
            append((Object[]) lhs, (Object[]) rhs, comparator);
        }
    }

    public int toComparison() {
        return comparison;
    }
}