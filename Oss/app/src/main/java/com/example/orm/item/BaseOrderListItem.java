package com.example.orm.item;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单卡片基础类型
 * Created by jbg168 on 15/1/16.
 */
public class BaseOrderListItem implements BaseResult.BaseData {
    private static final long serialVersionUID = 1L;

    /**
     * <pre>
     *     卡片类型
     * 1：机票
     * 2：酒店
     * 3：火车票
     * 4：车车
     * 5: 机推酒
     * </pre>
     */
    public int cardType;
    /**
     * 订单价格
     */
    public String orderPrice;
    /**
     * 货币符号(￥,$,HKD等),在orderPrice字段前面加上此字段 显示(货币符号+货币金额)
     */
    public String moneyTypeView = "￥";
    /**
     * 折算的人民币金额,客户端判断如果此字段有值，那么客户端需加上人民币货币符号￥ 和  convertPrice共同展示，如果没有值不显示任何东西。
     */
    public String convertPrice;
    /**
     * 订单状态
     */
    public String orderStatus;
    /**
     * 订单状态描述
     */
    public String orderStatusStr;
    /**
     * 订单状态颜色
     */
    public int orderStatusColor;
    /**
     * 订单号 eg:“100095289623”
     */
    public String orderNo;
    public String orderSign;
    /**
     * 如果支持租车，客户端跳转时，要带这个字段
     */
    public String phoneAKSign;
    /**
     * OrderAction
     */
    public ArrayList<ValidOrderListResult.OrderCardAction> orderActions;
    /**
     * 分享文案*
     */
    public String shareText;
    /**
     * 分享标题*
     */
    public String shareTitle;
    /**
     * 提醒标题*
     */
    public String remindTitle;
    /**
     * 位置*
     */
    public String remindPoi;
    /**
     * 开始时间  yyyy-MM-dd HH:mm*
     */
    public String remindStart;
    /**
     * 结束时间  yyyy-MM-dd HH:mm*
     */
    public String remindEnd;
    /**
     * 提醒  yyyy-MM-dd HH:mm*
     */
    public String remindSpace;
    /**
     * touch URL*
     */
    public String remindUrl;
    /**
     * 备注*
     */
    public String remindNote;
    /**
     * 0-非共享，1-共享订单（被动），2-已共享（主动） *
     */
    public int shareType;
    /**
     * 系统标识码 需要回传给后端   *
     */
    public String sysCode;
    /**
     * 发票状态码
     * 1已寄送 2未寄送 3无寄送信息
     */
    public int receiptStatus;
    /**
     * 发票状态文案的颜色*
     */
    public int receiptStatusColor;
    public String shareTip;//共享提示 仅订单入住人可以凭共享订单办理入住

    public List<ValidOrderListResult.HisEntry> hisEntry;
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseOrderListItem)) {
            return false;
        }

        BaseOrderListItem that = (BaseOrderListItem) o;

        if (cardType != that.cardType) {
            return false;
        }
        if (orderStatusColor != that.orderStatusColor) {
            return false;
        }
        if (orderActions != null ? !orderActions.equals(that.orderActions) : that.orderActions != null) {
            return false;
        }
        if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) {
            return false;
        }
        if (orderPrice != null ? !orderPrice.equals(that.orderPrice) : that.orderPrice != null) {
            return false;
        }
        if (moneyTypeView != null ? !moneyTypeView.equals(that.moneyTypeView) : that.moneyTypeView != null) {
            return false;
        }
        if (convertPrice != null ? !convertPrice.equals(that.convertPrice) : that.convertPrice != null) {
            return false;
        }
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) {
            return false;
        }
        if (orderStatusStr != null ? !orderStatusStr.equals(that.orderStatusStr) : that.orderStatusStr != null) {
            return false;
        }
        if (phoneAKSign != null ? !phoneAKSign.equals(that.phoneAKSign) : that.phoneAKSign != null) {
            return false;
        }
        if (remindEnd != null ? !remindEnd.equals(that.remindEnd) : that.remindEnd != null) {
            return false;
        }
        if (remindNote != null ? !remindNote.equals(that.remindNote) : that.remindNote != null) {
            return false;
        }
        if (remindPoi != null ? !remindPoi.equals(that.remindPoi) : that.remindPoi != null) {
            return false;
        }
        if (remindSpace != null ? !remindSpace.equals(that.remindSpace) : that.remindSpace != null) {
            return false;
        }
        if (remindStart != null ? !remindStart.equals(that.remindStart) : that.remindStart != null) {
            return false;
        }
        if (remindTitle != null ? !remindTitle.equals(that.remindTitle) : that.remindTitle != null) {
            return false;
        }
        if (remindUrl != null ? !remindUrl.equals(that.remindUrl) : that.remindUrl != null) {
            return false;
        }
        if (shareText != null ? !shareText.equals(that.shareText) : that.shareText != null) {
            return false;
        }
        if (shareTitle != null ? !shareTitle.equals(that.shareTitle) : that.shareTitle != null) {
            return false;
        }
        if (sysCode != null ? !sysCode.equals(that.sysCode) : that.sysCode != null) {
            return false;
        }
        if (receiptStatus != that.receiptStatus) {
            return false;
        }
        if (receiptStatusColor != that.receiptStatusColor) {
            return false;
        }
        return true;
    }

}
