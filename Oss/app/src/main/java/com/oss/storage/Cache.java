/*
 * @author 张雷
 * V 1.0.0
 */
package com.oss.storage;


public interface Cache {
    /**
     * @param key
     * @return
     */
    public String get(String key);

    /**
     * @param key
     * @param entry
     */
    public void put(String key, String entry);

    /**
     *
     */
    public void initialize();

    /**
     * @param key
     */
    public void remove(String key);

    /**
     *
     */
    public void clear();


}
