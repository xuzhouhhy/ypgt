package com.geocraft.electrics.sr;

import java.io.File;

/**
 * Created by zhongshibu02 on 2017/10/29.
 */

public class OpenSystemImportePhotoEventArgs {
    private File mFile;
    private String mCurrentDataSetName = "";

    public OpenSystemImportePhotoEventArgs(File file, String dataSetName) {
        this.mFile = file;
        this.mCurrentDataSetName = dataSetName;
    }

    public File getFile() {
        return mFile;
    }

    public String getCurrentDataSetName() {
        return this.mCurrentDataSetName;
    }
}
