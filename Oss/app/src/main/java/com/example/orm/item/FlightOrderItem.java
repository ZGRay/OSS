package com.example.orm.item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jbg168 on 15/1/16.
 */
public class FlightOrderItem extends BaseOrderListItem {
    private static final long serialVersionUID = 1L;

    /** 出发航班信息 */
    public ArrayList<FlightInfo> dptinfo;
    /** 返程航班信息 */
    public ArrayList<FlightInfo> arrinfo;
    /** 域名 */
    public String host;
    /** otaType */
    public int otaType;
    /** 请求订单详情的refer 1:本地订单的订单列表  2:我的订单列表 3:订单查询到订单订单列表 51-短信查看  52-二维码查看  53-push消息查看 */
    public int refer = 2;
    /** 扩展字段 */
    public String extparams;
    /** 是否可以值机 */
    public boolean cflag;
    /** 接送机跳转Scheme，如果有该字段则展示接送机服务 */
    public boolean carScheme;
    /** 国际航班时间提示 */
    public String interNotice;

    public ValidOrderListResult.HotelRecommed hotel;

    /** 卡片顶部 航空公司+航班号 拼接 包含中转航班 */
    public String title;

    /** 值机开放时间 */
    public String cbeginTime;
    /** 值机尚未开放提醒 */
    public String checkInDes;

    /**
     * 订单卡片上的questions
     */
    public OrderQuestionAction[] questions;
    /**
     * 问题列表描述
     */
    public  String questionsDesc;

