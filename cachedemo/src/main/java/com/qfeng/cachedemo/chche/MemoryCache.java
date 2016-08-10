package com.qfeng.cachedemo.chche;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 内存缓存
 */
public class MemoryCache {

    private LruCache<String, Bitmap> lruCache;

    public MemoryCache() {

        //lruCache用来存放数据到内存里面，需要设置一个最大的大小。
        //当前app可以扩展的最大内存。 16m ， 32m  ， 64m
        long maxMemory = Runtime.getRuntime().maxMemory();

        //可以根据具体需求来设置大小
        int size = (int) (maxMemory / 8);


        //实例化缓存
        lruCache = new LruCache<String, Bitmap>(size) {

            //计算存入的这一个条目的大小。
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };


        //这句话表示只能存10个条目
        //lruCache = new LruCache<>(10);
    }

    /**
     * 从内存缓存里面获取
     *
     * @param key 键，实际上就是图片地址
     * @return Bitmap
     */
    public Bitmap get(String key) {
        Bitmap bitmap = lruCache.get(key);
        return bitmap;
    }

    /**
     * 存入到缓存里面
     *
     * @param key   键，图片地址
     * @param value 图片地址对应的Bitmap
     */
    public void put(String key, Bitmap value) {
        lruCache.put(key, value);
    }


}
