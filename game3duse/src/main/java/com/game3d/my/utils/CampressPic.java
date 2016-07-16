package com.game3d.my.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by my on 2016/7/5.
 */
public class CampressPic {
    public static Bitmap compress(byte[] data,int picwidth,int picheight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap beforbit = BitmapFactory.decodeByteArray(data,0,data.length,options);
        options.inSampleSize = getSample(options,picwidth,picheight);
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length,options);
        return bitmap;
    }
    public static int getSample(BitmapFactory.Options options,int picwidth,int picheight){
        int width = options.outWidth;
        int height = options.outHeight;
        int widthrate = Math.round((float)width/picwidth);
        int heightrate = Math.round((float)height/picheight);
        return widthrate>heightrate?heightrate:widthrate;
    }
}
