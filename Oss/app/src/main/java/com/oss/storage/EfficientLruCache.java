
package com.oss.storage;

import android.text.TextUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class EfficientLruCache implements IStorage {
    private final LinkedHashMap<String, Object> map;
    private FileStorage fileStorage;
    private int size;
    private int maxSize;

    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();


    public EfficientLruCache(int maxSize, FileStorage fileStorage) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = maxSize;
        this.fileStorage = fileStorage;
        this.map = new LinkedHashMap<String, Object>(0, 0.75f, true);
        this.initialize();
    }

    /**
     * Sets the size of the cache.
     *
     * @param maxSize The new maximum size.
     */
    public void resize(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        w.lock();
        try {
            this.maxSize = maxSize;
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    private final Object get(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        return map.get(key);

    }

    private final Object put(String key, Object value) {
        Object previous;
        size += safeSizeOf(key, value);
        previous = map.put(key, value);
        if (previous != null) {
            size -= safeSizeOf(key, previous);
        }
        return previous;
    }


    /**
     * Remove the eldest entries until the total of remaining entries is at or
     * below the requested size.
     *
     * @param maxSize the maximum size of the cache before returning. May be -1
     *                to evict even 0-sized elements.
     */
    public void trimToSize(int maxSize) {
        while (true) {
            String key;
            Object value;
            w.lock();
            try {
                if (size < 0 || (map.isEmpty() && size != 0)) {
                    throw new IllegalStateException(getClass().getName()
                            + ".sizeOf() is reporting inconsistent results!");
                }

                if (size <= maxSize || map.isEmpty()) {
                    break;
                }

                Map.Entry<String, Object> toEvict = map.entrySet().iterator().next();
                key = toEvict.getKey();
                value = toEvict.getValue();
                map.remove(key);
                size -= safeSizeOf(key, value);
            } finally {
                w.unlock();
            }

        }
    }

    @Override
    public void initialize() {
        w.lock();
        try {
            fileStorage.initialize();
        } finally {
            w.unlock();
        }

    }

    @Override
    public void putString(String key, String value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putString(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putBytes(String key, byte[] value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putBytes(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putShort(String key, short value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putShort(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putInt(String key, int value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putInt(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putLong(String key, long value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putLong(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putFloat(String key, float value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putFloat(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putDouble(String key, double value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putDouble(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putBoolean(String key, boolean value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putBoolean(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public void putSerializable(String key, Serializable value) {
        w.lock();
        try {
            put(key, value);
            fileStorage.putSerializable(key, value);
        } finally {
            w.unlock();
        }
        trimToSize(maxSize);
    }

    @Override
    public String getString(String key, String defaultValue) {
        r.lock();
        String value;
        boolean d = false;
        try {
            value = (String) get(key);
            if (TextUtils.isEmpty(value)) {
                r.unlock();
                w.lock();
                try {
                    value = (String) get(key);
                    if (TextUtils.isEmpty(value)) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            try {
                                value = new String(bytes, "UTF-8");
                                put(key, value);
                                d = true;
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            }
        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        r.lock();
        Object o;
        byte[] value = null;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = bytes;
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (byte[]) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (byte[]) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public short getShort(String key, short defaultValue) {
        r.lock();
        Object o;
        short value = -99;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = FileStorage.b2Short(bytes);
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (short) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (short) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        r.lock();
        Object o;
        int value = -99;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = FileStorage.b2Int(bytes);
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (int) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (int) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        r.lock();
        Object o;
        long value = -99L;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = FileStorage.b2Long(bytes);
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (long) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (long) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        r.lock();
        Object o;
        float value = -99F;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = Float.intBitsToFloat(FileStorage.b2Int(bytes));
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (float) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (float) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        r.lock();
        Object o;
        double value = -99;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = Double.longBitsToDouble(FileStorage.b2Long(bytes));
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (double) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (double) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        r.lock();
        Object o;
        boolean value;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = bytes[0] == 0 ? false : true;
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (boolean) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (boolean) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, T defaultValue) {
        r.lock();
        Object o;
        T value;
        boolean d = false;
        try {
            o = get(key);
            if (o == null) {
                r.unlock();
                w.lock();
                try {
                    o = get(key);
                    if (o == null) {
                        byte[] bytes = fileStorage.readData(key);
                        if (bytes == null || bytes.length == 0) {
                            value = defaultValue;
                        } else {
                            value = (T) FileStorage.byteToObject(bytes);
                            put(key, value);
                            d = true;
                        }
                    } else {
                        value = (T) o;
                    }

                } finally {
                    r.lock();
                    w.unlock();
                }
            } else {
                value = (T) o;
            }

        } finally {
            r.unlock();
        }
        if (d) {
            trimToSize(maxSize);
        }
        return value;
    }

    /**
     * 移除内存缓存
     *
     * @param key
     */
    @Override
    public void remove(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }

        Object previous;

        w.lock();
        try {
            previous = map.remove(key);
            if (previous != null) {
                size -= safeSizeOf(key, previous);
            }
        } finally {
            w.unlock();
        }


    }

    public void removeDisk(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        w.lock();
        try {
            fileStorage.remove(key);
        } finally {
            w.unlock();
        }

    }

    @Override
    public void clearAll() {
        evictAll();
    }

    public void clearDisk() {
        w.lock();
        try {
            fileStorage.clearAll();
        } finally {
            w.unlock();
        }
    }


    private int safeSizeOf(String key, Object value) {
        int result = sizeOf(key, value);
        if (result < 0) {
            throw new IllegalStateException("Negative size: " + key + "=" + value);
        }
        return result;
    }

    /**
     * Returns the size of the entry for {@code key} and {@code value} in
     * user-defined units.  The default implementation returns 1 so that size
     * is the number of entries and max size is the maximum number of entries.
     * <p/>
     * <p>An entry's size must not change while it is in the cache.
     */
    protected int sizeOf(String key, Object value) {
        return FileStorage.objectToByte(value).length;
    }

    /**
     * Clear the cache, calling {@link #} on each removed entry.
     */
    public final void evictAll() {
        trimToSize(-1); // -1 will evict 0-sized elements
    }

    /**
     * For caches that do not override {@link #sizeOf}, this returns the number
     * of entries in the cache. For all other caches, this returns the sum of
     * the sizes of the entries in this cache.
     */
    public final int size() {
        r.lock();
        try {
            return size;
        } finally {
            r.unlock();
        }
    }

    /**
     * For caches that do not override {@link #sizeOf}, this returns the maximum
     * number of entries in the cache. For all other caches, this returns the
     * maximum sum of the sizes of the entries in this cache.
     */
    public final int maxSize() {
        r.lock();
        try {
            return maxSize;
        } finally {
            r.unlock();
        }
    }


    /**
     * Returns a copy of the current contents of the cache, ordered from least
     * recently accessed to most recently accessed.
     */
    public final Map<String, Object> snapshot() {
        w.lock();
        try {
            return new LinkedHashMap<String, Object>(map);
        } finally {
            w.unlock();
        }
    }

}
