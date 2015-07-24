package com.example.orm.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wei.fang on 2015/5/14.
 */
public class ICarOrderItem extends BaseOrderListItem  {

    public ICarOrderInfo business;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ICarOrderItem that = (ICarOrderItem) o;

        if (business != null ? !business.equals(that.business) : that.business != null)
            return false;

        return true;
    }

    public static class ICarOrderInfo implements Serializable {
        /**
         * 卡片标题*
         */
        public String title;
        /**
         * 订单状态的文字描述*
         */
        public String orderStatusName;
        /**订单状态颜色**/
        public int orderStatusColor;
        /**
         * 订单支付状态*
         */
        public int payStatus;
        /**
         * 国外客服电话*
         */
        public String interPhone;
        /**
         * 国内客服电话*
         */
        public String phone;
        /**
         * 司机电话*
         */
        public String driverPhone;
        /**
         * 车辆图片*
         */
        public String carPicUrl;
        /**
         * 预约乘车时间月和日*
         */
        public String takeDate;
        /**
         * 预约乘车时间时和分*
         */
        public String takeHour;
        /**
         * 出发地*
         */
        public String departurePlace;
        /**
         * 目的地*
         */
        public String destination;
        /**
         * 订单总金额（人民币*
         */
        public String price;
        /**
         * 下单时间*
         */
        public String createTime;
        /**
         * 提示文案*
         */
        public String tip;
        /**
         * 服务类型*
         */
        public int serviceType;
        /**
         * 订单类型*
         */
        public int orderType;

        public String orderNo;
        public String orderSign;
        /**国内固化拨打提醒**/
        public String mobileTip	;


        public ArrayList<ValidOrderListResult.OrderCardAction> orderActions;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ICarOrderInfo that = (ICarOrderInfo) o;

            if (orderStatusColor != that.orderStatusColor) return false;
            if (orderType != that.orderType) return false;
            if (payStatus != that.payStatus) return false;
            if (serviceType != that.serviceType) return false;
            if (carPicUrl != null ? !carPicUrl.equals(that.carPicUrl) : that.carPicUrl != null)
                return false;
            if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null)
                return false;
            if (departurePlace != null ? !departurePlace.equals(that.departurePlace) : that.departurePlace != null)
                return false;
            if (destination != null ? !destination.equals(that.destination) : that.destination != null)
                return false;
            if (driverPhone != null ? !driverPhone.equals(that.driverPhone) : that.driverPhone != null)
                return false;
            if (interPhone != null ? !interPhone.equals(that.interPhone) : that.interPhone != null)
                return false;
            if (orderActions != null ? !orderActions.equals(that.orderActions) : that.orderActions != null)
                return false;
            if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null)
                return false;
            if (orderSign != null ? !orderSign.equals(that.orderSign) : that.orderSign != null)
                return false;
            if (orderStatusName != null ? !orderStatusName.equals(that.orderStatusName) : that.orderStatusName != null)
                return false;
            if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
            if (price != null ? !price.equals(that.price) : that.price != null) return false;
            if (takeDate != null ? !takeDate.equals(that.takeDate) : that.takeDate != null)
                return false;
            if (takeHour != null ? !takeHour.equals(that.takeHour) : that.takeHour != null)
                return false;
            if (tip != null ? !tip.equals(that.tip) : that.tip != null) return false;
            if (title != null ? !title.equals(that.title) : that.title != null) return false;
            if (mobileTip != null ? !mobileTip.equals(that.mobileTip) : that.mobileTip != null) return false;
            return true;
        }


    }

}
