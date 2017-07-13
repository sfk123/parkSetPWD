//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package org.apache.shiro.codec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.ByteSource;

public abstract class CodecSupport {
    public static final String PREFERRED_ENCODING = "UTF-8";

    public CodecSupport() {
    }

    public static byte[] toBytes(char[] chars) {
        return toBytes(new String(chars), "UTF-8");
    }

    public static byte[] toBytes(char[] chars, String encoding) throws CodecException {
        return toBytes(new String(chars), encoding);
    }

    public static byte[] toBytes(String source) {
        return toBytes(source, "UTF-8");
    }

    public static byte[] toBytes(String source, String encoding) throws CodecException {
        try {
            return source.getBytes(encoding);
        } catch (UnsupportedEncodingException var4) {
            String msg = "Unable to convert source [" + source + "] to byte array using " + "encoding \'" + encoding + "\'";
            throw new CodecException(msg, var4);
        }
    }

    public static String toString(byte[] bytes) {
        return toString(bytes, "UTF-8");
    }

    public static String toString(byte[] bytes, String encoding) throws CodecException {
        try {
            return new String(bytes, encoding);
        } catch (UnsupportedEncodingException var4) {
            String msg = "Unable to convert byte array to String with encoding \'" + encoding + "\'.";
            throw new CodecException(msg, var4);
        }
    }

    public static char[] toChars(byte[] bytes) {
        return toChars(bytes, "UTF-8");
    }

    public static char[] toChars(byte[] bytes, String encoding) throws CodecException {
        return toString(bytes, encoding).toCharArray();
    }

    protected boolean isByteSource(Object o) {
        return o instanceof byte[] || o instanceof char[] || o instanceof String || o instanceof ByteSource || o instanceof File || o instanceof InputStream;
    }

    protected byte[] toBytes(Object o) {
        if(o == null) {
            String msg = "Argument for byte conversion cannot be null.";
            throw new IllegalArgumentException(msg);
        } else {
            return o instanceof byte[]?(byte[])((byte[])o):(o instanceof ByteSource?((ByteSource)o).getBytes():(o instanceof char[]?toBytes((char[])((char[])o)):(o instanceof String?toBytes((String)o):(o instanceof File?this.toBytes((File)o):(o instanceof InputStream?this.toBytes((InputStream)o):this.objectToBytes(o))))));
        }
    }

    protected String toString(Object o) {
        if(o == null) {
            String msg = "Argument for String conversion cannot be null.";
            throw new IllegalArgumentException(msg);
        } else {
            return o instanceof byte[]?toString((byte[])((byte[])o)):(o instanceof char[]?new String((char[])((char[])o)):(o instanceof String?(String)o:this.objectToString(o)));
        }
    }

    protected byte[] toBytes(File file) {
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        } else {
            try {
                return this.toBytes((InputStream)(new FileInputStream(file)));
            } catch (FileNotFoundException var4) {
                String msg = "Unable to acquire InputStream for file [" + file + "]";
                throw new CodecException(msg, var4);
            }
        }
    }

    protected byte[] toBytes(InputStream in) {
        if(in == null) {
            throw new IllegalArgumentException("InputStream argument cannot be null.");
        } else {
            boolean BUFFER_SIZE = true;
            ByteArrayOutputStream out = new ByteArrayOutputStream(512);
            byte[] buffer = new byte[512];

            byte[] ioe;
            try {
                int bytesRead;
                while((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                ioe = out.toByteArray();
            } catch (IOException var18) {
                throw new CodecException(var18);
            } finally {
                try {
                    in.close();
                } catch (IOException var17) {
                    ;
                }

                try {
                    out.close();
                } catch (IOException var16) {
                    ;
                }

            }

            return ioe;
        }
    }

    protected byte[] objectToBytes(Object o) {
        String msg = "The " + this.getClass().getName() + " implementation only supports conversion to " + "byte[] if the source is of type byte[], char[], String, " + ByteSource.class.getName() + " File or InputStream.  The instance provided as a method " + "argument is of type [" + o.getClass().getName() + "].  If you would like to convert " + "this argument type to a byte[], you can 1) convert the argument to one of the supported types " + "yourself and then use that as the method argument or 2) subclass " + this.getClass().getName() + "and override the objectToBytes(Object o) method.";
        throw new CodecException(msg);
    }

    protected String objectToString(Object o) {
        return o.toString();
    }
}
