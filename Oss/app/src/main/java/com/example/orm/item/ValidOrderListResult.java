package com.example.orm.item;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbg168 on 14-7-25.
 */
public class ValidOrderListResult extends BaseResult {
	private static final long serialVersionUID = 1L;
	public static final String TAG = ValidOrderListResult.class.getSimpleName();
	/**机票类型*/
	public static final int FLIGHT_ORDER = 1;
	/**酒店类型*/
	public static final int HOTEL_ORDER = 2;
	/**火车票类型*/
	public static final int TRAIN_ORDER = 3;
	/**车车类型*/
	public static final int CAR_ORDER = 4;
	/**机推酒类型*/
	public static final int F_2_HOTEL = 5;
	/**火车推酒类型**/
	public static final int T_2_HOTEL = 6;
	public static final int SIGHT = 7;
	/**酒店推身边类型**/
	public static final int H_2_Nearby = 8;

    /**团购订单类型*/
    public static final int GROUPBUY_ORDER = 9;

    /**当地人类型**/
    public static final int LOCALMAN_ORDER = 10;
    /**度假类型**/
    public static final int VACATION_ORDER = 11;
    /**国际车订单类型**/
    public static final int ICAR_ORDER = 12;
    /**专车类型**/
    public static final int DSELL_ORDER = 13;
    /**推送消息*/
    public static final int PUSH_MSG = 14;
    public static final int HISTORY_ORDER = 15;
    public static final int LOAD_MORE = 16;

    /**电影票订单类型**/
    public static final int MOVIE_ORDER = 17;

    public ValidOrderData data;

	public static class ValidOrderData implements BaseData {
		private static final long serialVersionUID = 1L;

		/** 订单卡片数量，判断是否加载更多用的 */
		public int count;
        public boolean loadMore;
        public int fromIndex;
		/** 共享订单提示：您有xx张来自朋友的共享订单 **/
		public String shareTip;
        public List<HisEntry> hisEntry;
        /**锚点的位置*/
        public int anchor;
        public List<TimeLine> timelines;



	}

