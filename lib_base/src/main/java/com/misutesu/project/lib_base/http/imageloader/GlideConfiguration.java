package com.misutesu.project.lib_base.http.imageloader;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;

@GlideModule(glideName = "GlideNGA")
public class GlideConfiguration extends AppGlideModule {
    private final String IMAGE_DISK_CACHE_DIR_NAME = "Glide";
    private final long IMAGE_DISK_CACHE_MAX_SIZE = 200 * 1024 * 1024;

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context).build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);

        builder.setDiskCache(() -> DiskLruCacheWrapper.create(new File(context.getCacheDir(), IMAGE_DISK_CACHE_DIR_NAME), IMAGE_DISK_CACHE_MAX_SIZE))
                .setMemoryCache(new LruResourceCache(customMemoryCacheSize))
                .setBitmapPool(new LruBitmapPool(customBitmapPoolSize))
                .setDefaultRequestOptions(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).disallowHardwareConfig());
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
