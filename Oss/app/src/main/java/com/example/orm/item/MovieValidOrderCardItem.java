package com.example.orm.item;

import java.util.List;

/**
 * Created by angelo.zhang on 15/3/31.
 * 电影票订单Item,单个电影票订单
 */
public class MovieValidOrderCardItem extends BaseOrderListItem {
	public static final String TAG = MovieValidOrderCardItem.class.getSimpleName();
	private static final long serialVersionUID = 1L;

	public MovieOrderCardData business;

	public MovieValidOrderCardItem() {
		cardType = ValidOrderListResult.MOVIE_ORDER;
	}

	public static class MovieOrderCardData implements BaseResult.BaseData {
		private static final long serialVersionUID = 1L;

		/* 是否可以支付 */
		public boolean canPay;

		/* 用户卡片排序的时间点，目前为订单更新的时间 */
		public String sortTime;

		/* 下单时间 */
		public String orderTime;

		/* 订单状态代码 0:待支付；4：未消费；5：已消费；6：退款中；7：已退款 */
		public String orderStatus;

		/* 订单状态描述 0:待支付；4：未消费；5：已消费；6：退款中；7：已退款 */
		public String orderStatusDesc;

		/* 订单号 */
		public String orderNo;

		/* 订单新旧类型 0:老订单 1:新订单 */
		public int orderType;

		/* */
		public String orderPrice;

		/* */
		public String sysCode;

		/* 订单状态颜色 蓝色:0x1ba9ba 红色:0xff4500 */
		public String orderStatusColor;

		/* 手机号码 */
		public String mobile;

		/* 影院名称 */
		public List<CinemaName> cinemaName;

		public static class CinemaName implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			/* 内容-影院名称 */
			public String value;

			/* 字体颜色 */
			public String color;

			/* 字体大小 */
			public String fontSize;

			/* 字体是否加粗 */
			public boolean bold;
		}

		/* 影院地址 */
		public String cinemaAddress;

		/* 电影名称 */
		public String movieTitle;

		/* 场次信息 */
		public List<ShowDesc> showDesc;

		public static class ShowDesc implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			/* 内容-电影描述 */
			public String value;

			/* 字体颜色 */
			public String color;

			/* 字体大小 */
			public String fontSize;

			/* 字体是否加粗 */
			public boolean bold;
		}

		/* 座位信息 */
		public List<SeatsInfo> seatsInfo;

		public static class SeatsInfo implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			/* 内容-座位信息 */
			public String value;

			/* 字体颜色 */
			public String color;

			/* 字体大小 */
			public String fontSize;

			/* 字体是否加粗 */
			public boolean bold;
		}

		/* 购买数量 */
		public int buyCount;

		/* 订单总金额 */
		public String totalMoney;

		/* 额外提示信息 */
		public String extraInfo;

		/* 额外退款提示信息 */
		public String extraRefundInfo;

		/* 额外退款提示信息颜色 */
		public int extraRefundInfoColor;

		/* 剩余支付时间 */
		public long leftTime;

		/* 订单过期时间 */
		public long deadTime;

		/* 是否展示查看更多 */
		public String codesDesc;

		/* 电影订单支付码等列表 */
		public List<MovieOrderCodes> codes;

		public static class MovieOrderCodes implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			/* 标签，兑换码/密码 */
			public String label;

			/* 文本text，兑换码文本/密码文本 */
			public String text;

			/*  */
			public String reserve;

			/* 文本颜色 */
			public int color;
		}

		/* 电影院位置坐标相关信息 */
		public MvCoordinates coordinate;

		/**
		 * 电影院地址/坐标相关
		 */
		public static class MovieCoordinate implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			// 影院坐标
			public String xy;

			// 影院地址
			public String address;

			// 影院名称
			public String cinemaName;

			// 影院电话
			public String cinemaTel;

			// 距离
			public String distance;

			// 距离描述
			public String distanceStr;
		}

		/**
		 * 电影院地址/坐标相关
		 * NOTE:使用 {@link MovieCoordinate}取不到数据,必须使用{@link MvCoordinates}才能取到相关信息.
		 */
		public static class MvCoordinates implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			/* 团品分店地址 */
			public String address;

			/* 地址 */
			public String title;

			/* 团品分店距离 */
			public int distance;

			/* 团品分店经纬度 */
			public String xy;

			/* 酒店名称 2012十月版本新增 */
			public String hotelName;

			/* 酒店电话 2012十月版本新增 */
			public String hotelTel;

			/* 当前团品ID 2014七月版本新增 */
			public String itemId;

			/* 分店距离 2014七月版本新增 */
			public String distanceStr;

			/* 当前分店所在城市 2014七月版本新增 */
			public String city;

			/* 地标类型 2014七月版本新增 */
			public String type;

			/* 酒店SEQ 2014七月版本新增 */
			public String hotel_seq;
		}

		/* 电影订单操作列表 */
		public List<MovieOrderActions> movieActions;

		public static class MovieOrderActions implements BaseResult.BaseData {
			private static final long serialVersionUID = 1L;

			// 操作id：1-立即支付，2-退款详情,3-我的红包,4-拨打电话
			public int actId;

			// ActionTitle，操作名称, eg：立即支付，退款详情...
			public String menu;

			// Action内容,弹窗需要显示的内容
			public String content;

			// 执行操作
			public String scheme;
		}
	}
}
