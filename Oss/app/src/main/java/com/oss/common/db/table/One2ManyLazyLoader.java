package com.oss.common.db.table;

import com.oss.common.model.DataBaseProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
public class One2ManyLazyLoader<O,M> {
    private O oneEntity;
    private  Object keyValue;
    private One2ManyInfo one2ManyInfo;
    private DataBaseProvider<M> dataBaseProvider;
    private List<M> manys;
    private  boolean isSerch;

    public One2ManyLazyLoader( Object keyValue, One2ManyInfo one2ManyInfo, DataBaseProvider<M> dataBaseProvider) {
        this.keyValue = keyValue;
        this.one2ManyInfo = one2ManyInfo;
        this.dataBaseProvider = dataBaseProvider;
    }

    public List<M> getManys(){
        if (!isSerch && manys == null){
            if (dataBaseProvider != null){
                manys = dataBaseProvider.getListByForeignkey(one2ManyInfo.referencedColumnName,keyValue);
            }
            isSerch = true;
        }
        return manys;
    }

    public void setManys(List<M> datas){
        this.manys = datas;
        isSerch = true;
    }


}
