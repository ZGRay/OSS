package com.oss.storage;

import java.io.File;
import java.io.Serializable;

/**
 * @author Layen.ZH
 *         create at 2015/7/23 16:52
 */
public class HighSynFileStorageMemche implements IStorage {
    private static final String TAG = "FileStorageMemche";
    EfficientLruCache mMemoryCache;
    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 1024 * 1; // 1MB
    public int maxMemCacheSize;
    private FileStorage fileStorage;

    public HighSynFileStorageMemche(File rootDirectory, int maxMemCacheSize, int maxCacheSizeInBytes) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory, maxCacheSizeInBytes);
        initialize();
    }

    public HighSynFileStorageMemche(File rootDirectory, int maxMemCacheSize) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory);
        initialize();
    }

    public HighSynFileStorageMemche(File rootDirectory) {
        this(rootDirectory, DEFAULT_MEM_CACHE_SIZE);
    }

    private void initMemoryCache() {
        mMemoryCache = new EfficientLruCache(maxMemCacheSize,fileStorage);
    }

    @Override
    public void initialize() {
        initMemoryCache();
    }

    @Override
    public void putString(String key, String value) {
        mMemoryCache.putString(key, value);
    }

    @Override
    public void putBytes(String key, byte[] value) {
        mMemoryCache.putBytes(key, value);

    }

    @Override
    public void putShort(String key, short value) {
        mMemoryCache.putShort(key, value);
    }

    @Override
    public void putInt(String key, int value) {
        mMemoryCache.putInt(key, value);
    }

    @Override
    public void putLong(String key, long value) {
        mMemoryCache.putLong(key, value);
    }

    @Override
    public void putFloat(String key, float value) {
        mMemoryCache.putFloat(key, value);
    }

    @Override
    public void putDouble(String key, double value) {
        mMemoryCache.putDouble(key, value);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        mMemoryCache.putBoolean(key, value);
    }

    @Override
    public void putSerializable(String key, Serializable value) {
        mMemoryCache.putSerializable(key, value);
    }

    @Override
    public String getString(String key, String defaultValue) {

        return mMemoryCache.getString(key, defaultValue);

    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        return mMemoryCache.getBytes(key, defaultValue);
    }

    @Override
    public short getShort(String key, short defaultValue) {
        return mMemoryCache.getShort(key, defaultValue);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return mMemoryCache.getInt(key, defaultValue);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return mMemoryCache.getLong(key, defaultValue);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return mMemoryCache.getFloat(key, defaultValue);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return mMemoryCache.getDouble(key, defaultValue);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return mMemoryCache.getBoolean(key, defaultValue);
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, T defaultValue) {
        return mMemoryCache.getSerializable(key, defaultValue);
    }

    @Override
    public void remove(String key) {
        mMemoryCache.remove(key);
        mMemoryCache.removeDisk(key);
    }
    public void removeCache(String key){
        mMemoryCache.remove(key);
    }
    public void removeDisk(String key){
        mMemoryCache.removeDisk(key);
    }

    @Override
    public void clearAll() {
        mMemoryCache.clearCache();
        mMemoryCache.clearDisk();
    }
    public void clearCache() {
        mMemoryCache.clearCache();
    }
    public void clearDisk() {
        mMemoryCache.clearDisk();
    }



}

