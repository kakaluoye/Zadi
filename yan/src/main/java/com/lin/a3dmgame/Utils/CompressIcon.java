package com.lin.a3dmgame.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by my on 2016/7/9.
 */
public class CompressIcon {
    /**
     * 调用这方法，得到压缩之后的图片
     *
     * @param data
     * @param iconweith
     * @param iconheight
     * @return
     */
    public static Bitmap getCompressionImage(byte[] data, int iconweith, int iconheight) {
//        Bitmap bitmap = null;

        //三个参数的Bitmap对象   这是原始图片
//        Bitmap initBitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        //=true  不获得图片本身，只得大小。但这样得到的Bitmap参数需要一个选项Option来设置下是否需要图片，默认的Bitmap肯定是需要的，值为false,这里设置成true。
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap beforeBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);//和initBitmap一样。
        //四个参数的Bitmap图片对象，里面有options对象，从它可以不经过图片得到图片的大小等信息。

//        int width = 0;
//        int height = 0;
//        width = options.outWidth;
//        height = options.outHeight;
        //没缩放的图片宽高（由options对象获得）

        options.inSampleSize = calculateSampleSize(options, iconweith, iconheight);
        //options对象调用inSampleSize是设置图片压缩比，它是int类型的值。所以方法也是返回int。
        //options都是用的一个对象，但是里面的参数options是保存原始的宽高，调用的这个自定义方法得到的options含有现在压缩之后的宽高。
        options.inJustDecodeBounds = false;
        Bitmap afterBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        //options对象里面有图片的宽高。再由Bitmap工厂制作图片。
        return afterBitmap;
    }

    /**
     * 计算图片的压缩比
     *
     * @param options    options对象里面有图片的宽高。
     * @param iconweith  这俩是目标宽高。
     * @param iconheight
     * @return
     */
    public static int calculateSampleSize(BitmapFactory.Options options, int iconweith, int iconheight) {
        int width = options.outWidth;
        int height = options.outHeight;

        int sampleSize = 1;

        if (width > iconweith || height > iconheight) {
            int widthRadio = Math.round((float) width / iconweith);
            int heightRadio = Math.round((float) height / iconheight);

            sampleSize = widthRadio > heightRadio ? heightRadio : widthRadio;
        }

        return sampleSize;
    }


}
