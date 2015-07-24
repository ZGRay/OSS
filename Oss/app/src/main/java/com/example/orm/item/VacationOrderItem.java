package com.example.orm.item;

/**
 * Created by jerry on 15-3-5.
 */
public class VacationOrderItem  extends BaseOrderListItem {
    private static final long serialVersionUID = 1L;
    public VacationOrderDetailResult.VacationOrderBusiness business;
    /*//支付金额
    public long money;
    //订单状态
    public int orderStatus;
    //订单状态
    public String orderStatusDesc;
    //房差
    public int extraRoomCost;
    //订单ID
    public String enId;
    //出游日期：
    public String depTimeStr;
    //类型产品有效期开始时间
    public long displayBeginDate;
    //类型产品有效期结束时间
    public long displayEndDate;
    public int day;
    public int night;
    //分享标题
    public String shareTitle;
    //分享内容
    public String shareContent;

    public boolean isVisa;*/
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

        VacationOrderItem that = (VacationOrderItem) o;

        return super.equals(o);
    }
}