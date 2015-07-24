package com.oss.storage;

import java.io.Serializable;

/**
 * @author Layen.ZH
 *         create at 2015/7/22 17:27
 */
public interface IStorage {
    void initialize();
    void putString(String key, String value);
    void putBytes(String key, byte[] value);
    void putShort(String key, short value);
    void putInt(String key, int value);
    void putLong(String key, long value);
    void putFloat(String key, float value);
    void putDouble(String key, double value);
    void putBoolean(String key, boolean value);
    void putSerializable(String key, Serializable value);
    String getString(String key,String defaultValue);
    byte[] getBytes(String key,byte[] defaultValue);
    short getShort(String key,short defaultValue);
    int getInt(String key,int defaultValue);
    long getLong(String key,long defaultValue);
    float getFloat(String key, float defaultValue);
    double getDouble(String key,double defaultValue);
    boolean getBoolean(String key,boolean defaultValue);
    <T extends Serializable> Object getSerializable(String key,T defaultValue);

    void remove(String key);

    void clear();
}
