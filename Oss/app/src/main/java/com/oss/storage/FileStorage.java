package com.oss.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.oss.common.util.Utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Layen.ZH
 *         create at 2015/7/22 18:00
 */
public class FileStorage implements IStorage {
    private static final String TAG = "FileStorage";
    /**
     * Map of the Key, CacheHeader pairs
     */
    final Map<String, CacheHeader> mEntries = new LinkedHashMap<String, CacheHeader>(16, .75f, true);

    /**
     * 当前使用的缓存空间大小
     */
    long mTotalSize = 0;

    /**
     * 缓存使用的根目录
     */
    final File mRootDirectory;

    /**
     * 缓存空间的最大值
     */
    final int mMaxCacheSizeInBytes;

    /**
     * 默认缓存使用的最大值
     */
    static final int DEFAULT_DISK_USAGE_BYTES = 5 * 1024 * 1024;

    /**
     * 缓存比例，本地缓存的大小超过最大值，缓存维持的比例
     */
    static final float HYSTERESIS_FACTOR = 0.9f;

    /**
     * Magic number for current version of cache file format.
     */
    static final int CACHE_MAGIC = 0x20150306;

    public FileStorage(File rootDirectory, int maxCacheSizeInBytes) {
        mRootDirectory = rootDirectory;
        mMaxCacheSizeInBytes = maxCacheSizeInBytes;
    }


    public FileStorage(File rootDirectory) {
        this(rootDirectory, DEFAULT_DISK_USAGE_BYTES);
    }

