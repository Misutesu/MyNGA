package com.misutesu.project.lib_base.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.misutesu.project.lib_base.R;

public class PaletteUtils {

    interface PaletteListener {
        void getColor(@ColorInt int color);
    }

    public static void getColor(ImageView imageView, PaletteListener paletteListener) {
        if (paletteListener == null) {
            return;
        }
        imageView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        imageView.setDrawingCacheEnabled(false);

        getColor(imageView.getContext(), bitmap, paletteListener);
    }

    public static void getColor(Context context, Bitmap bitmap, PaletteListener paletteListener) {
        if (paletteListener == null) {
            return;
        }
        Palette.from(bitmap).generate(palette -> {
            if (palette != null) {
                paletteListener.getColor(palette.getVibrantColor(ThemeUtils.getColor(context, R.attr.colorPrimary)));
            }
        });
    }
}
