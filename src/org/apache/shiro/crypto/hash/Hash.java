//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.crypto.hash;


import org.apache.shiro.crypto.ByteSource;

public interface Hash extends ByteSource {
    String getAlgorithmName();

    ByteSource getSalt();

    int getIterations();
}
