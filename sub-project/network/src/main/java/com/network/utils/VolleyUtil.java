package com.network.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.io.File;

public class VolleyUtil {
    public static RequestQueue sRequestQueue = Volley.newRequestQueue(AppContextUtil.getInstance().getAppContext());
    public static ImageLoader sImageLoader = new ImageLoader(sRequestQueue, new BitmapLruCache());

    public static void loadImage(final ImageView view, String url) {
        loadImage(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (null != response.getBitmap()) {
                    view.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(VolleyError volleyError) {
            }
        });
    }

    public static void loadImage(String url, ImageLoader.ImageListener listener) {
        doCleanAllCache();
        sImageLoader.get(url, listener);
    }

    private static void doCleanAllCache() {
        final String DEFAULT_CACHE_DIR = "volley";
        File cacheDir = new File(AppContextUtil.getInstance().getAppContext().getCacheDir(), DEFAULT_CACHE_DIR);
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static void preloadImage(final String imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }
        loadImage(imageUrl, null);
    }

    public static class BitmapLruCache extends android.support.v4.util.LruCache<String, Bitmap> implements ImageLoader.ImageCache {
        public static int getDefaultLruCacheSize() {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
            final int cacheSize = maxMemory / 8;
            return cacheSize;
        }

        public BitmapLruCache() {
            this(getDefaultLruCacheSize());
        }

        public BitmapLruCache(int sizeInKiloBytes) {
            super(sizeInKiloBytes);
        }

        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            if (bitmap == null) {
                return 0;
            }
            return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
        }

        @Override
        public Bitmap getBitmap(String url) {
            return get(url);
        }

        @Override
        public void putBitmap(String url, Bitmap bitmap) {
            put(url, bitmap);
        }
    }

}