	/**推荐身边，身边信息**/
	public static class NearbyRecommend implements  Serializable{
		/**推荐scheme**/
		public String scheme;
		/**推荐文案**/
		public String text;
		/**标识高亮显示的二维数组**/
		public int[][] colorSpan;

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof NearbyRecommend)) {
				return false;
			}

			NearbyRecommend that = (NearbyRecommend) o;

			if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null) {
				return false;
			}
			if (text != null ? !text.equals(that.text) : that.text != null) {
				return false;
			}

			return true;
		}
	}

	/**
	 * 机推酒，酒店信息
	 */
	public static class HotelRecommed implements Serializable {
		/** 入住时间 */
		public String fromDate;
		/** 离店时间 */
		public String toDate;
		/** 城市 */
		public String city;
		/** city url  例如beijing_city */
		public String cityUrl;
		/** 是否有低价酒店 */
		public boolean hasLowPrice;
		/** 记录日志所需 */
		public int fromForLog;
		/**
		 * 提示文案
		 */
		public String desc;
		/**
		 * 类似
		 * int[x]={start,end}这样的格式
		 * 例子：text=为保证你顺利入住，请在2分钟内完成支付
		 * colorSpan=[11, 12]，则显示为： 为保证你顺利入住，请在2分钟内完成支付
		 * 如果colorSpan=[[11, 12], ﻿﻿﻿[15, 19]]，则显示为： 为保证你顺利入住，请在2分钟内完成支付
		 */
		public int[][] colorSpan;
		/**星级 1 2 3 4 5 **/
		public String level;
		/**排序方式 0 1 2 **/
		public String sort;
		/**跳转酒店推广的链接**/
		public String scheme;


		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof HotelRecommed)) {
				return false;
			}

			HotelRecommed that = (HotelRecommed) o;

			if (fromForLog != that.fromForLog) {
				return false;
			}
			if (hasLowPrice != that.hasLowPrice) {
				return false;
			}
			if (city != null ? !city.equals(that.city) : that.city != null) {
				return false;
			}
			if (cityUrl != null ? !cityUrl.equals(that.cityUrl) : that.cityUrl != null) {
				return false;
			}
			if (desc != null ? !desc.equals(that.desc) : that.desc != null) {
				return false;
			}
			if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) {
				return false;
			}
			if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) {
				return false;
			}
			if (level != null ? !level.equals(that.level) : that.level != null) {
				return false;
			}
			if (sort != null ? !sort.equals(that.sort) : that.sort != null) {
				return false;
			}
			if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null) {
				return false;
			}

			return true;
		}
	}

	/**
	 * orderAction
	 */
	public static class OrderCardAction implements Serializable {
		/**
		 * <pre>
		 1	 立即支付
		 2	 立即担保
		 3	 联系酒店
		 4	 打车
		 5	 接送站服务
		 6	 联系商家
		 7	 联系司机
		 8	 航班选座
		 9	 接送机服务
		 10	 查看详情
		 11	 接机服务
		 12	 送机服务
		 13	 分享
		 14	 提醒
		 15	 联系服务商（门票自定义）
		 16	 领取返现（门票自定义）
		 17	 我已使用
		 18	 发票状态
		 19	 共享订单
		 20	 共享记录
		 </pre>
		 */
        public static final String PUB_CALL_HOTEL ="PUB_CALL_HOTEL";//联系酒店 3
        public static final String PUB_TAXI ="PUB_TAXI";//打车去酒店 4
        public static final String PUB_SHARE  ="PUB_SHARE";//分享  13
        public static final String PUB_REMIND ="PUB_REMIND";//提醒  14
        public static final String PUB_SHAREORDER ="PUB_SHAREORDER";//共享订单  19
        public static final String PUB_SHAREORDERLOG ="PUB_SHAREORDERLOG";//共享记录 20
        public static final String PUB_RECEIPT_SEND ="PUB_RECEIPT_SEND";//发票已寄送 21
        public static final String PUB_RECEIPT_UNSEND ="PUB_RECEIPT_UNSEND";//发票未寄送 22
        public static final String PUB_RECEIPT_UNKNOWN  ="PUB_RECEIPT_UNKNOWN ";//发票寄送状态未知 23
        public static final String PUB_SHUTTLE="PUB_SHUTTLE";//接送车，id=5

        public static final String HOTEL_PAY ="HOTEL_PAY";//立即支付  1
        public static final String HOTEL_INSURE ="HOTEL_INSURE";//立即担保  2
        public static final String HOTEL_DETAIL ="HOTEL_DETAIL";//查看详情  10
        public static final String HOTEL_MAP ="HOTEL_MAP";//跳转地图


        public static final String TRAIN_PAY="TRAIN_PAY";//立即支付,id=1
        public static final String TRAIN_DETAIL="TRAIN_DETAIL";//跳转到详情,原来没有id
        public static final String TRAIN_REFUND="TC_ORDER_REFUND";//退款详情,id=100



        public int actId;
		/** 操作文案 */
		public String menu;
		/** 跳转URL */
		public String scheme;
        public int iconId;
        public String intentAction;
        public String url;//加载图片URL
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof OrderCardAction)) {
				return false;
			}

			OrderCardAction that = (OrderCardAction) o;

			if (actId != that.actId) {
				return false;
			}
			if (menu != null ? !menu.equals(that.menu) : that.menu != null) {
				return false;
			}
			if (scheme != null ? !scheme.equals(that.scheme) : that.scheme != null) {
				return false;
			}

			return true;
		}
	}
    public static class HisEntry implements Serializable{
        public HisEntry() {
        }

        public HisEntry(String businessType, String businessName, int drawableId) {
            this.businessType = businessType;
            this.businessName = businessName;
            this.drawableId = drawableId;
        }

        public String businessType;
        public String businessName;
        public int drawableId;

    }
    public static class TimeLine implements  Serializable{
        public String date;
        public String groupId;
        public boolean today;
        public boolean anchor;
        public ArrayList<OrderCardAction> actions;
        public JSONArray ordercards;
        public ArrayList<BaseOrderListItem> list;
        /**
         * 不用get是防止被序列化
         */
        public ArrayList<BaseOrderListItem> parseValidOrders() {
            if (ArrayUtils.isEmpty(ordercards)) {
                return list = null;
            }
            if (list != null && list.size() == ordercards.size()) {
                return list;
            }
            list = new ArrayList<BaseOrderListItem>();
            for (int i = 0; i < ordercards.size(); i++) {
                JSONObject jobj = ordercards.getJSONObject(i);
                int orderType = jobj.getIntValue("cardType");
                BaseOrderListItem item = null;
                if (orderType == FLIGHT_ORDER) {
                    item = JSON.toJavaObject(jobj, FlightOrderItem.class);
//                    if (((FlightOrderItem) item).hotel != null) {
//                        FlightOrderItem fHotel = new FlightOrderItem();
//                        fHotel.cardType = F_2_HOTEL;
//                        fHotel.hotel = ((FlightOrderItem) item).hotel;
//                        list.add(item);
//                        list.add(fHotel);
//                        continue;
//                    }
                } else if (orderType == HOTEL_ORDER) {
                    item = JSON.toJavaObject(jobj, HotelOrderItem.class);
//                    if (((HotelOrderItem) item).nearby != null) {
//                        HotelOrderItem tHotel = new HotelOrderItem();
//                        tHotel.cardType = H_2_Nearby;
//                        tHotel.nearby = ((HotelOrderItem) item).nearby;
//                        list.add(item);
//                        list.add(tHotel);
//                        continue;
//                    }
                } else if (orderType == TRAIN_ORDER) {
                    item = JSON.toJavaObject(jobj, TrainOrderItem.class);
//                    if (((TrainOrderItem) item).hotel != null) {
//                        TrainOrderItem tHotel = new TrainOrderItem();
//                        tHotel.cardType = T_2_HOTEL;
//                        tHotel.hotel = ((TrainOrderItem) item).hotel;
//                        list.add(item);
//                        list.add(tHotel);
//                        continue;
//                    }
                } else if (orderType == CAR_ORDER) {
                    item = JSON.toJavaObject(jobj, CarOrderItem.class);
                } else if (orderType == SIGHT) {
                    item = JSON.toJavaObject(jobj, SightOrderItem.class);
                } else if (orderType == GROUPBUY_ORDER) {
                    item = JSON.toJavaObject(jobj, GroupbuyValidOrderCardItem.class);
                } else if(orderType==LOCALMAN_ORDER){
                    item = JSON.toJavaObject(jobj, LocalmanCardOrderItem.class);
                }else if(orderType==VACATION_ORDER){
                    item = JSON.toJavaObject(jobj, VacationOrderItem.class);
                } else if(orderType == ICAR_ORDER){
                    item = JSON.toJavaObject(jobj, ICarOrderItem.class);
                }else if (orderType == MOVIE_ORDER){
					item = JSON.toJavaObject(jobj, MovieValidOrderCardItem.class);
				}
                if (item != null) {
                    list.add(item);
                }
            }
            return list;
        }

        /**
         * 让list不参与序列化(序列化orders)
         */
        public ArrayList<BaseOrderListItem> getList() {
            return null;
        }
    }

}
