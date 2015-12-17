package com.oss.storage;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁，高校读取
 * @author Layen.ZH
 *         create at 2015/8/6 14:31
 */
public class HFileStorage implements IStorage {
    private FileStorage fileStorage;
    /**string 锁*/
    private ReadWriteLock strwl = new ReentrantReadWriteLock();
    //不可以用多个锁，存放不同类型数据，key相同，会操错同一个文件，
//    /**byte锁*/
//    private ReadWriteLock byrwl = new ReentrantReadWriteLock();
//    /**short 锁*/
//    private ReadWriteLock shrwl = new ReentrantReadWriteLock();
//    /**int锁*/
//    private ReadWriteLock irwl = new ReentrantReadWriteLock();
//    /**long锁*/
//    private ReadWriteLock lrwl = new ReentrantReadWriteLock();
//    /**float锁*/
//    private ReadWriteLock frwl = new ReentrantReadWriteLock();
//    /**double锁*/
//    private ReadWriteLock drwl = new ReentrantReadWriteLock();
//    /**boolean 锁*/
//    private ReadWriteLock borwl = new ReentrantReadWriteLock();
//    /**对象锁*/
//    private ReadWriteLock orwl = new ReentrantReadWriteLock();


    public HFileStorage(File rootDirectory, int maxCacheSizeInBytes) {
        fileStorage = new FileStorage(rootDirectory, maxCacheSizeInBytes);
        initialize();
    }

    public HFileStorage(File rootDirectory) {
        fileStorage = new FileStorage(rootDirectory);
        initialize();
    }

    @Override
    public  void initialize() {
        strwl.writeLock().lock();
        try {
            fileStorage.initialize();
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putString(String key, String value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putString(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putBytes(String key, byte[] value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putBytes(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putShort(String key, short value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putShort(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putInt(String key, int value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putInt(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putLong(String key, long value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putLong(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putFloat(String key, float value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putFloat(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putDouble(String key, double value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putDouble(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putBoolean(String key, boolean value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putBoolean(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public void putSerializable(String key, Serializable value) {
        strwl.writeLock().lock();
        try {
            fileStorage.putSerializable(key, value);
        } finally {
            strwl.writeLock().unlock();
        }
    }

    @Override
    public String getString(String key, String defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getString(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public byte[] getBytes(String key, byte[] defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getBytes(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public short getShort(String key, short defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getShort(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public int getInt(String key, int defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getInt(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public long getLong(String key, long defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getLong(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getFloat(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getDouble(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getBoolean(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    @Override
    public <T extends Serializable> T getSerializable(String key, T defaultValue) {
        strwl.readLock().lock();
        try {
            return fileStorage.getSerializable(key, defaultValue);
        } finally {
            strwl.readLock().unlock();
        }
    }

    /**
     * @hide
     */
    @Override
    public  void remove(String key) {
        strwl.writeLock().lock();
//        byrwl.writeLock().lock();
//        shrwl.writeLock().lock();
//        irwl.writeLock().lock();
//        lrwl.writeLock().lock();
//        frwl.writeLock().lock();
//        drwl.writeLock().lock();
//        borwl.writeLock().lock();
//        orwl.writeLock().lock();
        try {
            fileStorage.remove(key);
        } finally {
            strwl.writeLock().unlock();
//            byrwl.writeLock().unlock();
//            shrwl.writeLock().unlock();
//            irwl.writeLock().unlock();
//            lrwl.writeLock().unlock();
//            frwl.writeLock().unlock();
//            drwl.writeLock().unlock();
//            borwl.writeLock().unlock();
//            orwl.writeLock().unlock();
        }
    }

    @Override
    public  void clearAll() {
        strwl.writeLock().lock();
        try {
            fileStorage.clearAll();
        } finally {
            strwl.writeLock().unlock();
        }
    }
}
