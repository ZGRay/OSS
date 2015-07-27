package com.oss.common.db.table;


import com.oss.common.model.DataBaseProvider;

/**
 * @author Layen.ZH
 *         create at 2015/7/27 11:41
 * @param <O> 一对一主表
 * @param <T> 一对一 关联的表
 */
public class One2OneLazyLoader<O,T> {
    O PrimaryEntity;
    Object keyValue;
    One2OneInfo oneInfo;
    private T foreignEntity;
    DataBaseProvider<T> dataBaseProvider;
    private  boolean isSerch;
    public One2OneLazyLoader(Object keyValue, One2OneInfo oneInfo, DataBaseProvider<T> dataBaseProvider) {
        this.keyValue = keyValue;
        this.oneInfo = oneInfo;
        this.dataBaseProvider = dataBaseProvider;
    }

    public T getForeignEntity(){
        if (!isSerch && foreignEntity == null){
            if (dataBaseProvider != null ){
                foreignEntity = dataBaseProvider.getByForeignKey(oneInfo.referencedColumnName, keyValue);
            }
            isSerch = true;
        }
        return foreignEntity;
    }

    public void setForeignEntity(T fEntity){
        foreignEntity = fEntity;
        isSerch = true;
    }

    @Override
    public String toString() {
        return "One2OneLazyLoader{" +
                "foreignEntity=" + foreignEntity +
                '}';
    }
}
