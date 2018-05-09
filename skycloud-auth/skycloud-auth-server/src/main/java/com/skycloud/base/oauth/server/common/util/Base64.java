package com.skycloud.base.oauth.server.common.util;

/**
 * Base64算法
 */
public class Base64 {
    /**
     * Base64编码加密
     *
     * @param data 待加密字节数组
     * @return 加密后字符串
     */
    public static String encode(byte[] data) {
        if (data == null) return null;
        return new String(org.bouncycastle.util.encoders.Base64.encode(data));
    }

    /**
     * Base64编码解密
     *
     * @param data 待解密字符串
     * @return 解密后字节数组
     */
    public static byte[] decode(String data) throws Exception {
        if (data == null) return null;
        try {
            return org.bouncycastle.util.encoders.Base64.decode(data.getBytes());
        } catch (RuntimeException e) {
            throw e;
        }
    }
}