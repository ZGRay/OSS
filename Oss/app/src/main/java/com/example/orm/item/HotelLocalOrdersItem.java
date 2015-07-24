package com.example.orm.item;


import java.io.Serializable;

public class HotelLocalOrdersItem implements Serializable {
    private static final long serialVersionUID = 1L;

    public String orderNo; // 订单id
    public String hotelname; // 酒店标题
    public String fromDate; // 入住日期
    public String toDate; // 退房日期
    public String phone; // 联系人手机号
    public String concat; // 联系人
    public String orderDate; // 下单时间
    public String currencySign = "￥";
    public String price; // 订单金额
    public int orderType = 0; // 0 PPB订单 1 lastMin订单 2 钟点房订单
    public String wrapperID;
    public String extra;
    public String deleteWarn;

	public String stayTime;//钟点房可住时长
	public String arriveTime;//钟点房最晚到店时间
}
