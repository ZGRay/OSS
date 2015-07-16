package com.oss.common.model;

import java.util.ArrayList;

/**
 * Created by sunby.zhang on 2015/7/10.
 */
public class SqlValue {
    public String sql;
    public ArrayList<Object> bindArgs;

    public void addValue(Object obj) {
        if (bindArgs == null)
            bindArgs = new ArrayList<Object>();

        bindArgs.add(obj);
    }

    public String[] getBindArgsAsStringArray() {
        if (bindArgs != null) {
            String[] strings = new String[bindArgs.size()];
            for (int i = 0; i < bindArgs.size(); i++) {
                strings[i] = bindArgs.get(i).toString();
            }
            return strings;
        }
        return null;
    }

    public Object[] getBindArgsAsArray() {
        if (bindArgs != null)
            return bindArgs.toArray();
        return null;
    }
}
