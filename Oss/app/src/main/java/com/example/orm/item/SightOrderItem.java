package com.example.orm.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pan.feng on 2014/10/17.
 * 门票订单卡片Item
 */
public class SightOrderItem extends BaseOrderListItem {
	public static final String TAG = SightOrderItem.class.getSimpleName();
	private static final long serialVersionUID = 1L;
	public SightOrderInfo business;

	public static class SightOrderInfo implements Serializable {
		private static final long serialVersionUID = 1L;
		/** 景点名称 **/
		public String sightName;
		/** 票名 **/
		public String ticketName;
		/** 门票类型 0、日历 1、分类 **/
		public String teamType;
		/** 订单金额 **/
		public String orderPrice;
		/** 游玩日期 **/
		public String date;
		/** 订单张数 **/
		public String count;
		/** 入园凭证  **/
		public ETicket eticket;
		/** 供应商名称 **/
		public String supplierName;
		/** 供应商电话 **/
		public String suplierPhone;
		/** 可用日期 **/
		public String validUseDateDescription;
		/** 不可用日期 **/
		public String invalidUseDateDescription;
		/** 订单状态描述 **/
		public String orderStatusDesc;
		/** 订单状态显示颜色 **/
		public int orderStatusColor;
		/** true可以进行下一步；false不能进行下一步，弹出提示信息 **/
		public boolean nextStepStatus;
		/** 不能进行下一步的提示信息 **/
		public String nextStepTip;

		public boolean canPay;
		public int cashBackState;
		/** 操作列表 * */
		public ArrayList<OrderAction> orderActions;
		/** 景点id **/
		public String sightId;
		/** 订单号 **/
		public String orderNo;
		/** 订单状态码 **/
		public String orderStatus;
		/** 下单时间 **/
		public String orderTime;
		/** 用于卡片排序的时间点 **/
		public String sortTime;
        /** 问题描述 **/
		public ArrayList<Question> questions;
		/** 投诉问题描述(2015.06.02新加) **/
		public ArrayList<OrderQuestion> orderActionList;
	}
	public static class OrderAction implements Serializable {
		private static final long serialVersionUID = 1L;
		public int actId;
		/** 操作文案 */
		public String menu;
		/** 跳转URL */
		public String scheme;
		public String content;
	}


    public static class ETicket implements Serializable {
        private static final long serialVersionUID = 1L;
        public String type;   // 入园凭证类型
        public String string;   // 入园凭证内容显示
        public String smsTemp;  // 取票和入园方式描述
        public String useDesc;  // 入园凭证号码
    }

    public static class Question implements Serializable {
        private static final long serialVersionUID = 1L;
        public int id;
        public String parentid;				//父问题id
        public String question;				//问题名称（无法入园、退款遇到问题、投诉供应商、返现问题）
        public int tagId;					//问题对应工单系统的id
        public String tips;					//提示文案
        public String dataOrder;			//顺序
        public boolean orderComplain;		//是否生成工单
        public ArrayList<Question> childQuestions;//子问题列表（结构跟question一致）
    }

	public static class OrderQuestion implements Serializable {
		private static final long serialVersionUID = 1L;
		public boolean isDirect;      //是否直接跳转
		public String tagName;        //操作名称
		public String directURL;      //跳转url
		public String tagId;          //操作编号

	}
}
