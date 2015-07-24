package com.oss.storage;

import android.support.v4.util.LruCache;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.Serializable;

/**
 * @author Layen.ZH
 *         create at 2015/7/23 16:52
 */
public class FileStorageMemache implements IStorage {
    private static final String TAG = "memcahce";
    private LruCache<String, Object> mMemoryCache;
    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 1024 * 1; // 1MB
    public int maxMemCacheSize;
    private FileStorage fileStorage;

    public FileStorageMemache(File rootDirectory, int maxMemCacheSize, int maxCacheSizeInBytes) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory, maxCacheSizeInBytes);
        initialize();
    }

    public FileStorageMemache(File rootDirectory, int maxMemCacheSize) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory);
        initialize();
    }

    public FileStorageMemache(File rootDirectory) {
        this(rootDirectory, DEFAULT_MEM_CACHE_SIZE);
    }

    private void initMemoryCache() {
        mMemoryCache = new LruCache<String, Object>(DEFAULT_MEM_CACHE_SIZE) {
            @Override
            public void trimToSize(int maxSize) {
                super.trimToSize(maxSize);
                Log.d(TAG, "当前内存缓存大小 = " + mMemoryCache.size());
            }

            @Override
            protected int sizeOf(String key, Object value) {
                return FileStorage.objectToByte(value).length;
            }
        };
        fileStorage.initialize();
    }

    @Override
    public void initialize() {
        initMemoryCache();
    }

    @Override
    public void putString(String key, String value) {
        fileStorage.putString(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putBytes(String key, byte[] value) {
        fileStorage.putBytes(key, value);
        mMemoryCache.put(key, value);

    }

    @Override
    public void putShort(String key, short value) {
        fileStorage.putShort(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putInt(String key, int value) {
        fileStorage.putInt(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putLong(String key, long value) {
        fileStorage.putLong(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putFloat(String key, float value) {
        fileStorage.putFloat(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putDouble(String key, double value) {
        fileStorage.putDouble(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        fileStorage.putBoolean(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public void putSerializable(String key, Serializable value) {
        fileStorage.putSerializable(key, value);
        mMemoryCache.put(key, value);
    }

    @Override
    public String getString(String key, String defaultValue) {
        String value = (String) mMemoryCache.get(key);
        if (!TextUtils.isEmpty(value)) {
            return value;
        }
        value = fileStorage.getString(key, defaultValue);
        if (!value.equals(defaultValue)) {
            mMemoryCache.put(key, value);
        }
        return value;

    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        Object o = mMemoryCache.get(key);
        byte[] value = o == null ? null : (byte[]) o;
        if (value == null || value.length == 0) {
            value = fileStorage.readData(key);
            if (value == null || value.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, value);
            return value;
        }

        Log.d(TAG, "Memory cache hit");
        return value;
    }

    @Override
    public short getShort(String key, short defaultValue) {
        Object o = mMemoryCache.get(key);
        short value;
        if (o == null) {
            value = fileStorage.getShort(key, defaultValue);
            if (value!=defaultValue) {
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (short) o;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        Object o = mMemoryCache.get(key);
        int value;
        if (o == null){
            value = fileStorage.getInt(key, defaultValue);
            if (value!=defaultValue){
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (int) o;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        Object o = mMemoryCache.get(key);
        long value;
        if (o == null){
            value = fileStorage.getLong(key, defaultValue);
            if (value!=defaultValue){
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (long) o;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        Object o = mMemoryCache.get(key);
        float value;
        if (o == null){
            value = fileStorage.getFloat(key, defaultValue);
            if (value!=defaultValue){
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (float) o;
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        Object o = mMemoryCache.get(key);
        double value;
        if (o == null){
            value = fileStorage.getDouble(key, defaultValue);
            if (value!=defaultValue){
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (double) o;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        Object o = mMemoryCache.get(key);
        boolean value;
        if (o == null){
            value = fileStorage.getBoolean(key, defaultValue);
            if (value!=defaultValue){
                mMemoryCache.put(key, value);
            }
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (boolean) o;
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, T defaultValue) {
        Object o = mMemoryCache.get(key);
        T value;
        if (o == null){
            byte[] bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0){
                return defaultValue;
            }
            value = (T) FileStorage.byteToObject(bytes);
            return value;
        }
        Log.d(TAG, "Memory cache hit");
        return (T) o;
    }

    @Override
    public void remove(String key) {
        mMemoryCache.remove(key);
        fileStorage.remove(key);
    }

    @Override
    public void clear() {
        mMemoryCache.evictAll();
        fileStorage.clear();
    }


}

