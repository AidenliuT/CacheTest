package com.qfeng.cachedemo.chche;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.ImageView;

/**
 * Created by innershows on 16/6/21.
 */
public class ImageLoader {
    private static ImageLoader ourInstance = new ImageLoader();

    private MemoryCache memoryCache;
    private DiskCache diskCache;


    public static ImageLoader getInstance() {
        return ourInstance;
    }

    private ImageLoader() {
        memoryCache = new MemoryCache();
        diskCache = new DiskCache();
    }


    public void display(String url, ImageView iv) {
        if (TextUtils.isEmpty(url)) {
            return;
        }

        //先在内存取
        Bitmap bitmap = memoryCache.get(url);

        if (bitmap != null) {
            //如果有，从内存缓存取
            iv.setImageBitmap(bitmap);
            return;
        }

        //应该开启异步任务，继续流程
        new ImageTask(iv).execute(url);

    }


    private class ImageTask extends AsyncTask<String, Void, Bitmap> {
        private String url;

        private ImageView imageView;

        public ImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //如果不存在，从磁盘缓存取
            url = params[0];

            byte[] ret = diskCache.get(url);

            if (ret != null) {
                //存在，直接返回
                return BitmapFactory.decodeByteArray(ret, 0, ret.length);
            }

            //如果不存在，从网络下载
            ret = Network.download(url);

            if (ret != null) {
                //放在磁盘里面去
                diskCache.put(url, ret);
                return BitmapFactory.decodeByteArray(ret, 0, ret.length);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                return;
            }

            //放置到内存里面去，将结果转为Bitmap，展示在ImageView.放在主线程
            memoryCache.put(url, bitmap);
            imageView.setImageBitmap(bitmap);

            super.onPostExecute(bitmap);
        }
    }


}
