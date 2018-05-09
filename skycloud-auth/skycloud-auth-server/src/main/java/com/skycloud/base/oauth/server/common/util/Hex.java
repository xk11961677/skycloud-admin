package com.skycloud.base.oauth.server.common.util;

import java.math.BigInteger;

/**
 * 十六进制功能类
 *
 */
public class Hex {
    /**
     * 16进制字符串转换为字节数组
     *
     * @param data 字符串
     * @return 转换后字节数组
     */
    public static byte[] decode(String data) throws Exception {
        try {
            return org.bouncycastle.util.encoders.Hex.decode(data);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 字节数组转换为16进制字符串
     *
     * @param data 字节数组
     * @return 转换后字符串
     */
    public static String encode(byte[] data) {
        return new String(org.bouncycastle.util.encoders.Hex.encode(data));
    }

    /**
     * 无符号16进制字符串转换为整数
     *
     * @param hexString 16进制字符串
     * @return 转换后整数
     */
    public static int hexToInt(String hexString) {
        //必须用BigInteger，否则容易溢出了 “1236SDFS”
        return new BigInteger(hexString, 16).intValue();
    }


}