    @Override
    public synchronized void initialize() {
        if (!mRootDirectory.exists()) {
            if (!mRootDirectory.mkdirs()) {
                DiskCacheLog.e("Unable to create cache dir %s", mRootDirectory.getAbsolutePath());
            }
            return;
        }

        File[] files = mRootDirectory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            BufferedInputStream fis = null;
            try {
                fis = new BufferedInputStream(new FileInputStream(file));
                CacheHeader entry = CacheHeader.readHeader(fis);
                entry.size = file.length();
                putEntry(entry.key, entry);
            } catch (IOException e) {
                e.printStackTrace();
                if (file != null) {
                    file.delete();
                }
            } finally {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException ignored) {
                    ignored.printStackTrace();
                }
            }
        }
    }


    @Override
    public synchronized void putString(String key, String value) {
        File file = getFileForKey(key);
        try {
            writeCacheHeader(key, value.getBytes("UTF-8"), file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putBytes(String key, byte[] value) {
        File file = getFileForKey(key);
        try {
            writeCacheHeader(key, value, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putShort(String key, short value) {
        File file = getFileForKey(key);
        try {
            byte[] b = short2B(value);
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putInt(String key, int value) {
        File file = getFileForKey(key);
        try {
            byte[] b = int2B(value);
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putLong(String key, long value) {
        File file = getFileForKey(key);
        try {
            byte[] b = long2B(value);
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putFloat(String key, float value) {
        File file = getFileForKey(key);
        try {
            byte[] b = int2B(Float.floatToIntBits(value));
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putDouble(String key, double value) {
        File file = getFileForKey(key);
        try {
            byte[] b = long2B(Double.doubleToLongBits(value));
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putBoolean(String key, boolean value) {
        File file = getFileForKey(key);
        try {
            byte[] b = value ? new byte[]{1} : new byte[]{0};
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        deleteFile(file);
    }

    @Override
    public synchronized void putSerializable(String key, Serializable entry) {
        File file = getFileForKey(key);
        try {
            byte[] b = objectToByte(entry);
            writeCacheHeader(key, b, file);
            return;
        } catch (IOException e) {
            e.printStackTrace();

        }
        deleteFile(file);
    }

    @Override
    public synchronized String getString(String key, String defaultValue) {
        byte[] bytes = readData(key);
        if (bytes != null && bytes.length > 0) {
            try {
                return new String(bytes, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }

    @Override
    public synchronized byte[] getBytes(String key, byte[] defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return bytes;
    }

    @Override
    public synchronized short getShort(String key, short defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return b2Short(bytes);
    }

    @Override
    public synchronized int getInt(String key, int defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return b2Int(bytes);
    }

    @Override
    public synchronized long getLong(String key, long defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return b2Long(bytes);
    }

    @Override
    public synchronized float getFloat(String key, float defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return Float.intBitsToFloat(b2Int(bytes));
    }

    @Override
    public synchronized double getDouble(String key, double defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return Double.longBitsToDouble(b2Long(bytes));
    }

    @Override
    public synchronized boolean getBoolean(String key, boolean defaultValue) {
        byte[] bytes = readData(key);
        if (bytes == null || bytes.length == 0) {
            return defaultValue;
        }
        return bytes[0] == 0 ? false : true;
    }

    @Override
    public synchronized <T extends Serializable> T getSerializable(String key, T defaultValue) {
        FileStorage.CacheHeader entry = mEntries.get(key);
        // if the entry does not exist, return.
        if (entry == null) {
            return defaultValue;
        }
        Object obj;
        File file = getFileForKey(key);
        try {
            FileInputStream fis = new FileInputStream(file);   //获得输入流
            ObjectInputStream ois = new ObjectInputStream(fis);
            CacheHeader.readHeader(ois);
            obj = ois.readObject();
            ois.close();
            fis.close();
            return (T) obj;
        } catch (Exception e) {
            DiskCacheLog.d("%s: %s", file.getAbsolutePath(), e.toString());
            remove(key);
            return defaultValue;
        }
    }

    @Override
    public synchronized void remove(String key) {
        boolean deleted = getFileForKey(key).delete();
        removeEntry(key);
        if (!deleted) {
            DiskCacheLog.d("Could not delete cache entry for key=%s, filename=%s",
                    key, getFilenameForKey(key));
        }
    }

    @Override
    public synchronized void clear() {
        File[] files = mRootDirectory.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
        mEntries.clear();
        mTotalSize = 0;
        DiskCacheLog.d("Cache cleared.");
    }

    protected  void writeCacheHeader(String key, byte[] value, File file) throws IOException {
        //调整缓存
        pruneIfNeeded(value.length);

        FileOutputStream fos = new FileOutputStream(file);
        FileStorage.CacheHeader e = new FileStorage.CacheHeader(key, value.length);
        boolean success = e.writeHeader(fos);
        if (!success) {
            fos.close();
            DiskCacheLog.d("Failed to write header for %s", file.getAbsolutePath());
            throw new IOException();
        }
        fos.write(value);
        fos.close();
        putEntry(key, e);

    }

    protected byte[] readData(String key) {
        FileStorage.CacheHeader entry = mEntries.get(key);
        // if the entry does not exist, return.
        if (entry == null) {
            return null;
        }

        File file = getFileForKey(key);
        CountingInputStream cis = null;
        try {
            cis = new CountingInputStream(new FileInputStream(file));
            CacheHeader.readHeader(cis); // eat header
            return streamToBytes(cis, (int) (file.length() - cis.bytesRead));
        } catch (IOException e) {
            DiskCacheLog.d("%s: %s", file.getAbsolutePath(), e.toString());
            remove(key);
            return null;
        } finally {
            if (cis != null) {
                try {
                    cis.close();
                } catch (IOException ioe) {
                    return null;
                }
            }
        }
    }

    public void deleteFile(File file) {
        boolean deleted = file.delete();
        if (!deleted) {
            DiskCacheLog.d("Could not clean up file %s", file.getAbsolutePath());
        }
    }
/*
     * Homebrewed simple serialization system used for reading and writing cache
     * headers on disk. Once upon a time, this used the standard Java
     * Object{Input,Output}Stream, but the default implementation relies heavily
     * on reflection (even for standard types) and generates a ton of garbage.
     */

    /**
     * Simple wrapper around {@link InputStream#read()} that throws EOFException
     * instead of returning -1.
     */
    private static int read(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        }
        return b;
    }

    static void writeInt(OutputStream os, int n) throws IOException {
        os.write((n >> 0) & 0xff);
        os.write((n >> 8) & 0xff);
        os.write((n >> 16) & 0xff);
        os.write((n >> 24) & 0xff);
    }

    static int readInt(InputStream is) throws IOException {
        int n = 0;
        n |= (read(is) << 0);
        n |= (read(is) << 8);
        n |= (read(is) << 16);
        n |= (read(is) << 24);
        return n;
    }

    static void writeLong(OutputStream os, long n) throws IOException {
        os.write((byte) (n >>> 0));
        os.write((byte) (n >>> 8));
        os.write((byte) (n >>> 16));
        os.write((byte) (n >>> 24));
        os.write((byte) (n >>> 32));
        os.write((byte) (n >>> 40));
        os.write((byte) (n >>> 48));
        os.write((byte) (n >>> 56));
    }

    static long readLong(InputStream is) throws IOException {
        long n = 0;
        n |= ((read(is) & 0xFFL) << 0);
        n |= ((read(is) & 0xFFL) << 8);
        n |= ((read(is) & 0xFFL) << 16);
        n |= ((read(is) & 0xFFL) << 24);
        n |= ((read(is) & 0xFFL) << 32);
        n |= ((read(is) & 0xFFL) << 40);
        n |= ((read(is) & 0xFFL) << 48);
        n |= ((read(is) & 0xFFL) << 56);
        return n;
    }

    static void writeString(OutputStream os, String s) throws IOException {
        byte[] b = s.getBytes("UTF-8");
        writeLong(os, b.length);
        os.write(b, 0, b.length);
    }

    static String readString(InputStream is) throws IOException {
        int n = (int) readLong(is);
        byte[] b = streamToBytes(is, n);
        return new String(b, "UTF-8");
    }

    /**
     * 读取数据流到 byte【[]
     */
    static byte[] streamToBytes(InputStream in, int length) throws IOException {
        byte[] bytes = new byte[length];
        int count;
        int pos = 0;
        while (pos < length && ((count = in.read(bytes, pos, length - pos)) != -1)) {
            pos += count;
        }
        if (pos != length) {
            throw new IOException("Expected " + length + " bytes, read " + pos + " bytes");
        }
        Log.d(TAG, "disk  hit");
        return bytes;
    }

    /**
     * 存储entry到缓存
     *
     * @param key   The key to identify the entry by.
     * @param entry The entry to cache.
     */
    void putEntry(String key, CacheHeader entry) {
        if (!mEntries.containsKey(key)) {
            mTotalSize += entry.size;
        } else {
            CacheHeader oldEntry = mEntries.get(key);
            mTotalSize += (entry.size - oldEntry.size);
        }
        mEntries.put(key, entry);
    }

    /**
     * 从缓存中移除指定key的 entry
     */
    private void removeEntry(String key) {
        CacheHeader entry = mEntries.get(key);
        if (entry != null) {
            mTotalSize -= entry.size;
            mEntries.remove(key);
        }
    }

    /**
     * Creates a pseudo-unique filename for the specified cache key.
     *
     * @param key The key to generate a file name for.
     * @return A pseudo-unique filename.
     */
    protected String getFilenameForKey(String key) {
        int firstHalfLength = key.length() / 2;
        String localFilename = String.valueOf(key.substring(0, firstHalfLength).hashCode());
        localFilename += String.valueOf(key.substring(firstHalfLength).hashCode());
        return localFilename;
    }

    /**
     * Returns a file object for the given cache key.
     */
    public File getFileForKey(String key) {
        return new File(mRootDirectory, getFilenameForKey(key));
    }

    /**
     * Prunes the cache to fit the amount of bytes specified.
     *
     * @param neededSpace The amount of bytes we are trying to fit into the cache.
     */
    private void pruneIfNeeded(long neededSpace) {
        if ((mTotalSize + neededSpace) < mMaxCacheSizeInBytes) {
            return;
        }
        if (DiskCacheLog.DEBUG) {
            DiskCacheLog.v("Pruning old cache entries.");
        }

        long before = mTotalSize;
        int prunedFiles = 0;
        long startTime = SystemClock.elapsedRealtime();

        Iterator<Map.Entry<String, CacheHeader>> iterator = mEntries.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, CacheHeader> entry = iterator.next();
            CacheHeader e = entry.getValue();
            boolean deleted = getFileForKey(e.key).delete();
            if (deleted) {
                mTotalSize -= e.size;
            } else {
                DiskCacheLog.d("Could not delete cache entry for key=%s, filename=%s",
                        e.key, getFilenameForKey(e.key));
            }
            iterator.remove();
            prunedFiles++;

            if ((mTotalSize + neededSpace) < mMaxCacheSizeInBytes * HYSTERESIS_FACTOR) {
                break;
            }
        }

        if (DiskCacheLog.DEBUG) {
            DiskCacheLog.v("pruned %d files, %d bytes, %d ms",
                    prunedFiles, (mTotalSize - before), SystemClock.elapsedRealtime() - startTime);
        }
        Log.d(TAG, "disk size = " + mTotalSize);
    }

    static class CacheHeader {
        /**
         * The size of the data identified by this CacheHeader. (This is not
         * serialized to disk.
         */
        public long size;

        /**
         * The key that identifies the cache entry.
         */
        public String key;


        private CacheHeader() {
        }


        public CacheHeader(String key, long size) {
            this.key = key;
            this.size = size;
        }

        /**
         * Reads the header off of an InputStream and returns a CacheHeader object.
         *
         * @param is The InputStream to read from.
         * @throws IOException
         */
        public static CacheHeader readHeader(InputStream is) throws IOException {
            CacheHeader entry = new CacheHeader();
            int magic = readInt(is);
            if (magic != CACHE_MAGIC) {
                // don't bother deleting, it'll get pruned eventually
                throw new IOException();
            }
            entry.key = readString(is);
            return entry;
        }

        public String toCacheEntry(byte[] data) {
            return new String(data);
        }


        /**
         * 把 指定的缓存头信息 写入到输出流
         */
        public boolean writeHeader(OutputStream os) {
            try {
                writeInt(os, CACHE_MAGIC);
                writeString(os, key);
                os.flush();
                return true;
            } catch (IOException e) {
                DiskCacheLog.d("%s", e.toString());
                return false;
            }
        }

    }

    private static class CountingInputStream extends FilterInputStream {
        private int bytesRead = 0;

        private CountingInputStream(InputStream in) {
            super(in);
        }

        @Override
        public int read() throws IOException {
            int result = super.read();
            if (result != -1) {
                bytesRead++;
            }
            return result;
        }

        @Override
        public int read(byte[] buffer, int offset, int count) throws IOException {
            int result = super.read(buffer, offset, count);
            if (result != -1) {
                bytesRead += result;
            }
            return result;
        }
    }

    static byte[] short2B(short value) {
        return new byte[]{(byte) (value & 0xff), (byte) ((value >> 8) & 0xff)};
    }

    static byte[] int2B(int value) {
        return new byte[]{(byte) (value & 0xff), (byte) ((value >> 8) & 0xff), (byte) ((value >> 16) & 0xff), (byte) ((value >> 24) & 0xff)};
    }

    static byte[] long2B(long value) {
        return new byte[]{(byte) value , (byte) (value >>> 8), (byte) (value >>> 16), (byte) (value >>> 24),
                (byte) (value >>> 32), (byte) (value >>> 40) , (byte) (value >>>48) , (byte) (value >>> 56) };
    }

    static short b2Short(byte[] bytes) {
        short num = 0;
        for (int i = 0; i < 2; i++) {
            num |= (bytes[i] << (i * 8));

        }
        return num;
    }

    static int b2Int(byte[] bytes) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            num |= (bytes[i] << (i * 8));

        }
        return num;
    }

    static long b2Long(byte[] bytes) {
        long num = 0L;
        for (int i = 0; i < 8; i++) {
            num |= ((bytes[i]& 0xFFL)<< (i * 8));

        }
        return num;
    }

    public static byte[] objectToByte(Object obj) {
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

    public static Object byteToObject(byte[] bytes) {
        Object obj = null;
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

