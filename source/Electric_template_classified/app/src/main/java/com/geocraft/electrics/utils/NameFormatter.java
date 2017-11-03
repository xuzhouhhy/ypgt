package com.geocraft.electrics.utils;


import com.huace.log.logger.L;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 命名工具类
 */
public class NameFormatter {

    private static AlphabeticalNumber st_alphabeticalNumber = new AlphabeticalNumber();

    public NameFormatter() {
        super();
    }

    /**
     * 名称后缀数字加step
     */
    public static String getNextNameWithDigitSuffix(String name, int step) {
        if (null == name || name.isEmpty()) {
            return "1";
        } else {
            boolean isDigitSuffix = Character.isDigit(name.charAt(name.length() - 1));
            String namePrefix = name;
            if (!isDigitSuffix) {
                namePrefix = namePrefix + "0";
            }
            return getNextName(namePrefix, step);
        }
    }

    /**
     * 获取递增名称，后缀为数字，则数字递增，后缀为字母，则字母递增
     */
    public static String getNextName(String name, int step) {
        try {
            if (null == name || name.isEmpty()) {
                return "1";
            } else {
                boolean isDigitSuffix = Character.isDigit(name.charAt(name.length() - 1));
                int cnt;
                if (isDigitSuffix) {
                    cnt = getDigitSuffixCount(name);
                } else {
                    cnt = getAlphabetSuffixCount(name);
                }
                if (0 == cnt) {
                    if (step >= 0) {
                        return name + "1";
                    } else {
                        return name + "10000";
                    }
                }
                String prefix = "";
                String suffix;
                if (cnt == name.length()) {
                    suffix = name;
                } else {
                    prefix = name.substring(0, name.length() - cnt);
                    suffix = name.substring(name.length() - cnt, name.length());
                }
                if (isDigitSuffix) {
                    int v = StringUtils.string2int(suffix) + step;
                    if (v >= 0) {
                        int width = suffix.length();
                        return prefix + StringUtils.int2String(v, width);
                    } else {
                        if (!prefix.isEmpty()) {
                            return prefix;
                        } else {
                            return "10000";
                        }
                    }
                } else {
                    return prefix + nextAlphabetical(suffix, step);
                }
            }
        } catch (Exception e) {
            L.printException(e);
            return "1";
        }
    }

    /**
     * 根据已有点名获取下一个带数字后缀的点名
     */
    public static String getNextNameWithDigitSuffix(List<String> existNames, String prefix) {
        return getNextNameWithDigitSuffix(existNames, prefix, 1);
    }

    /**
     * 根据已有点名获取下一个带数字后缀的点名
     */
    public static String getNextNameWithDigitSuffix(List<String> existNames,
                                                    String prefix, int step) {
        int cnt = 0;
        for (String name : existNames) {
            if (!name.startsWith(prefix)) {
                continue;
            }
            String str = name.substring(prefix.length());
            int suffix = StringUtils.string2int(str);
            if (suffix > cnt) {
                cnt = suffix;
            }
        }
        cnt = cnt + step;
        return prefix + cnt;
    }

    /**
     * 根据已有点名获取下一个带数字后缀的点名（格式化）
     *
     * @param format DecimalFormat 格式化符
     */
    public static String getNextNameWithDigitSuffixFormat(List<String> existNames,
                                                          String prefix, int step, String format) {
        int cnt = 0;
        for (String name : existNames) {
            if (!name.startsWith(prefix)) {
                continue;
            }
            String str = name.substring(prefix.length());
            int suffix = StringUtils.string2int(str);
            if (suffix > cnt) {
                cnt = suffix;
            }
        }
        cnt = cnt + step;
        String formatValue = new DecimalFormat(format).format(cnt);
        return prefix + formatValue;
    }

    /**
     * @return digit suffix
     */
    private static int getDigitSuffixCount(String str) {
        int cnt = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            Character c = str.charAt(i);
            if (Character.isDigit(c)) {
                ++cnt;
            } else {
//                if ('-' == c) {
//                    if (i <= 0 || !Character.isDigit(str.charAt(i-1))) {
//                        ++cnt;
//                    }
//                }
                break;
            }
        }
        return cnt;
    }

    private static int getAlphabetSuffixCount(String str) {
        int cnt = 0;
        for (int i = str.length() - 1; i >= 0; --i) {
            char c = str.charAt(i);
            if (!isLetter(c)) {
                break;
            }
            ++cnt;
        }
        return cnt;
    }

    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private static String nextAlphabetical(String str, int step) {
        return st_alphabeticalNumber.next(str, step);
    }
}
