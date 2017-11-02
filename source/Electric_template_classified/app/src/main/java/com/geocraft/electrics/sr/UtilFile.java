package com.geocraft.electrics.sr;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by zhongshibu02 on 2017/10/29.
 */

public class UtilFile {

    /**
     * 复制单个文件，传统方式
     *
     * @param srcFile 包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest 目标文件路径 如：E:/phsftp/tgt/abc.txt
     * @throws IOException
     */
    public static void copyFile(String srcFile, String dirDest)
            throws IOException {
        if (null == srcFile || srcFile.isEmpty()) {
            return;
        }
        FileInputStream in = new FileInputStream(srcFile);
        FileOutputStream out = new FileOutputStream(dirDest);
        int len;
        byte buffer[] = new byte[1024];
        while ((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }
}
