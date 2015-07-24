package com.example.orm.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 酒店订单卡片Item
 * Created by jbg168 on 15/1/16.
 */
public class HotelOrderItem extends BaseOrderListItem {
    private static final long serialVersionUID = 1L;

    /**
     * 到达时间 eg:"14:11"
     */
    public String arriveTime;
    /**  */
    public int commented;
    /**
     * 联系人电话 eg:13800138000
     */
    public String contactPhone;
    /**
     * 订单删除提示 eg:"您正在进行订单删除操作，删除后订单无法查询，确定要删除吗？"
     */
    public String deleteWarn;
    /**
     * 入住日期 eg:6月19日
     */
    public String fromDate;
    /**
     * 离店日期 eg:6月22日
     */
    public String toDate;
    /**
     * 酒店坐标 eg:“39.92222,116.4174”
     */
    public String gpoint;
    /**
     * 酒店地址 eg:"北京市东城区东四南大街33号"
     */
    public String hotelAddress;
    /**
     * 酒店名 eg:永正商务酒店
     */
    public String hotelName;
    /**
     * 酒店电话 eg:010-85116688
     */
    public String hotelPhoneNumber;
    /**
     * 下单时间 eg:"2014-06-18 14:10:32"
     */
    public String orderTime;
    /**
     * 房间名 eg:"房间2aa-担保2RP"
     */
    public String roomName;
    /**
     * 详情跳转URL eg:"phoneScheme://hotel/orderDetail?wrapperId
     * =wiqunarqta2&orderNo=100095289623&contactPhone=18510685996"
     */
    public String schema;
    /**
     * 酒店序列号 eg:"beijing_city_1"
     */
    public String seq;
    /**
     * 停留时间 eg:"06月19日—06月20日 共1晚(目的地时间)"
     */
    public String stayTime;
    /**
     * 订单总价 eg:"78"
     */
    public String totalPrice;
    /**
     * 酒店wrapperId eg:"wiquanrqta2"
     */
    public String wrapperId;
    /**
     * 入住日期是周几
     */
    public String weekDay;
    /**
     * extra
     */
    public String extra;
    /**
     * 地图图片显示URL
     */
    public String mapUrl;
    /**
     * 国际酒店时间提示
     */
    public String interNotice;
    /**
     * actions的描述  例：入住酒店后遇到了问题？*
     */
    public String problemActionsTipContent;
    /**
     * 酒店订单问题按钮集合*
     */
    public JSONArray problemActions;

    public String onlineServiceUrl;
    /**
     * 能否申请返现*
     */
    public boolean ifCashBack;
    /**
     * 金额*
     */
    public int cashBackPrice;
    /**
     * 酒店推身边*
     */
    public ValidOrderListResult.NearbyRecommend nearby;
    public boolean isTeamPriceOrder;//是否可以拼团
    public String downLandDoc;//正在帮您寻找拼团小伙伴…
    public HotelTeamPriceResult.RelatedProductInfo relatedProductInfo;//拼团信息

    private ArrayList<HotelProblemItem> list;

    /**
     * 不用get是防止被序列化
     */
    public ArrayList<HotelProblemItem> parseProblems() {
        if (ArrayUtils.isEmpty(problemActions)) {
            return list = null;
        }
        if (list != null && list.size() == problemActions.size()) {
            return list;
        }
        list = new ArrayList<HotelProblemItem>();
        for (int i = 0; i < problemActions.size(); i++) {
            JSONObject jobj = problemActions.getJSONObject(i);
            HotelProblemItem item = JSON.toJavaObject(jobj, HotelProblemItem.class);
            ;
            list.add(item);
        }
        return list;
    }

    /**
     * 让list不参与序列化(序列化orders)
     */
    public ArrayList<HotelProblemItem> getList() {
        return null;
    }


    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HotelOrderItem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        HotelOrderItem that = (HotelOrderItem) o;

