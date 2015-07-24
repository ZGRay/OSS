package com.oss.storage;

import android.support.v4.util.LruCache;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

/**
 * @author Layen.ZH
 *         create at 2015/7/23 16:52
 */
public class FileStorageMemoryCache implements IStorage {
    private static final String TAG = "memcahce";
    private LruCache<String, byte[]> mMemoryCache;
    // Default memory cache size in kilobytes
    private static final int DEFAULT_MEM_CACHE_SIZE = 1024 * 1024 * 1; // 1MB
    public int maxMemCacheSize;
    private FileStorage fileStorage;

    public FileStorageMemoryCache(File rootDirectory, int maxMemCacheSize, int maxCacheSizeInBytes) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory, maxCacheSizeInBytes);
        initialize();
    }

    public FileStorageMemoryCache(File rootDirectory, int maxMemCacheSize) {
        this.maxMemCacheSize = maxMemCacheSize;
        fileStorage = new FileStorage(rootDirectory);
        initialize();
    }

    public FileStorageMemoryCache(File rootDirectory) {
        this(rootDirectory, DEFAULT_MEM_CACHE_SIZE);
    }

    private void initMemoryCache() {
        mMemoryCache = new LruCache<String, byte[]>(DEFAULT_MEM_CACHE_SIZE) {
            @Override
            public void trimToSize(int maxSize) {
                super.trimToSize(maxSize);
                Log.d(TAG, "当前内存缓存大小 = " + mMemoryCache.size());
            }

            @Override
            protected int sizeOf(String key, byte[] value) {
                return value.length;
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
        try {
            byte[] b = value.getBytes("UTF-8");
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putBytes(String key, byte[] value) {
        try {
            fileStorage.writeCacheHeader(key, value, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putShort(String key, short value) {
        try {
            byte[] b = FileStorage.short2B(value);
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putInt(String key, int value) {
        try {
            byte[] b = FileStorage.int2B(value);
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putLong(String key, long value) {
        try {
            byte[] b = FileStorage.long2B(value);
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putFloat(String key, float value) {
        try {
            byte[] b = FileStorage.int2B(Float.floatToIntBits(value));
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putDouble(String key, double value) {
        try {
            byte[] b = FileStorage.long2B(Double.doubleToLongBits(value));
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putBoolean(String key, boolean value) {
        try {
            byte[] b = value ? new byte[]{1} : new byte[]{0};
            fileStorage.writeCacheHeader(key, b, fileStorage.getFileForKey(key));
            mMemoryCache.put(key, b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void putSerializable(String key, Serializable value) {
        fileStorage.putSerializable(key, value);
        mMemoryCache.put(key, objectToByte(value));
    }

    @Override
    public String getString(String key, String defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes != null && bytes.length > 0) {
            try {
                Log.d(TAG, "Memory cache hit");
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        bytes = fileStorage.readData(key);
        if (bytes != null && bytes.length > 0) {
            try {
                mMemoryCache.put(key, bytes);
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;

    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
            bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return bytes;
        }

        Log.d(TAG, "Memory cache hit");
        return bytes;
    }

    @Override
    public short getShort(String key, short defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
            bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return FileStorage.b2Short(bytes);
        }
        Log.d(TAG, "Memory cache hit");
        return FileStorage.b2Short(bytes);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
             bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return FileStorage.b2Int(bytes);
        }
        Log.d(TAG, "Memory cache hit");
        return FileStorage.b2Int(bytes);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
             bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return FileStorage.b2Long(bytes);
        }
        Log.d(TAG, "Memory cache hit");
        return FileStorage.b2Long(bytes);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
            bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return Float.intBitsToFloat(FileStorage.b2Int(bytes));
        }
        Log.d(TAG, "Memory cache hit");
        return Float.intBitsToFloat(FileStorage.b2Int(bytes));
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
           bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return Double.longBitsToDouble(FileStorage.b2Long(bytes));
        }
        Log.d(TAG, "Memory cache hit");
        return Double.longBitsToDouble(FileStorage.b2Long(bytes));
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        byte[] bytes = mMemoryCache.get(key);
        if (bytes == null || bytes.length == 0) {
             bytes = fileStorage.readData(key);
            if (bytes == null || bytes.length == 0) {
                return defaultValue;
            }
            mMemoryCache.put(key, bytes);
            return bytes[0] == 0 ? false : true;
        }
        Log.d(TAG, "Memory cache hit");
        return bytes[0] == 0 ? false : true;
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, T defaultValue) {
        byte[] b = mMemoryCache.get(key);
        if (b == null || b.length == 0) {
            b = fileStorage.readData(key);
            if (b == null || b.length == 0){
                return defaultValue;
            }
            mMemoryCache.put(key,b);

            return (T) byteToObject(b);
        }
        Log.d(TAG, "Memory cache hit");
        return (T) byteToObject(b);
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

    public byte[] objectToByte(java.lang.Object obj) {
        byte[] bytes = new byte[0];
        try {
            //object to bytearray
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(obj);

            bytes = bo.toByteArray();

            bo.close();
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (bytes);
    }

    private static java.lang.Object byteToObject(byte[] bytes) {
        java.lang.Object obj = null;
        try {
            //bytearray to object
            ByteArrayInputStream bi = new ByteArrayInputStream(bytes);
            ObjectInputStream oi = new ObjectInputStream(bi);

            obj = oi.readObject();

            bi.close();
            oi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
}

