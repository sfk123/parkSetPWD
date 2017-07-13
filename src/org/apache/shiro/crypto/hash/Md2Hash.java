//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.crypto.hash;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.codec.SimpleHash;

public class Md2Hash extends SimpleHash {
    public static final String ALGORITHM_NAME = "MD2";

    public Md2Hash() {
        super("MD2");
    }

    public Md2Hash(Object source) {
        super("MD2", source);
    }

    public Md2Hash(Object source, Object salt) {
        super("MD2", source, salt);
    }

    public Md2Hash(Object source, Object salt, int hashIterations) {
        super("MD2", source, salt, hashIterations);
    }

    public static Md2Hash fromHexString(String hex) {
        Md2Hash hash = new Md2Hash();
        hash.setBytes(Hex.decode(hex));
        return hash;
    }

    public static Md2Hash fromBase64String(String base64) {
        Md2Hash hash = new Md2Hash();
        hash.setBytes(Base64.decode(base64));
        return hash;
    }
}
