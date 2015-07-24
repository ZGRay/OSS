package com.example.orm.item;

import java.io.Serializable;

public class BaseResult implements Serializable {
    private static final long serialVersionUID = 1L;
    public BStatus bstatus = new BStatus();

    public BaseResult() {
    }

    public String getExt() {
        return null;
    }


    public interface BaseData extends Serializable {
    }
}