    public String domain;

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlightOrderItem)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        FlightOrderItem that = (FlightOrderItem) o;

        if (carScheme != that.carScheme) {
            return false;
        }
        if (cflag != that.cflag) {
            return false;
        }
        if (otaType != that.otaType) {
            return false;
        }
        if (refer != that.refer) {
            return false;
        }
        if (arrinfo != null ? !arrinfo.equals(that.arrinfo) : that.arrinfo != null) {
            return false;
        }
        if (cbeginTime != null ? !cbeginTime.equals(that.cbeginTime) : that.cbeginTime != null) {
            return false;
        }
        if (checkInDes != null ? !checkInDes.equals(that.checkInDes) : that.checkInDes != null) {
            return false;
        }
        if (dptinfo != null ? !dptinfo.equals(that.dptinfo) : that.dptinfo != null) {
            return false;
        }
        if (extparams != null ? !extparams.equals(that.extparams) : that.extparams != null) {
            return false;
        }
        if (host != null ? !host.equals(that.host) : that.host != null) {
            return false;
        }
        if (hotel != null ? !hotel.equals(that.hotel) : that.hotel != null) {
            return false;
        }
        if (interNotice != null ? !interNotice.equals(that.interNotice) : that.interNotice != null) {
            return false;
        }
        if (title != null ? !title.equals(that.title) : that.title != null) {
            return false;
        }
        if (domain != null ? !domain.equals(that.domain) : that.domain != null) {
            return false;
        }

        return super.equals(o);
    }


    public static class FlightInfo implements Serializable {
        private static final long serialVersionUID = 1L;

        /** 出发日期 */
        public String depDateFormat;
        /** 出发时间 */
        public String depTime;
        /** 出发城市 */
        public String depCity;
        /** 出发航站楼 */
        public String depTerminal;
        /** 出发机场 */
        public String depAirport;
        /** 到达时间 */
        public String arrTime;
        /** 到达城市 */
        public String arrCity;
        /** 到达机场 */
        public String arrAirport;
        /** 到达航站楼 */
        public String arrTerminal;
        /** 航班号 */
        public String airCode;
        /** 航空公司 */
        public String shortCompany;

        /** 到达日期 */
        public String arrDateFormat;
        /** 订单类型描述 eg:"往饭包" */
        public String flightTypeDesc;

        //航班动态信息
        /** 原计划时间信息 */
        public String ptime;
        /** 航班状态 eg:延误\起飞\迫降\正常\到达\备降\取消\其它 */
        public String flightStatus;
        /** 航班状态背景色 */
        public int flightStatusColor;
        /** 值机柜台 */
        public String checkinCounter;
        /** 登机口 */
        public String boardgate;
        /** 行李转盘 */
        public String baggageTurntable;
        /** 去程/返程，该字段有值时展示去返信息 */
        public String direction;
        /** 中转时间信息 */
        public String remainTime;
        /** 中转城市 */
        public String remainCity;
        /** 显示航班起飞 “预计 计划”字样 */
        public String depText;
        /** 显示航班到达 “预计 计划”字样 */
        public String arrText;

        public String flightWarning;

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FlightInfo)) {
                return false;
            }

            FlightInfo that = (FlightInfo) o;

            if (flightStatusColor != that.flightStatusColor) {
                return false;
            }
            if (airCode != null ? !airCode.equals(that.airCode) : that.airCode != null) {
                return false;
            }
            if (arrAirport != null ? !arrAirport.equals(that.arrAirport) : that.arrAirport != null) {
                return false;
            }
            if (arrCity != null ? !arrCity.equals(that.arrCity) : that.arrCity != null) {
                return false;
            }
            if (arrDateFormat != null ? !arrDateFormat.equals(that.arrDateFormat) : that.arrDateFormat != null) {
                return false;
            }
            if (arrTerminal != null ? !arrTerminal.equals(that.arrTerminal) : that.arrTerminal != null) {
                return false;
            }
            if (arrText != null ? !arrText.equals(that.arrText) : that.arrText != null) {
                return false;
            }
            if (arrTime != null ? !arrTime.equals(that.arrTime) : that.arrTime != null) {
                return false;
            }
            if (baggageTurntable != null ? !baggageTurntable.equals(that.baggageTurntable) : that.baggageTurntable !=
                    null) {
                return false;
            }
            if (boardgate != null ? !boardgate.equals(that.boardgate) : that.boardgate != null) {
                return false;
            }
            if (checkinCounter != null ? !checkinCounter.equals(that.checkinCounter) : that.checkinCounter != null) {
                return false;
            }
            if (depAirport != null ? !depAirport.equals(that.depAirport) : that.depAirport != null) {
                return false;
            }
            if (depCity != null ? !depCity.equals(that.depCity) : that.depCity != null) {
                return false;
            }
            if (depDateFormat != null ? !depDateFormat.equals(that.depDateFormat) : that.depDateFormat != null) {
                return false;
            }
            if (depTerminal != null ? !depTerminal.equals(that.depTerminal) : that.depTerminal != null) {
                return false;
            }
            if (depText != null ? !depText.equals(that.depText) : that.depText != null) {
                return false;
            }
            if (depTime != null ? !depTime.equals(that.depTime) : that.depTime != null) {
                return false;
            }
            if (direction != null ? !direction.equals(that.direction) : that.direction != null) {
                return false;
            }
            if (flightStatus != null ? !flightStatus.equals(that.flightStatus) : that.flightStatus != null) {
                return false;
            }
            if (flightTypeDesc != null ? !flightTypeDesc.equals(that.flightTypeDesc) : that.flightTypeDesc != null) {
                return false;
            }
            if (ptime != null ? !ptime.equals(that.ptime) : that.ptime != null) {
                return false;
            }
            if (remainCity != null ? !remainCity.equals(that.remainCity) : that.remainCity != null) {
                return false;
            }
            if (remainTime != null ? !remainTime.equals(that.remainTime) : that.remainTime != null) {
                return false;
            }
            if (shortCompany != null ? !shortCompany.equals(that.shortCompany) : that.shortCompany != null) {
                return false;
            }

            if (flightWarning != null ? !flightWarning.equals(that.flightWarning) : that.flightWarning != null) {
                return false;
            }

            return true;
        }

    }

}
