package com.oss.storage;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/25.
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
