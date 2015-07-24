package com.example.orm.item;

/**
 * Created by jbg168 on 15/1/16.
 */

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 火车票订单卡片Item
 */
public class TrainOrderItem extends BaseOrderListItem {
    private static final long serialVersionUID = 1L;

    public String extra;
    /**
     * 到达日期 eg:2014-06-12
     */
    public String arrDate;
    /**
     * 到达车站 eg:长沙南
     */
    public String arrStation;
    /**
     * 到达时间 eg:"14:11"
     */
    public String arrTime;
    /**
     * 出发日期 eg:2014-06-11
     */
    public String depDate;
    /**
     * 出发车站 eg:长沙南
     */
    public String depStation;
    /**
     * 出发时间 eg:"14:11"
     */
    public String depTime;
    /**
     * 车次名 eg:普通快车
     */
    public String trainTypeName;
    /**
     * 车次号 eg:G179
     */
    public String trainNo;
    /**
     * 列车类型 eg:G
     */
    public String trainType;
    /**
     * 出发城市
     */
    public String depCity;
    /**
     * 到达城市
     */
    public String arrCity;
    /**
     * 座位号
     */
    public ArrayList<Sit> listSit;
    /**
     * 出发  几月几号 周几
     */
    public String depDateFormat;
    /**
     * 到达 几月几号 周几
     */
    public String arrDateFormat;

    public String extparams;

    public ValidOrderListResult.HotelRecommed hotel;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrainOrderItem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        TrainOrderItem that = (TrainOrderItem) o;

        if (arrCity != null ? !arrCity.equals(that.arrCity) : that.arrCity != null) {
            return false;
        }
        if (arrDate != null ? !arrDate.equals(that.arrDate) : that.arrDate != null) {
            return false;
        }
        if (arrDateFormat != null ? !arrDateFormat.equals(that.arrDateFormat) : that.arrDateFormat != null) {
            return false;
        }
        if (arrStation != null ? !arrStation.equals(that.arrStation) : that.arrStation != null) {
            return false;
        }
        if (arrTime != null ? !arrTime.equals(that.arrTime) : that.arrTime != null) {
            return false;
        }
        if (depCity != null ? !depCity.equals(that.depCity) : that.depCity != null) {
            return false;
        }
        if (depDate != null ? !depDate.equals(that.depDate) : that.depDate != null) {
            return false;
        }
        if (depDateFormat != null ? !depDateFormat.equals(that.depDateFormat) : that.depDateFormat != null) {
            return false;
        }
        if (depStation != null ? !depStation.equals(that.depStation) : that.depStation != null) {
            return false;
        }
        if (depTime != null ? !depTime.equals(that.depTime) : that.depTime != null) {
            return false;
        }
        if (extparams != null ? !extparams.equals(that.extparams) : that.extparams != null) {
            return false;
        }
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) {
            return false;
        }
        if (listSit != null ? !listSit.equals(that.listSit) : that.listSit != null) {
            return false;
        }
        if (trainNo != null ? !trainNo.equals(that.trainNo) : that.trainNo != null) {
            return false;
        }
        if (trainType != null ? !trainType.equals(that.trainType) : that.trainType != null) {
            return false;
        }
        if (trainTypeName != null ? !trainTypeName.equals(that.trainTypeName) : that.trainTypeName != null) {
            return false;
        }

        if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null) {
            return false;
        }

        return super.equals(o);
    }


    public static class Sit implements Serializable {
        private static final long serialVersionUID = 1L;
        /**
         * 座位号 A车A号
         */
        public String seatNo;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Sit)) {
                return false;
            }

            Sit sit = (Sit) o;

            if (seatNo != null ? !seatNo.equals(sit.seatNo) : sit.seatNo != null) {
                return false;
            }

            return true;
        }
    }


}
