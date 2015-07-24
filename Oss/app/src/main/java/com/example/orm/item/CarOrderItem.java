package com.example.orm.item;

import java.io.Serializable;

/**
 * Created by jbg168 on 15/1/16.
 * 车车订单卡片Item
 */
public class CarOrderItem extends BaseOrderListItem {
    private static final long serialVersionUID = 1L;
    /** 资源服务类型描述 */
    public String title;
    /** 出发地 */
    public String departurePlace;
    /** 目的地 */
    public String destination;
    /** 车型图片 */
    public String carPicUrl;
    /***/
    public String carLicense;
    /** 司机姓名 */
    public String driverName;
    /** 司机电话 */
    public String driverPhone;
    /** 取车日期 */
    public String takeDate;
    /** 取车时间 */
    public String takeTime;
    /** 取车日期为周几 */
    public String takeWeekDay;
    /** 还车日期 */
    public String returnDate;
    /** 还车时间 */
    public String returnTime;
    /** 还车日期是周几 */
    public String returnWeekDay;
    /** 取车门店 */
    public String takeStoreName;
    /** 还车门店 */
    public String returnStoreName;
    /** 车型名称 */
    public String carTypeName;
    /** 商家电话 */
    public String agentPhone;
    /** 资源类型 */
    public int resourceType;
    /** 业务类型 */
    public int serviceType;
    /** 预定用车日期 */
    public String scheduledDate;
    /** 预定用车时间 */
    public String scheduledTime;
    /** 预定用车是周几 */
    public String scheduledWeekDay;

    /** 车辆品牌名称 **/
    public String carTypeNo;
    /** 车辆颜色 **/
    public String carColor;

    /** 订单状态信息  **/
    public String statusInfo;
    /** 订单状态提示 **/
    public String statusRemind;
    // 0立即 1预约
    public int orderType;
    public String reminder;

    //订单卡片预估价
	/* 预估价 */
    public String priceName;
	/* 预估价 */
    public double predicPrice;
    /* 预估原价 */
    public double predicOriginalPrice;
    /* 券抵扣金额 */
    public double couponDeductPrice;
    /* 预估价格字符串 */
    public String predicPriceStr;
    /* 预估原价格字符串 */
    public String predicOriginalPriceStr;
    /* 券抵扣金额说明 */
    public String couponDeductPriceStr;
    // 催单模块显示信息
    public UrgeInfo urgeState;
    // 剩余催单时间
    public int urgeTimeLeft;
    public int couponType;

    public static class UrgeInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        public long currentTimeMillis; // 当前时间（毫秒）
        public int isShowUrgeBtn; // 是否展示催单按钮 0：不显示 1：显示
        public String urgeBtnName; // 催单按钮名称
        public String urgeBtnValue; // 催单按钮上附加信息
        public long timer; // 催单后的倒计时终止时间（秒）
        public String statusInfo; // 状态信息（转圈图标左侧文案）
        public String statusRemind; // 状态提醒 （转圈图标下方文案）
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarOrderItem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        CarOrderItem that = (CarOrderItem) o;

        if (resourceType != that.resourceType) {
            return false;
        }
        if (serviceType != that.serviceType) {
            return false;
        }
        if (agentPhone != null ? !agentPhone.equals(that.agentPhone) : that.agentPhone != null) {
            return false;
        }
        if (carLicense != null ? !carLicense.equals(that.carLicense) : that.carLicense != null) {
            return false;
        }
        if (carPicUrl != null ? !carPicUrl.equals(that.carPicUrl) : that.carPicUrl != null) {
            return false;
        }
        if (carTypeName != null ? !carTypeName.equals(that.carTypeName) : that.carTypeName != null) {
            return false;
        }
        if (departurePlace != null ? !departurePlace.equals(that.departurePlace) : that.departurePlace != null) {
            return false;
        }
        if (destination != null ? !destination.equals(that.destination) : that.destination != null) {
            return false;
        }
        if (driverName != null ? !driverName.equals(that.driverName) : that.driverName != null) {
            return false;
        }
        if (driverPhone != null ? !driverPhone.equals(that.driverPhone) : that.driverPhone != null) {
            return false;
        }
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) {
            return false;
        }
        if (returnStoreName != null ? !returnStoreName.equals(that.returnStoreName) : that.returnStoreName !=
                null) {
            return false;
        }
        if (returnTime != null ? !returnTime.equals(that.returnTime) : that.returnTime != null) {
            return false;
        }
        if (returnWeekDay != null ? !returnWeekDay.equals(that.returnWeekDay) : that.returnWeekDay != null) {
            return false;
        }
        if (scheduledDate != null ? !scheduledDate.equals(that.scheduledDate) : that.scheduledDate != null) {
            return false;
        }
        if (scheduledTime != null ? !scheduledTime.equals(that.scheduledTime) : that.scheduledTime != null) {
            return false;
        }
        if (scheduledWeekDay != null ? !scheduledWeekDay.equals(that.scheduledWeekDay) : that.scheduledWeekDay !=
                null) {
            return false;
        }
        if (takeDate != null ? !takeDate.equals(that.takeDate) : that.takeDate != null) {
            return false;
        }
        if (takeStoreName != null ? !takeStoreName.equals(that.takeStoreName) : that.takeStoreName != null) {
            return false;
        }
        if (takeTime != null ? !takeTime.equals(that.takeTime) : that.takeTime != null) {
            return false;
        }
        if (takeWeekDay != null ? !takeWeekDay.equals(that.takeWeekDay) : that.takeWeekDay != null) {
            return false;
        }
        if (predicPriceStr != null ? !predicPriceStr.equals(that.predicPriceStr) : that.predicPriceStr != null) {
            return false;
        }
        if (predicOriginalPriceStr != null ? !predicOriginalPriceStr.equals(that.predicOriginalPriceStr) : that.predicOriginalPriceStr != null) {
            return false;
        }
        if (couponDeductPriceStr != null ? !couponDeductPriceStr.equals(that.couponDeductPriceStr) : that.couponDeductPriceStr != null) {
            return false;
        }
        if (predicPrice != that.predicPrice) {
            return false;
        }
        if (predicOriginalPrice != that.predicOriginalPrice) {
            return false;
        }
        if (couponDeductPrice != that.couponDeductPrice) {
            return false;
        }

        return super.equals(o);
    }
}
