package com.bitcoin.juwan.baselibrary.utils;

/**
 * FileName：StringUtils
 * Create By：liumengqiang
 * Description：TODO
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param stringValue
     * @return
     */
    public static boolean stringIsEmpty(String stringValue) {
        if(stringValue == null || stringValue.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
