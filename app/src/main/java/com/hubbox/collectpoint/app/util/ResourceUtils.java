package com.hubbox.collectpoint.app.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by VMakarenko on 27.04.2016.
 */
public class ResourceUtils {
    public static String getStringResourceByName(Context context, String aString) {
        String packageName = context.getPackageName();
        int resId = context.getResources().getIdentifier(aString, "string", packageName);
        return context.getString(resId);
    }

    public static File constructPicName() {
        String basicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getAbsolutePath() + "/HubBoxPics/";
        mkDirIfNescessary(new File(basicPath));
        String localPath = System.currentTimeMillis() + ".jpg";
        return new File(basicPath + localPath);
    }

    public static File mkDirIfNescessary(File file) {
        if (!file.exists()) {
            _mkDirIfNescessary(file);
        }
        return file;
    }

    private static void _mkDirIfNescessary(File file) {
        while (!file.mkdir()) {
            _mkDirIfNescessary(
                    new File(
                            file.getAbsolutePath()
                                    .substring(0, file.getAbsolutePath().lastIndexOf('/')
                                    )
                    ));
        }
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromPath(String path,
                                                         int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap bmImg = BitmapFactory.decodeFile(path, options);
        try {
            ExifInterface exif = new ExifInterface(path);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
            } else if (orientation == 3) {
                matrix.postRotate(180);
            } else if (orientation == 8) {
                matrix.postRotate(270);
            }
            bmImg = Bitmap.createBitmap(bmImg, 0, 0, bmImg.getWidth(), bmImg.getHeight(), matrix, true); // rotating bitmap
        } catch (Exception e) {

        }
        return bmImg;
    }
}



