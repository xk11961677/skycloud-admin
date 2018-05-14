package com.skycloud.auth.server.common.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;

/**
 *
 */
public class Md5 {
    /**
     * Md5编码
     *
     * @param data 待加密数据
     * @return 加密后16进制字符串
     */
    public static String encode(byte[] data) {
        Digest digest = new MD5Digest();
        byte[] resBuf = new byte[digest.getDigestSize()];
        digest.update(data, 0, data.length);
        digest.doFinal(resBuf, 0);
        return Hex.encode(resBuf);
    }

    /**
     * Md5编码
     *
     * @param data 待加密数据
     * @return 加密后16进制字符串
     */
    public static String encode(String data) {
        return encode(data.getBytes());
    }


}
