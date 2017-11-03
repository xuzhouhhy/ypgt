package com.geocraft.electrics.utils;

import com.huace.log.logger.L;

import java.util.Arrays;

public class AlphabeticalNumber {

    private int mMaxLen = 20;

    public AlphabeticalNumber(int maxLen) {
        super();
        mMaxLen = maxLen;
    }

    public AlphabeticalNumber() {
        super();
    }

    /**
     * @param args
     * @author Lucifer
     */
    public static void main(String[] args) {

        AlphabeticalNumber an = new AlphabeticalNumber(16);

        String str = "z";
        String text = an.next(str, -25);
        System.out.println(text + ",size = " + text.length());
        text = an.next(str, -26);
        System.out.println(text + ",size = " + text.length());
        text = an.next(str, -27);
        System.out.println(text + ",size = " + text.length());
        for (int i = 0; i < 284; ++i) {
            System.out.println(an.next(str, -i));
        }
    }

    public String next(String current, int step) {
        try {
            if (0 == step) {
                return current;
            }
            final int base = 26;
            if (current.length() <= mMaxLen) {
                char[] out = new char[mMaxLen + 2];
                char[] flg = new char[mMaxLen + 2];
                Arrays.fill(out, (char) 0);
                Arrays.fill(flg, (char) 0);

                boolean isSub = step < 0;
                if (isSub) {
                    for (int i = 0; i < mMaxLen; ++i) {
                        out[i] = (char) (base - 1);
                    }
                    out[mMaxLen] = base;
                }

                for (int i = current.length() - 1, o = mMaxLen; i >= 0; --i, --o) {
                    char c = current.charAt(i);
                    flg[o] = start(c);
                    out[o] += (char) (c - flg[o]);
                }

                char s = start();
                for (int i = 0; i < mMaxLen - current.length() + 1; ++i) {
                    flg[i] = s;
                }

                for (int i = mMaxLen; i > 0; --i) {
                    int t = out[i] + step % base;
                    out[i] = (char) (t % base);
                    out[i - 1] += (t / base);
                    step /= base;
                }
                out[0] = (char) (out[0] % base);

                boolean neg = (base - 1 == out[0]);// 字母负的了
                if (!neg) {
                    for (int i = 0; i < mMaxLen + 1; ++i) {
                        out[i] += flg[i];
                    }
                    if (isSub || s >= out[0]) {
                        int i = 1;
                        while (i < mMaxLen + 1 && s >= out[i++]) {
                            ;
                        }
                        return new String(out, i - 1, out.length - i + 1)
                                .trim();
                    }
                }
            }
        } catch (Exception e) {
            L.printException(e);
        }
        return "Z";
    }

    public int getMaxLen() {
        return mMaxLen;
    }

    public void setMaxLen(int maxLen) {
        mMaxLen = maxLen;
    }

    private char start() {
        return 'A' - 1;
    }

    /**
     * @return 基数（比如a-z的基数是a（即97），A-Z的基数为A（即65）
     * @author Lucifer
     */
    private char start(char c) {
        return (char) ((0xe0 & c) + 1);
    }
}
