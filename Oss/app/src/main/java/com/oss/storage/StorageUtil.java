package com.oss.storage;

/**
 * Created by sunby.zhang on 2015/7/21.
 */
public class StorageUtil {
    private static StorageUtil storageUtil = null;
    private static final String DEFAULT_CACHE_DIR = "storage";

    private StorageUtil() {

    }

    public static StorageUtil getInstance() {
        if (storageUtil == null) {
            synchronized (StorageUtil.class) {
                if (storageUtil == null) {
                    storageUtil = new StorageUtil();
                }
            }
        }
        return storageUtil;
    }
}
