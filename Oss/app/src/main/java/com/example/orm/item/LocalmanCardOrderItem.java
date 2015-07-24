package com.example.orm.item;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by zhao.liu on 2015/1/14.
 */
public class LocalmanCardOrderItem extends BaseOrderListItem {

	private static final long serialVersionUID = 1L;
	public LocalmanOrderInfo business;

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof LocalmanCardOrderItem)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		LocalmanCardOrderItem that = (LocalmanCardOrderItem) o;

		if (business != null ? !business.equals(that.business) : that.business != null) {
			return false;
		}
		return true;
	}

	public static class LocalmanOrderInfo implements Serializable {
		private static final long serialVersionUID = 1L;

		public boolean canPay;
		public ArrayList<ValidOrderListResult.OrderCardAction> orderActions;
		public String orderNo;
		public String orderStatus;
		public long orderTime;
		public long sortTime;
		public LocalmanOrderItem orderDetail;

		public String remindEnd;
		public String remindNote;
		public String remindPoi;
		public String remindSpace;
		public String remindStart;
		public String remindTitle;
		public String remindUrl;

		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof LocalmanOrderInfo)) {
				return false;
			}
			LocalmanOrderInfo that = (LocalmanOrderInfo) o;
			if (canPay != that.canPay) {
				return false;
			}
			if (orderTime != that.orderTime) {
				return false;
			}
			if (sortTime != that.sortTime) {
				return false;
			}
			if (orderActions != null ? !orderActions.equals(that.orderActions) : that.orderActions != null) {
				return false;
			}
			if (orderNo != null ? !orderNo.equals(that.orderNo) : that.orderNo != null) {
				return false;
			}
			if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) {
				return false;
			}
			if (orderDetail != null ? !orderDetail.equals(that.orderDetail) : that.orderDetail != null) {
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
			return true;
		}

	}
    public static class LocalmanOrderItem implements Serializable {
        private static final long serialVersionUID = 1L;

        public long id;
        public String orderId;
        public String serviceInfo;
        public String travelDate;
        public String sales;
        public int status;
        public String statusStr;
        public String lmNick;
        public String lmContact;//显示用
        public String lmDialContact;//拨打用
        public String createTime;
        public String detailUrl;
        //出行人数
        public int personNum;
        //服务次数（null的时候显示出行人数）
        public Integer serviceNum;
        //退款说明
        public String refundReason;
        //用户说明
        public String userRemark;
        //改价说明
        public String priceChangeRemark;
        //当地人补充说明
        public String lmRemark;
        //联系人姓名
        public String contactName;
        //联系人手机号
        public String contactMobile;
        //当地人IM ID
        public int localmanImId;
        //改价后单位
        public String profit;
        //下步操作列表，使用逗号分隔
        //操作类型 PAY(1, "用户支付订单"),REQUIRE_REFUND(2, "用户申请退款"),
        // REQUIRE_CANCEL(3, "用户申请退款"),CONFIRM_PAY(4, "用户确认支付")
        public String operations;
        //查询订单Token
        public String orderToken;


        public String areaName;
        public String category;
        public String portrait;
        public String guideName;
        public String subCategoryName;
        public String orderStatusColor;
        public String shareText;
        public String shareTitle;
        //  订单明细touch add 15.2.5版本
        public String orderDetail;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof LocalmanOrderItem)) {
                return false;
            }
            LocalmanOrderItem that = (LocalmanOrderItem) o;

            if (orderDetail != null ? !orderDetail.equals(that.orderDetail) : that.orderDetail != null) {
                return false;
            }

            if (areaName != null ? !areaName.equals(that.areaName) : that.areaName != null) {
                return false;
            }
            if (category != null ? !category.equals(that.category) : that.category != null) {
                return false;
            }
            if (subCategoryName != null ? !subCategoryName.equals(that.subCategoryName) : that.subCategoryName != null) {
                return false;
            }
            if (contactMobile != null ? !contactMobile.equals(that.contactMobile) : that.contactMobile != null) {
                return false;
            }
            if (contactName != null ? !contactName.equals(that.contactName) : that.contactName != null) {
                return false;
            }
            if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) {
                return false;
            }
            if (id != that.id) {
                return false;
            }
            if (localmanImId != that.localmanImId) {
                return false;
            }
            if (personNum != that.personNum) {
                return false;
            }
            if (status != that.status) {
                return false;
            }
            if (lmContact != null ? !lmContact.equals(that.lmContact) : that.lmContact != null) {
                return false;
            }
            if (lmDialContact != null ? !lmDialContact.equals(that.lmDialContact) : that.lmDialContact != null) {
                return false;
            }
            if (lmNick != null ? !lmNick.equals(that.lmNick) : that.lmNick != null) {
                return false;
            }
            if (operations != null ? !operations.equals(that.operations) : that.operations != null) {
                return false;
            }
            if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) {
                return false;
            }
            if (profit != null ? !profit.equals(that.profit) : that.profit != null) {
                return false;
            }
            if (refundReason != null ? !refundReason.equals(that.refundReason) : that.refundReason != null) {
                return false;
            }
            if (sales != null ? !sales.equals(that.sales) : that.sales != null) {
                return false;
            }
            if (serviceInfo != null ? !serviceInfo.equals(that.serviceInfo) : that.serviceInfo != null) {
                return false;
            }
            if (statusStr != null ? !statusStr.equals(that.statusStr) : that.statusStr != null) {
                return false;
            }
            if (travelDate != null ? !travelDate.equals(that.travelDate) : that.travelDate != null) {
                return false;
            }
            if (userRemark != null ? !userRemark.equals(that.userRemark) : that.userRemark != null) {
                return false;
            }
            if (portrait != null ? !portrait.equals(that.portrait) : that.portrait != null) {
                return false;
            }
            if (guideName != null ? !guideName.equals(that.guideName) : that.guideName != null) {
                return false;
            }
            if (detailUrl != null ? !detailUrl.equals(that.detailUrl) : that.detailUrl != null) {
                return false;
            }
            if (serviceNum != null ? serviceNum!=that.serviceNum : that.serviceNum != null) {
                return false;
            }
            if (shareText != null ? shareText!=that.shareText : that.shareText != null) {
                return false;
            }
            if (orderStatusColor != null ? orderStatusColor!=that.orderStatusColor : that.orderStatusColor != null) {
                return false;
            }

            return true;
        }
    }
}