        if (commented != that.commented) {
            return false;
        }
        if (arriveTime != null ? !arriveTime.equals(that.arriveTime) : that.arriveTime != null) {
            return false;
        }
        if (contactPhone != null ? !contactPhone.equals(that.contactPhone) : that.contactPhone != null) {
            return false;
        }
        if (deleteWarn != null ? !deleteWarn.equals(that.deleteWarn) : that.deleteWarn != null) {
            return false;
        }
        if (extra != null ? !extra.equals(that.extra) : that.extra != null) {
            return false;
        }
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) {
            return false;
        }
        if (gpoint != null ? !gpoint.equals(that.gpoint) : that.gpoint != null) {
            return false;
        }
        if (hotelAddress != null ? !hotelAddress.equals(that.hotelAddress) : that.hotelAddress != null) {
            return false;
        }
        if (hotelName != null ? !hotelName.equals(that.hotelName) : that.hotelName != null) {
            return false;
        }
        if (hotelPhoneNumber != null ? !hotelPhoneNumber.equals(that.hotelPhoneNumber) : that.hotelPhoneNumber !=
                null) {
            return false;
        }
        if (interNotice != null ? !interNotice.equals(that.interNotice) : that.interNotice != null) {
            return false;
        }
        if (mapUrl != null ? !mapUrl.equals(that.mapUrl) : that.mapUrl != null) {
            return false;
        }
        if (orderTime != null ? !orderTime.equals(that.orderTime) : that.orderTime != null) {
            return false;
        }
        if (roomName != null ? !roomName.equals(that.roomName) : that.roomName != null) {
            return false;
        }
        if (schema != null ? !schema.equals(that.schema) : that.schema != null) {
            return false;
        }
        if (seq != null ? !seq.equals(that.seq) : that.seq != null) {
            return false;
        }
        if (stayTime != null ? !stayTime.equals(that.stayTime) : that.stayTime != null) {
            return false;
        }
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) {
            return false;
        }
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) {
            return false;
        }
        if (weekDay != null ? !weekDay.equals(that.weekDay) : that.weekDay != null) {
            return false;
        }
        if (wrapperId != null ? !wrapperId.equals(that.wrapperId) : that.wrapperId != null) {
            return false;
        }
        if (problemActionsTipContent != null ? !problemActionsTipContent.equals(that.problemActionsTipContent) : that.problemActionsTipContent != null) {
            return false;
        }
        if (problemActions != null ? !problemActions.equals(that.problemActions) : that.problemActions != null) {
            return false;
        }
        if (onlineServiceUrl != null ? !onlineServiceUrl.equals(that.onlineServiceUrl) : that.onlineServiceUrl != null) {
            return false;
        }
        if (nearby != null ? !nearby.equals(that.nearby) : that.nearby != null) {
            return false;
        }
        return super.equals(o);
    }

    public static class HotelProblemItem implements Serializable {
        /**
         * 催酒店确认*
         */
        public static final String ORDER_NEW_NEEDCONFIRM = "ORDER_NEW_NEEDCONFIRM";
        /**
         * 申请返现*
         */
        public static final String APPLY_CASH_BACK = "APPLY_CASH_BACK";

        public static final String GET_CASH_BACK = "CASH_BACK";
        /**
         * 酒店查不到订单*
         */
        public static final String CHECKIN_BEFORE_ORDER_NOTFOUND = "CHECKIN_BEFORE_ORDER_NOTFOUND";
        /**
         * 酒店价格变了*
         */
        public static final String CHECKIN_BEFORE_PRICE_CHANGE = "CHECKIN_BEFORE_PRICE_CHANGE";
        /**
         * 我已入住，订单显示未入住*
         */
        public static final String CHECKIN_ORDER_STATE_NOSHOW_ERR = "CHECKIN_ORDER_STATE_NOSHOW_ERR";
        /**
         * 酒店说无房*
         */
        public static final String CHECKIN_BEFORE_ROOM_FULL = "CHECKIN_BEFORE_ROOM_FULL";
        /**
         * 返现已领，如何提现*
         */
        public static final String CHECKOUT_CASHBACK_WITHDRAWLS = "CHECKOUT_CASHBACK_WITHDRAWLS";
        /**
         * 返现金额不一致*
         */
        public static final String CHECKOUT_CASHBACK_WRONG_AMOUNT = "CHECKOUT_CASHBACK_WRONG_AMOUNT";
        /**
         * 按钮文字*
         */
        public String topic;
        /**
         * 处理样式*
         */
        public String intentType;

        public String intentAction;

        public String actId;

        public String tipContent;

        public String feedBackType;

        public String feedBackState;

        public String feedBackStateName;

        public int color;

        public String scheme;

    }

}

