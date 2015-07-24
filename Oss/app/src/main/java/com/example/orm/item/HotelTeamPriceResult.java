package com.example.orm.item;


import java.io.Serializable;

/**
 * Created by zaixi.cui on 2014/12/23. wiki 地址：http://wiki.corp.qunar.com/pages/viewpage.action?pageId=68228663
 * 拼团订单列表页的步骤：1.根据订单返回结果，展示可以拼团的订单。2.把所有可以拼团的订单号拼接起来，发送请求，请求拼团的具体消息 3.根据消息刷新订单列表
 */
public class HotelTeamPriceResult extends BaseResult {
	public static final String TAG = "HotelTeamPriceResult";
	public HotelTeamPriceData data;

	public static class HotelTeamPriceData implements BaseResult.BaseData {
		private static final long serialVersionUID = 1L;
		public RelatedProductInfo[] relatedProductInfos;

	}

	public static class RelatedProductInfo implements Serializable {
		/**
		 * 订单号
		 */
		public String orderNum;
		/**
		 * 仅差n间成团 d天hh小时后结束
		 */
		public String teamPriceContent;
		/**
		 * 订单卡片仅查N间成团,N的颜色
		 */
		public int[] remainColor;
		/**
		 * 订单卡片仅查N间成团,N的位置
		 */
		public int[][] remainPlace;
	}
}
