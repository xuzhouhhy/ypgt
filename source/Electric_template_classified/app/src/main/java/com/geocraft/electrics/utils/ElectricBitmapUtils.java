package com.geocraft.electrics.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;

import com.geocraft.electrics.R;
import com.geocraft.electrics.app.ElectricApplication_;
import com.huace.log.logger.L;

import java.io.IOException;

import common.geocraft.untiltools.Tools;

/**
 * Created by Administrator on 2016/6/6.
 */
public class ElectricBitmapUtils {

	public static Bitmap getItemBitmap(String iconPath) {
		Context context = ElectricApplication_.getInstance().getApplicationContext();
		int size = (int) (context.getResources().getDimension(R.dimen.bitmap_size));
		Bitmap bitmap = Tools.getBitmap(iconPath);
		bitmap = ThumbnailUtils.extractThumbnail(bitmap, size, size);
		if (bitmap == null) {
			bitmap = Tools.getBitmap(context, R.mipmap.ic_tip);
		}
		return bitmap;
	}

	private static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			L.printException(e);
		}
		return degree;
	}

	private static Bitmap rotateImageView(int angle, Bitmap bitmap) {
		Matrix matrix = new Matrix();
		matrix.postRotate(angle);

		return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
	}

	public static Bitmap getCorrectDirectionBitmap(String filePath) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 4;
		Bitmap temp = BitmapFactory.decodeFile(filePath, options);
		if (temp != null) {
			int angle = readPictureDegree(filePath);
			return ThumbnailUtils.extractThumbnail(rotateImageView(angle, temp), 135, 180);
		} else {
			return null;
		}
	}
}
