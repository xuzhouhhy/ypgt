package com.geocraft.homeapp;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipException;

import common.geocraft.untiltools.FileUtils;
import common.geocraft.untiltools.SDCardManager;
import common.geocraft.untiltools.SPUtils;

/**
 * Copy assets to SD card and unzip.
 * Created by zhoujin on 2015-9-17.
 */
public class AssetsManager {

    static final String VERSION_CODE = "VERSION_CODE";

    public static void   unZipFile(String archive, String decompressDir) throws IOException, FileNotFoundException, ZipException
    {
        BufferedInputStream bi;

        org.apache.tools.zip.ZipFile zf = new org.apache.tools.zip.ZipFile(archive, "GBK");
        Enumeration e = zf.getEntries();
        while(e.hasMoreElements())
        {
            org.apache.tools.zip.ZipEntry ze2 = (org.apache.tools.zip.ZipEntry) e.nextElement();
            String entryName = ze2.getName();
            String path = decompressDir + "/" + entryName;
            if (ze2.isDirectory())
            {
                System.out.println("正在创建解压目录 - " + entryName);
                File decompressDirFile = new File(path);
                if (!decompressDirFile.exists())
                {
                    decompressDirFile.mkdirs();
                }
            }
            else
            {
                System.out.println("正在创建解压文件 - " + entryName);
                String fileDir = path.substring(0, path.lastIndexOf("/"));
                File fileDirFile = new File(fileDir);
                if (!fileDirFile.exists())
                {
                    fileDirFile.mkdirs();
                }
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(decompressDir + "/" + entryName));
                bi = new BufferedInputStream(zf.getInputStream(ze2));
                byte[] readContent = new byte[1024];
                int readCount = bi.read(readContent);
                while (readCount != -1)
                {
                    bos.write(readContent, 0, readCount);
                    readCount = bi.read(readContent);
                }
                bos.close();
            }
        }
        zf.close();
    }



    private static void copyAssets(Context context) throws IOException {
        String fullPath = SDCardManager.GetSDCardPath() + Constants.getAssetsZipFileName();
        OutputStream myOutput = new FileOutputStream(fullPath);
        InputStream myInput = context.getAssets().open(Constants.getAssetsZipFileName());
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
        File zipFile = new File(fullPath);
        unZipFile(fullPath, SDCardManager.GetSDCardPath());
        zipFile.delete();
    }


    public static void initConfigRes(Context context, int version_code_new) throws IOException {

        int version_code_odd = (int) SPUtils.get(context, VERSION_CODE, 0);
        if (version_code_odd == 0) {
            FileUtils.delFolder(Constants.getAppDataFolder());
            copyAssets(context);
            SPUtils.put(context, VERSION_CODE, version_code_new);
        } else {
            boolean assetsExits = FileUtils.existFile(Constants.getAppDataFolder())
                    &&FileUtils.existFile(Constants.getAppDataFolder()+Constants.apk1)
                    &&FileUtils.existFile(Constants.getAppDataFolder()+Constants.apk2);
            if (version_code_new != version_code_odd) {
                if (assetsExits) {
                    deleteReplaceFile(Constants.getAppDataFolder());
                }
                copyAssets(context);
                SPUtils.put(context, VERSION_CODE, version_code_new);
            } else {
                if (!assetsExits) {
                    copyAssets(context);
                    SPUtils.put(context, VERSION_CODE, version_code_new);
                }
            }
        }

    }


    private static void deleteReplaceFile(String path) {
        FileUtils.deleteFile(path);
    }

}
