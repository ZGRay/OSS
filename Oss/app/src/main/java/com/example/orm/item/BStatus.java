//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.orm.item;

import java.io.Serializable;

public class BStatus implements Serializable {
    public static final String TAG = "bstatus";
    private static final long serialVersionUID = 1L;
    public int code = -2147483648;
    public String des = "code 不存在";
    public BStatus.QAction action;

    public BStatus() {
    }

    public static final class QAction implements Serializable {
        private static final long serialVersionUID = 1L;
        public static final int JUMP_TO_HOME = 0;
        public static final int JUMP_TO_HOTEL_SEARCH = 21;
        public static final int JUMP_TO_HOTEL_LIST = 22;
        public static final int JUMP_TO_HOTEL_DETAIL = 23;
        public static final int JUMP_TO_HOTEL_ROOMTYPE_PRICE = 24;
        public static final int JUMP_TO_HOTEL_ORDER_FILL = 25;
        public static final int JUMP_TO_HOTEL_ORDERS_LIST = 26;
        public static final int JUMP_TO_HOTEL_ORDERS_LIST_SEARCH_TAB = 27;
        public static final int JUMP_TO_HOTEL_REFRESH_SELF = 50;
        public int pageto;
        public int[] resets;

        public QAction() {
        }
    }
}
