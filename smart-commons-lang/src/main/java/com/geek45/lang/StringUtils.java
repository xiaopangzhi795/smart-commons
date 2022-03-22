/**
 * From geek45.com
 * Email to : rubixgeek795@gmail.com
 */
package com.geek45.lang;

/**
 * @ClassName: StringUtils
 * @Decription:
 * @Author: rubik
 *  rubik create StringUtils.java of 2022/3/22 5:44 下午
 */
public class StringUtils {

    public static final String EMPTY = "";
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <p>Checks if any of the CharSequences are empty ("") or null or whitespace only.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.</p>
     *
     * <pre>
     * StringUtils.isAnyBlank((String) null)    = true
     * StringUtils.isAnyBlank((String[]) null)  = false
     * StringUtils.isAnyBlank(null, "foo")      = true
     * StringUtils.isAnyBlank(null, null)       = true
     * StringUtils.isAnyBlank("", "bar")        = true
     * StringUtils.isAnyBlank("bob", "")        = true
     * StringUtils.isAnyBlank("  bob  ", null)  = true
     * StringUtils.isAnyBlank(" ", "bar")       = true
     * StringUtils.isAnyBlank(new String[] {})  = false
     * StringUtils.isAnyBlank(new String[]{""}) = true
     * StringUtils.isAnyBlank("foo", "bar")     = false
     * </pre>
     *
     * @param css  the CharSequences to check, may be null or empty
     * @return {@code true} if any of the CharSequences are empty or null or whitespace only
     * @since 3.2
     */
    public static boolean isAnyBlank(final CharSequence... css) {
        if (CollectionUtils.isEmpty(css)) {
            return false;
        }
        for (final CharSequence cs : css) {
            if (isBlank(cs)) {
                return true;
            }
        }
        return false;
    }
    /**
     * StringUtils.equals(null, null)   = true
     * StringUtils.equals(null, "abc")  = false
     * StringUtils.equals("abc", null)  = false
     * StringUtils.equals("abc", "abc") = true
     * StringUtils.equals("abc", "ABC") = false
     */
    public static final boolean equals(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (null == cs1 || null == cs2) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        if (cs1 instanceof String && cs2 instanceof String) {
            return cs1.equals(cs2);
        }
        final int length = cs1.length();
        for (int i = 0; i < length; i++) {
            if (cs1.charAt(i) != cs2.charAt(i)) {
                return false;
            }
        }
        return true;
    }
    /**
     * <p>Compares given {@code string} to a CharSequences vararg of {@code searchStrings},
     * returning {@code true} if the {@code string} is equal to any of the {@code searchStrings}.</p>
     *
     * <pre>
     * StringUtils.equalsAny(null, (CharSequence[]) null) = false
     * StringUtils.equalsAny(null, null, null)    = true
     * StringUtils.equalsAny(null, "abc", "def")  = false
     * StringUtils.equalsAny("abc", null, "def")  = false
     * StringUtils.equalsAny("abc", "abc", "def") = true
     * StringUtils.equalsAny("abc", "ABC", "DEF") = false
     * </pre>
     *
     * @param string to compare, may be {@code null}.
     * @param searchStrings a vararg of strings, may be {@code null}.
     * @return {@code true} if the string is equal (case-sensitive) to any other element of {@code searchStrings};
     * {@code false} if {@code searchStrings} is null or contains no matches.
     * @since 3.5
     */
    public static boolean equalsAny(final CharSequence string, final CharSequence... searchStrings) {
        if (CollectionUtils.isNotEmpty(searchStrings)) {
            for (final CharSequence next : searchStrings) {
                if (equals(string, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * StringUtils.equalsIgnoreCase(null, null)   = true
     * StringUtils.equalsIgnoreCase(null, "abc")  = false
     * StringUtils.equalsIgnoreCase("abc", null)  = false
     * StringUtils.equalsIgnoreCase("abc", "abc") = true
     * StringUtils.equalsIgnoreCase("abc", "ABC") = true
     */
    public static boolean equalsIgnoreCase(final CharSequence cs1, final CharSequence cs2) {
        if (cs1 == cs2) {
            return true;
        }
        if (cs1 == null || cs2 == null) {
            return false;
        }
        if (cs1.length() != cs2.length()) {
            return false;
        }
        return regionMatches(cs1, true, 0, cs2, 0, cs1.length());
    }



    /**
     * Green implementation of regionMatches.
     *
     * @param cs the {@code CharSequence} to be processed
     * @param ignoreCase whether or not to be case insensitive
     * @param thisStart the index to start on the {@code cs} CharSequence
     * @param substring the {@code CharSequence} to be looked for
     * @param start the index to start on the {@code substring} CharSequence
     * @param length character length of the region
     * @return whether the region matched
     */
    static boolean regionMatches(final CharSequence cs, final boolean ignoreCase, final int thisStart,
                                 final CharSequence substring, final int start, final int length)    {
        if (cs instanceof String && substring instanceof String) {
            return ((String) cs).regionMatches(ignoreCase, thisStart, (String) substring, start, length);
        }
        int index1 = thisStart;
        int index2 = start;
        int tmpLen = length;

        final int srcLen = cs.length() - thisStart;
        final int otherLen = substring.length() - start;

        if (thisStart < 0 || start < 0 || length < 0) {
            return false;
        }

        if (srcLen < length || otherLen < length) {
            return false;
        }

        while (tmpLen-- > 0) {
            final char c1 = cs.charAt(index1++);
            final char c2 = substring.charAt(index2++);

            if (c1 == c2) {
                continue;
            }

            if (!ignoreCase) {
                return false;
            }

            if (Character.toUpperCase(c1) != Character.toUpperCase(c2)
                    && Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                return false;
            }
        }

        return true;
    }

    /**
     * StringUtils.trimToNull(null)          = null
     * StringUtils.trimToNull("")            = null
     * StringUtils.trimToNull("     ")       = null
     * StringUtils.trimToNull("abc")         = "abc"
     * StringUtils.trimToNull("    abc    ") = "abc"
     */
    public static String trimToNull(final String str) {
        final String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     */
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * StringUtils.trim(null)          = null
     * StringUtils.trim("")            = ""
     * StringUtils.trim("     ")       = ""
     * StringUtils.trim("abc")         = "abc"
     * StringUtils.trim("    abc    ") = "abc"
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }


    /**
     * StringUtils.trimToEmpty(null)          = ""
     * StringUtils.trimToEmpty("")            = ""
     * StringUtils.trimToEmpty("     ")       = ""
     * StringUtils.trimToEmpty("abc")         = "abc"
     * StringUtils.trimToEmpty("    abc    ") = "abc"
     */
    public static String trimToEmpty(final String str) {
        return str == null ? EMPTY : str.trim();
    }

    /**
     * StringUtils.replace(null, *, *)        = null
     *        StringUtils.replace("", *, *)          = ""
     *        StringUtils.replace("any", null, *)    = "any"
     *        StringUtils.replace("any", *, null)    = "any"
     *        StringUtils.replace("any", "", *)      = "any"
     *        StringUtils.replace("aba", "a", null)  = "aba"
     *        StringUtils.replace("aba", "a", "")    = "b"
     *        StringUtils.replace("aba", "a", "z")   = "zbz"
     * @param text
     * @param searchString
     * @param replacement
     * @return
     */
    public static String replace(final String text, final String searchString, final String replacement) {
        return replace(text, searchString, replacement, -1);
    }

    /**
     * A null reference passed to this method is a no-op.
     *        StringUtils.replace(null, *, *, *)         = null
     *        StringUtils.replace("", *, *, *)           = ""
     *        StringUtils.replace("any", null, *, *)     = "any"
     *        StringUtils.replace("any", *, null, *)     = "any"
     *        StringUtils.replace("any", "", *, *)       = "any"
     *        StringUtils.replace("any", *, *, 0)        = "any"
     *        StringUtils.replace("abaa", "a", null, -1) = "abaa"
     *        StringUtils.replace("abaa", "a", "", -1)   = "b"
     *        StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     *        StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     *        StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     *        StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * @param text
     * @param searchString
     * @param replacement
     * @param max
     * @return
     */
    public static String replace(final String text, final String searchString, final String replacement, final int max) {
        return replace(text, searchString, replacement, max, false);
    }

    /**
     * A null reference passed to this method is a no-op.
     *        StringUtils.replace(null, *, *, *, false)         = null
     *        StringUtils.replace("", *, *, *, false)           = ""
     *        StringUtils.replace("any", null, *, *, false)     = "any"
     *        StringUtils.replace("any", *, null, *, false)     = "any"
     *        StringUtils.replace("any", "", *, *, false)       = "any"
     *        StringUtils.replace("any", *, *, 0, false)        = "any"
     *        StringUtils.replace("abaa", "a", null, -1, false) = "abaa"
     *        StringUtils.replace("abaa", "a", "", -1, false)   = "b"
     *        StringUtils.replace("abaa", "a", "z", 0, false)   = "abaa"
     *        StringUtils.replace("abaa", "A", "z", 1, false)   = "abaa"
     *        StringUtils.replace("abaa", "A", "z", 1, true)   = "zbaa"
     *        StringUtils.replace("abAa", "a", "z", 2, true)   = "zbza"
     *        StringUtils.replace("abAa", "a", "z", -1, true)  = "zbzz"
     * @param text
     * @param searchString
     * @param replacement
     * @param max
     * @param ignoreCase
     * @return
     */
    private static String replace(final String text, String searchString, final String replacement, int max, final boolean ignoreCase) {
        if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
            return text;
        }
        if (ignoreCase) {
            searchString = searchString.toLowerCase();
        }
        int start = 0;
        int end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
        if (end == INDEX_NOT_FOUND) {
            return text;
        }
        final int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = increase < 0 ? 0 : increase;
        increase *= max < 0 ? 16 : max > 64 ? 64 : max;
        final StringBuilder buf = new StringBuilder(text.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(text, start, end).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = ignoreCase ? indexOfIgnoreCase(text, searchString, start) : indexOf(text, searchString, start);
        }
        buf.append(text, start, text.length());
        return buf.toString();
    }

    /**
     * an empty search CharSequence.
     *        StringUtils.indexOf(null, *, *)          = -1
     *        StringUtils.indexOf(*, null, *)          = -1
     *        StringUtils.indexOf("", "", 0)           = 0
     *        StringUtils.indexOf("", *, 0)            = -1 (except when * = "")
     *        StringUtils.indexOf("aabaabaa", "a", 0)  = 0
     *        StringUtils.indexOf("aabaabaa", "b", 0)  = 2
     *        StringUtils.indexOf("aabaabaa", "ab", 0) = 1
     *        StringUtils.indexOf("aabaabaa", "b", 3)  = 5
     *        StringUtils.indexOf("aabaabaa", "b", 9)  = -1
     *        StringUtils.indexOf("aabaabaa", "b", -1) = 2
     *        StringUtils.indexOf("aabaabaa", "", 2)   = 2
     *        StringUtils.indexOf("abc", "", 9)        = 3
     * @param seq
     * @param searchSeq
     * @param startPos
     * @return
     */
    public static int indexOf(final CharSequence seq, final CharSequence searchSeq, final int startPos) {
        if (seq == null || searchSeq == null) {
            return INDEX_NOT_FOUND;
        }
        return seq.toString().indexOf(searchSeq.toString(), startPos);
    }

    /**
     * an empty search CharSequence.
     *        StringUtils.indexOfIgnoreCase(null, *, *)          = -1
     *        StringUtils.indexOfIgnoreCase(*, null, *)          = -1
     *        StringUtils.indexOfIgnoreCase("", "", 0)           = 0
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
     *        StringUtils.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
     *        StringUtils.indexOfIgnoreCase("abc", "", 9)        = -1
     * @param str
     * @param searchStr
     * @param startPos
     * @return
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int startPos) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (startPos < 0) {
            startPos = 0;
        }
        final int endLimit = str.length() - searchStr.length() + 1;
        if (startPos > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return startPos;
        }
        for (int i = startPos; i < endLimit; i++) {
            if (regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * A null input String returns null.
     *        StringUtils.lowerCase(null)  = null
     *        StringUtils.lowerCase("")    = ""
     *        StringUtils.lowerCase("aBc") = "abc"
     * @param str
     * @return
     */
    public static String lowerCase(final String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    /**
     * represented by empty strings.
     *        StringUtils.join(null)            = null
     *        StringUtils.join([])              = ""
     *        StringUtils.join([null])          = ""
     *        StringUtils.join(["a", "b", "c"]) = "abc"
     *        StringUtils.join([null, "", "a"]) = "a"
     * @param elements
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> String join(final T... elements) {
        return join(elements, null);
    }

    /**
     * Null objects or empty strings within the array are represented by empty strings.
     *        StringUtils.join(null, *)                = null
     *        StringUtils.join([], *)                  = ""
     *        StringUtils.join([null], *)              = ""
     *        StringUtils.join(["a", "b", "c"], "--")  = "a--b--c"
     *        StringUtils.join(["a", "b", "c"], null)  = "abc"
     *        StringUtils.join(["a", "b", "c"], "")    = "abc"
     *        StringUtils.join([null, "", "a"], ',')   = ",,a"
     * @param array
     * @param separator
     * @return
     */
    public static String join(final Object[] array, final String separator) {
        if (array == null) {
            return null;
        }
        return join(array, separator, 0, array.length);
    }

    /**
     * Null objects or empty strings within the array are represented by empty strings.
     *        StringUtils.join(null, *, *, *)                = null
     *        StringUtils.join([], *, *, *)                  = ""
     *        StringUtils.join([null], *, *, *)              = ""
     *        StringUtils.join(["a", "b", "c"], "--", 0, 3)  = "a--b--c"
     *        StringUtils.join(["a", "b", "c"], "--", 1, 3)  = "b--c"
     *        StringUtils.join(["a", "b", "c"], "--", 2, 3)  = "c"
     *        StringUtils.join(["a", "b", "c"], "--", 2, 2)  = ""
     *        StringUtils.join(["a", "b", "c"], null, 0, 3)  = "abc"
     *        StringUtils.join(["a", "b", "c"], "", 0, 3)    = "abc"
     *        StringUtils.join([null, "", "a"], ',', 0, 3)   = ",,a"
     * @param array
     * @param separator
     * @param startIndex
     * @param endIndex
     * @return
     */
    public static String join(final Object[] array, String separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        if (separator == null) {
            separator = EMPTY;
        }

        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }

        final StringBuilder buf = newStringBuilder(noOfItems);

        if (array[startIndex] != null) {
            buf.append(array[startIndex]);
        }

        for (int i = startIndex + 1; i < endIndex; i++) {
            buf.append(separator);

            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

    private static StringBuilder newStringBuilder(final int noOfItems) {
        return new StringBuilder(noOfItems * 16);
    }

    /**
     * Whitespace is defined by Character.isWhitespace(char).
     *        StringUtils.isNotBlank(null)      = false
     *        StringUtils.isNotBlank("")        = false
     *        StringUtils.isNotBlank(" ")       = false
     *        StringUtils.isNotBlank("bob")     = true
     *        StringUtils.isNotBlank("  bob  ") = true
     * @param cs
     * @return
     */
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * Integer.parseInt or Long.parseLong, e.g. if the value is outside the range for int or long respectively.
     *        StringUtils.isNumeric(null)   = false
     *        StringUtils.isNumeric("")     = false
     *        StringUtils.isNumeric("  ")   = false
     *        StringUtils.isNumeric("123")  = true
     *        StringUtils.isNumeric("१२३")  = true
     *        StringUtils.isNumeric("12 3") = false
     *        StringUtils.isNumeric("ab2c") = false
     *        StringUtils.isNumeric("12-3") = false
     *        StringUtils.isNumeric("12.3") = false
     *        StringUtils.isNumeric("-123") = false
     *        StringUtils.isNumeric("+123") = false
     * @param cs
     * @return
     */
    public static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * StringUtils.isNotEmpty(null)      = false
     *        StringUtils.isNotEmpty("")        = false
     *        StringUtils.isNotEmpty(" ")       = true
     *        StringUtils.isNotEmpty("bob")     = true
     *        StringUtils.isNotEmpty("  bob  ") = true
     * @param cs
     * @return
     */
    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     *        StringUtils.isBlank(null)      = true
     *        StringUtils.isBlank("")        = true
     *        StringUtils.isBlank(" ")       = true
     *        StringUtils.isBlank("bob")     = false
     *        StringUtils.isBlank("  bob  ") = false
     * @param cs
     * @return
     */
    public static boolean isBlank(final CharSequence cs) {
        final int strLen = length(cs);
        if (strLen == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static int length(final CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
