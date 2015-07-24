package com.example.orm.item;


import java.util.ArrayList;
import java.util.List;

public class GroupbuyValidOrderCardItem extends BaseOrderListItem {
	public static final String TAG = "GroupbuyValidOrderCardItem";

	public GroupbuyValidOrderCardItem() {
		cardType = 9;// ValidOrderListResult.GROUPBUY_ORDER;
	}

	private static final long serialVersionUID = 1L;

	public GroupbuyOrderCardData business;
	public static class GroupbuyOrderCardData implements BaseResult.BaseData{
		private static final long serialVersionUID = 1L;
		public boolean canPay; //是否可以支付	所有可以支付的订单值都为true,可以支付的会置顶显示

		public String sortTime;//用于卡片排序的时间点	目前为订单更新时间

		public String orderStatus;//订单状态码	0:待支付；4：未消费；5：已消费；6：退款中

		public String orderStatusStr; //订单状态描述	0:待支付；4：未消费；5：已消费；6：退款中

		public int orderType; // 0老订单 1新订单

		public String orderStatusDesc;
		/** 订单状态颜色 */
		public int orderStatusColor;
		/** 订单价格 */
		public String orderPrice;

		public String mobile;//电话

		public String teamId;//团品id

		public String packageId;//套餐团品的套餐id

		public String sourceType;//1. 表示HMS上单 2. 表示CRM供应链提供 3. 表示OTA 4. 表示老团购后台

		public String type;//hotelteam

		public String hotelName;//酒店名称
		public String	hotelPhoneNum;

		/* team-svr新接口 根据partner_id对应的名称 */
		public String productName;

		public String hotelSeq;//酒店序列

		public String hotelImage;//酒店图片url

		public String roomType;//房型

		public int buyCount;//购买数量

		public String validDate;// 券的截止日期

		public String validDateExtra;// 有效期附加说明

		public String consumeDate;// 券的消费日期

		public String address;// 酒店地址

		public String voucherIds;// 券号
		public String secret;    // 券号 密码
		public String vouchersDesc;// 多张会显示“X张”
		public String refundExtraDescription;//退款描述文字   退款确认中
		public String bookingCheckinDate; // 预约入住日期
		public int bookingCheckinDateColor; //  预约入住日期颜色

		public long bookingId; // 预约id

		public String routeURL;

		public ArrayList<TuanActions> tuanActions;

		public GroupbuyCoordinates coordinate;

		public static class TuanActions implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;
			public int actId; //操作id 1：立即支付；2：电话预约；3：查看分店； 4：查看地图；5：骆驼币商城；6：我要点评；7：退款详情
			public String menu;// 操作名称
			public String content;// 弹窗需要显示的内容
			public String scheme;// 执行操作
		}

		/** 订单卡片的投诉 */
		public List<GroupbuyHotelProblemAction> problemActions;

		public static class GroupbuyHotelProblemAction implements BaseResult.BaseData {
			/** 按钮文字* */
			public String topic;
			/** 处理样式* */
			public String intentType;

			public String intentAction;

			public String actId;

			public String tipContent;

			public String feedBackType;

			public String feedBackState;

			public String feedBackStateName;

			public int color;

		}
	}
}
