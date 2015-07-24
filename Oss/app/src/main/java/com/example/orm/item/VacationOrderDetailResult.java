package com.example.orm.item;

import java.util.List;

public class VacationOrderDetailResult extends BaseResult {

    private static final long serialVersionUID = -2308345553652004065L;
    public static final String TAG = "VacationOrderDetailResult";
    public static final String VACATION_TYPE_VISA = "visa";
	public VacationOrderDetaillData data;

	public static class VacationOrderBusiness implements BaseData {
		private static final long serialVersionUID = 1L;
		public VacationOrderDetaillData orderInfo;
		public List<VacationOrderActions> orderActions;
		public boolean canPay;

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof VacationOrderBusiness)) {
				return false;
			}
			VacationOrderBusiness that = (VacationOrderBusiness) o;
			if (orderInfo != null ? !orderInfo.equals(that.orderInfo) : that.orderInfo != null) {
				return false;
			}
			return true;
		}
	}

	public static class VacationOrderActions implements BaseData {
		public int actId;
		public String menu;
		public String scheme;
	}

    public static class VacationOrderDetaillData  implements BaseData {
        private static final long serialVersionUID = 3360838684314219779L;
        public String enId;//订单加密ID
        public String displayId;//订单流水号
        public int price;//成人单价
        public String productName;//产品名称
		public Integer productVersion;//订单产品快照版本
        public int adultNum;//成人数量
        public int childNum;//儿童单价
        public int childPrice;//儿童单价
		public int taoCanNum;//套餐数量
        public int roomNum;//房间数量
        public String mmType;//支付方式
        public String depTimeStr;//出发日期
        public int money;//（成人＋儿童）＊数量
		//类型产品有效期开始时间
		public long displayBeginDate;
		//类型产品有效期结束时间
		public long displayEndDate;
		public String shareTitle;
		public String shareText;
		/**
		 * 订单状态
		 0- 未知状态 （停用）
		 1- 未支付，等待供应商确认订单
		 2- 未支付，供应商已经确认订单
		 3-已支付，等待供应商申请资源  （停用）
		 4-已支付，等待用户确认消费
		 5-未支付，申请资源失败
		 6-待预授权，等待用户预授权 (支付预授权新增)
		 7-待确认，用户预授权成功，等待供应商确认 (支付预授权新增)
		 10-已支付，仲裁中     （停用）
		 11-已支付，订单金额异常
		 12-订单完成
		 13-订单取消
		 */
        public int orderStatus;//订单状态码
		public String orderStatusDesc;
        public String orderStatusStr;//订单状态值
        public String orderBtnStatusDesc;//订单状态说明
		public String orderBtnActionDesc;
        public boolean hasInsurance;//是否有保险
        public int insuranceOrigin;//保险总金额
        public long activityDiscountAmount;//产品优惠金额(现金红包金额也在里面)
		public long insuranceDiscountAmount;//保险优惠金额(现金红包金额也在里面)
		public long couponAmount;//代金券金额
        public String coupon;//消费码
        public String clientAddress;//地址
        public Boolean visaExpressFeeArrive;//签证快递
        public String createTime;//创建时间
        public int refundStatus;//退款状态
        public String src;//
        public String now;//服务器当前时间
        public String payDeadLine;//截止时间
        public String visaDate;//签证日期
		public long extraRoomCost;//房差
        public String qMethod;//取件方式
		public String payTime;//支付时间
        public Boolean isVisaExpressFree;//是否包含快递

        public String userAddress;//客户地址
        public VacationOrderDetailProduct product;
        public VacationTouristInfo touristsInfo;//联系人信息
		public VacationTcTaoCanInfo tcTaoCanInfo;//套餐包含
        public VacationOrderOperations operations;//
		public String qunarTel;
		public String userMessage;//留言信息


		public int payTimeMinute;
		public String visaType;
		public String visaBusinessHours;
    }

    public static class VacationOrderDetailProduct implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 4124954808961687815L;
        public String productType;//产品类型
        public int id;//产品ID
        public String enId;//产品加密ID
        public String name;//产品名称
        public int day;//几天几晚中天数
        public int night;//几天几晚中晚数
        public String departure;//出发地
		public String showArrive;
		public String arrive;
		public String shortUrl;//分享短链接
		public VacationImageInfos[] imageInfos; //分享的图片
        public boolean seckill;//是否秒杀
        public int secKillDurationWithCurrent;//秒杀的当前时间
        public VacationSupplier supplier;//供应商
        public Tel tel;//供应商电话
        public String vacationType;//度假或签证
		public Integer version;
		public String travelType;//
        public boolean isVisa() {
            return VACATION_TYPE_VISA.equalsIgnoreCase(productType);
        }
    }

	public static class VacationImageInfos implements BaseData {
		private static final long serialVersionUID = 1L;
		public String bigUrl;
		public String url;
	}
    public static class VacationSupplier implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public String enId;//供应商ID
        public boolean isDisplayTq;
        public String name;//供应商名称
        public String shopName;//店铺名称

    }

    public static class Tel implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public String telephone;//电话号码
        public String extension;//分机号

    }

    public static class VacationTouristInfo implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = -9012057419394617535L;
        public String contactMobile;//联系人电话
        public String userName;//联系人名称
        public List<VacationOrderPassenger> orderPassengers;

    }

	public static class VacationTcTaoCanInfo implements BaseData {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public List<VacationSights> sights;
		public String desc;
	}

	public static class VacationSights implements BaseData {
		private static final long serialVersionUID = 1L;
		public int orderId;
		public int product_id;
		public int supplier_id;
		public int sight_id;
		public String sight_name;
		public String use_date;
		public int num;
		public String ticket_type;
		public int status;
		public String operator;
		public long operate_time;
		public int relate_id;
		public String name;
	}

    public static class VacationOrderPassenger implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 5401966403486650503L;
        public int orderId;//订单号
        public String name;//旅客姓名
        public int abroadRecord;//出国记录
		public String abroadRecordStr;//出国记录
        public String birthday;//出生日期
        public boolean isAdult;//是否成人
        public boolean canceled;//
        public String country;//国家
        public int id;
        public int idType;//证件类型
        public String idNo;//证件号
        public String idTypeStr;//
        public String idValidDate;//有效期
        public boolean status;
        public String operateTime;
        public String isAdultStr;
        public boolean isSelected;
		public VacationOrderPassengerInsurance insurance;
    }

    public static class VacationOrderOperations implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public VacationOrderOperationItem cancel;
		public VacationOrderOperationItem refund;
    }

    public static class VacationOrderOperationItem implements BaseData {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public String name;
        public boolean support;

    }


	public static class VacationOrderPassengerInsurance implements BaseData {
		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		public String title ;
		public long  price ;
		public int  count ;
		public int status;
		public String statusDesc;
        public long effDate;
        public String enId;
        public List<VacationOrderOperationItem> operations;
	}
}
