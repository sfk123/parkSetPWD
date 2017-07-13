//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.subject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PrincipalCollection extends Iterable, Serializable {
    Object getPrimaryPrincipal();

    <T> T oneByType(Class<T> var1);

    <T> Collection<T> byType(Class<T> var1);

    List asList();

    Set asSet();

    Collection fromRealm(String var1);

    Set<String> getRealmNames();

    boolean isEmpty();
}
