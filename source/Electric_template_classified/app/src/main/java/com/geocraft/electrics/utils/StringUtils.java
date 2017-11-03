package com.geocraft.electrics.utils;

import com.huace.log.logger.L;

import java.util.Locale;

/**
 * 字符串工具类
 */
public class StringUtils {
    private static final Locale LOC = Locale.ENGLISH;

    public StringUtils() {
    }

    /**
     * 字符串转浮点数，格式不对或空指针，返回0
     */
    public static int string2int(String str) {
        try {
            return Integer.valueOf(str);
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 字符串转浮点数，格式不对或空指针，返回默认值
     */
    public static int string2int(String string, int defaultValue) {
        int v;
        try {
            if (string == null) {
                return defaultValue;
            }
            v = Integer.valueOf(string);
        } catch (Exception e) {
            L.printException(e);
            return defaultValue;
        }
        return v;
    }

    public static String int2String(int v, int width) {
        String format = "%0" + String.valueOf(width) + "d";
        return String.format(LOC, format, v);
    }
}
