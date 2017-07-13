//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.shiro.subject.PrincipalCollection;

public class CollectionUtils {
    public CollectionUtils() {
    }

    public static <E> Set<E> asSet(E... elements) {
        if(elements != null && elements.length != 0) {
            LinkedHashSet set = new LinkedHashSet(elements.length * 4 / 3 + 1);
            Collections.addAll(set, elements);
            return set;
        } else {
            return Collections.emptySet();
        }
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static boolean isEmpty(Map m) {
        return m == null || m.isEmpty();
    }

    public static int size(Collection c) {
        return c != null?c.size():0;
    }

    public static int size(Map m) {
        return m != null?m.size():0;
    }

    public static boolean isEmpty(PrincipalCollection principals) {
        return principals == null || principals.isEmpty();
    }

    public static <E> List<E> asList(E... elements) {
        if(elements != null && elements.length != 0) {
            int capacity = computeListCapacity(elements.length);
            ArrayList list = new ArrayList(capacity);
            Collections.addAll(list, elements);
            return list;
        } else {
            return Collections.emptyList();
        }
    }

    static int computeListCapacity(int arraySize) {
        return (int)Math.min(5L + (long)arraySize + (long)(arraySize / 10), 2147483647L);
    }
}
