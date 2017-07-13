//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.codec;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md2Hash;

/** @deprecated */
@Deprecated
public abstract class AbstractHash extends CodecSupport implements Hash, Serializable {
    private byte[] bytes;
    private transient String hexEncoded;
    private transient String base64Encoded;

    public AbstractHash() {
        this.bytes = null;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    public AbstractHash(Object source) throws CodecException {
        this(source, (Object)null, 1);
    }

    public AbstractHash(Object source, Object salt) throws CodecException {
        this(source, salt, 1);
    }

    public AbstractHash(Object source, Object salt, int hashIterations) throws CodecException {
        this.bytes = null;
        this.hexEncoded = null;
        this.base64Encoded = null;
        byte[] sourceBytes = this.toBytes(source);
        byte[] saltBytes = null;
        if(salt != null) {
            saltBytes = this.toBytes(salt);
        }

        byte[] hashedBytes = this.hash(sourceBytes, saltBytes, hashIterations);
        this.setBytes(hashedBytes);
    }

    public abstract String getAlgorithmName();

    public byte[] getBytes() {
        return this.bytes;
    }

    public void setBytes(byte[] alreadyHashedBytes) {
        this.bytes = alreadyHashedBytes;
        this.hexEncoded = null;
        this.base64Encoded = null;
    }

    protected MessageDigest getDigest(String algorithmName) throws UnknownAlgorithmException {
        try {
            return MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException var4) {
            String msg = "No native \'" + algorithmName + "\' MessageDigest instance available on the current JVM.";
            throw new UnknownAlgorithmException(msg, var4);
        }
    }

    protected byte[] hash(byte[] bytes) {
        return this.hash(bytes, (byte[])null, 1);
    }

    protected byte[] hash(byte[] bytes, byte[] salt) {
        return this.hash(bytes, salt, 1);
    }

    protected byte[] hash(byte[] bytes, byte[] salt, int hashIterations) throws UnknownAlgorithmException {
        MessageDigest digest = this.getDigest(this.getAlgorithmName());
        if(salt != null) {
            digest.reset();
            digest.update(salt);
        }

        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - 1;

        for(int i = 0; i < iterations; ++i) {
            digest.reset();
            hashed = digest.digest(hashed);
        }

        return hashed;
    }

    public String toHex() {
        if(this.hexEncoded == null) {
            this.hexEncoded = Hex.encodeToString(this.getBytes());
        }

        return this.hexEncoded;
    }

    public String toBase64() {
        if(this.base64Encoded == null) {
            this.base64Encoded = Base64.encodeToString(this.getBytes());
        }

        return this.base64Encoded;
    }

    public String toString() {
        return this.toHex();
    }

    public boolean equals(Object o) {
        if(o instanceof Hash) {
            Hash other = (Hash)o;
            return Arrays.equals(this.getBytes(), other.getBytes());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return this.bytes != null && this.bytes.length != 0?Arrays.hashCode(this.bytes):0;
    }

    private static void printMainUsage(Class<? extends AbstractHash> clazz, String type) {
        System.out.println("Prints an " + type + " hash value.");
        System.out.println("Usage: java " + clazz.getName() + " [-base64] [-salt <saltValue>] [-times <N>] <valueToHash>");
        System.out.println("Options:");
        System.out.println("\t-base64\t\tPrints the hash value as a base64 String instead of the default hex.");
        System.out.println("\t-salt\t\tSalts the hash with the specified <saltValue>");
        System.out.println("\t-times\t\tHashes the input <N> number of times");
    }

    private static boolean isReserved(String arg) {
        return "-base64".equals(arg) || "-times".equals(arg) || "-salt".equals(arg);
    }

    static int doMain(Class<? extends AbstractHash> clazz, String[] args) {
        String simple = clazz.getSimpleName();
        int index = simple.indexOf("Hash");
        String type = simple.substring(0, index).toUpperCase();
        if(args != null && args.length >= 1 && args.length <= 7) {
            boolean hex = true;
            String salt = null;
            int times = 1;
            String text = args[args.length - 1];

            String hashed;
            for(int hash = 0; hash < args.length; ++hash) {
                hashed = args[hash];
                if(hashed.equals("-base64")) {
                    hex = false;
                } else {
                    String e;
                    if(hashed.equals("-salt")) {
                        if(hash + 1 >= args.length - 1) {
                            e = "Salt argument must be followed by a salt value.  The final argument is reserved for the value to hash.";
                            System.out.println(e);
                            printMainUsage(clazz, type);
                            return -1;
                        }

                        salt = args[hash + 1];
                    } else if(hashed.equals("-times")) {
                        if(hash + 1 >= args.length - 1) {
                            e = "Times argument must be followed by an integer value.  The final argument is reserved for the value to hash";
                            System.out.println(e);
                            printMainUsage(clazz, type);
                            return -1;
                        }

                        try {
                            times = Integer.valueOf(args[hash + 1]).intValue();
                        } catch (NumberFormatException var13) {
                            String msg = "Times argument must be followed by an integer value.";
                            System.out.println(msg);
                            printMainUsage(clazz, type);
                            return -1;
                        }
                    }
                }
            }

            Md2Hash var14 = new Md2Hash(text, salt, times);
            hashed = hex?var14.toHex():var14.toBase64();
            System.out.print(hex?"Hex: ":"Base64: ");
            System.out.println(hashed);
            return 0;
        } else {
            printMainUsage(clazz, type);
            return -1;
        }
    }
}
