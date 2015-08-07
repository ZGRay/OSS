package com.example.orm;

import java.io.File;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;
import com.example.orm.item.ValidOrderListResult;
import com.orm.demo.R;
import com.oss.common.model.DataBaseProvider;
import com.oss.storage.FileStorageMemoryCache;
import com.oss.storage.FileStorageMemche;
import com.oss.storage.FilteTest;
import com.oss.storage.HighSynFileStorage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class Aorm extends Activity {
    Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private TextView btn5;
    private Button btn7;
    private Button btn6;
    private Button btn8;
    private Button btn9;
    private Button btn10;
    private Button btn11;
    FileStorageMemoryCache cache;
    FileStorageMemche cache1;
    HighSynFileStorage hi;
    FilteTest hf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orm);
        final String ss = "{\"bstatus\":{\"code\":0,\"des\":\"成功\"},\"data\":{\"anchor\":0,\"count\":12,\"fromIndex\":20,\"hisEntry\":[{\"businessName\":\"酒店订单\",\"businessType\":\"hotel\"},{\"businessName\":\"接送用车订单\",\"businessType\":\"car_pick\"},{\"businessName\":\"门票/本地玩乐订单\",\"businessType\":\"ticket\"}],\"loadMore\":false,\"shareTip\":\"\",\"timelines\":[{\"anchor\":true,\"date\":\"4月18日 周六\",\"groupId\":\"0\",\"ordercards\":[{\"agentName\":\"杭州车队测试专用\",\"agentPhone\":\"400-777-2014\",\"available\":false,\"buttonOnDispatching\":0,\"carLicense\":\"\",\"carPicUrl\":\"http://img1.qunarzz.com//car/201408/04/c26702038e833497ddb12cfb.jpg\",\"cardType\":4,\"convertPrice\":\"\",\"couponDeductPrice\":0,\"couponDeductPriceStr\":\"\",\"departurePlace\":\"海兴大厦\",\"destination\":\"首都机场T2航站楼\",\"driverName\":\"\",\"driverPhone\":\"\",\"extparams\":\"\",\"ifCanShare\":false,\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"needGuarantee\":false,\"needPay\":false,\"orderActions\":[{\"actId\":11,\"menu\":\"接机\",\"scheme\":\"\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"}],\"orderNo\":\"0100020150418231500466532\",\"orderPrice\":\"\",\"orderStatus\":102,\"orderStatusColor\":16729344,\"orderStatusStr\":\"正在派车\",\"orderTime\":\"2015-04-13 22:23:38\",\"payLeftTimeNotice\":\"\",\"phoneAKSign\":\"\",\"position\":3,\"predicOriginalPrice\":0,\"predicOriginalPriceStr\":\"\",\"predicPrice\":0,\"predicPriceStr\":\"\",\"priceName\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"remindEnd\":\"2015-04-19 00:15\",\"remindNote\":\"\",\"remindPoi\":\"海兴大厦\",\"remindSpace\":\"2015-04-18 23:00\",\"remindStart\":\"2015-04-18 23:15\",\"remindTitle\":\"送机 出租车从海兴大厦去首都机场T2航站楼\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"resourceType\":1,\"resourceTypeName\":\"出租车\",\"returnDate\":\"\",\"returnDateTime\":\"\",\"returnStoreName\":\"\",\"returnTime\":\"\",\"returnWeekDay\":\"\",\"returnYear\":\"\",\"scheduledDate\":\"04月18日\",\"scheduledDateTime\":\"2015-04-18 23:15\",\"scheduledTime\":\"23:15\",\"scheduledWeekDay\":\"周六\",\"serviceType\":1,\"serviceTypeName\":\"送机\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了送机-出租车服务，04月18日 23:15从海兴大厦到首都机场T2航站楼，更多详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了送机服务\",\"shareType\":0,\"sortTime\":1429370100000,\"statusInfo\":\"\",\"statusRemind\":\"\",\"sysCode\":\"7001\",\"takeDate\":\"\",\"takeDateTime\":\"\",\"takeStoreName\":\"\",\"takeTime\":\"\",\"takeWeekDay\":\"\",\"takeYear\":\"\",\"title\":\"送机-出租车\"}],\"today\":false},{\"anchor\":false,\"date\":\"4月24日 周五\",\"groupId\":\"1\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[{\"airCode\":\"MU5127\",\"arrAirport\":\"北京首都国际机场\",\"arrCity\":\"北京\",\"arrCityCode\":\"\",\"arrDate\":\"2015-04-27\",\"arrDateFormat\":\"4月27日 周一\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"\",\"arrText\":\"\",\"arrTime\":\"23:30\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"上海虹桥国际机场\",\"depCity\":\"上海\",\"depDate\":\"2015-04-27\",\"depDateFormat\":\"4月27日 周一\",\"depStatus\":\"\",\"depTerminal\":\"\",\"depText\":\"\",\"depTime\":\"21:00\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"返\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"available\":true,\"canPay\":false,\"canShare\":false,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"host\",\"dptinfo\":[{\"airCode\":\"MU5156\",\"arrAirport\":\"上海虹桥国际机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-04-24\",\"arrDateFormat\":\"4月24日 周五\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"\",\"arrText\":\"\",\"arrTime\":\"20:45\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"北京首都国际机场\",\"depCity\":\"北京\",\"depDate\":\"2015-04-24\",\"depDateFormat\":\"4月24日 周五\",\"depStatus\":\"\",\"depTerminal\":\"\",\"depText\":\"\",\"depTime\":\"18:30\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"去\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"540\\\"}\",\"flightType\":\"2\",\"flightTypeDesc\":\"往返包\",\"host\":\"\",\"ifCanShare\":false,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":20,\"menu\":\"共享记录\",\"scheme\":\"qunaraphone://order/viewRecord?orderNo=bna141212193913731&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"bna141212193913731\",\"orderPrice\":\"540\",\"orderStatus\":5,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票中\",\"orderTime\":\"\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"\",\"remindNote\":\"\",\"remindPoi\":\"\",\"remindSpace\":\"\",\"remindStart\":\"\",\"remindTitle\":\"\",\"remindUrl\":\"\",\"shareCount\":0,\"shareOrder\":true,\"shareText\":\"\",\"shareTip\":\"仅订单乘机人可以凭共享订单办理登机\",\"shareTitle\":\"\",\"shareType\":1,\"sortTime\":1429871400000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5156 / 东方航空 MU5127\",\"vendorName\":\"\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"5月26日 周二\",\"groupId\":\"2\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"cn2.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"MU5183\",\"arrAirport\":\"浦东机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-05-26\",\"arrDateFormat\":\"5月26日 周二\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T1\",\"arrText\":\"\",\"arrTime\":\"09:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-05-26\",\"depDateFormat\":\"5月26日 周二\",\"depStatus\":\"\",\"depTerminal\":\"T2\",\"depText\":\"\",\"depTime\":\"07:25\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"795\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"cn2.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=2&goLongitude=116.603407&goLatitude=40.088211&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E6%B5%A6%E4%B8%9C%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=PVG&desTerminalId=211&desLongitude=121.815352&desLatitude=31.157385&sourceDeliverType=1&sourceOrderId=cnb150121181140577&sourceOrderStartTime=2015-05-26+07%3A25&sourceOrderArrTime=09%3A55&sourceTripNo=MU5183&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cnb150121181140577&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cnb150121181140577\",\"orderPrice\":\"795\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 18:11:40\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-05-26 09:55\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T2\",\"remindSpace\":\"2015-05-26 04:25\",\"remindStart\":\"2015-05-26 07:25\",\"remindTitle\":\"航班MU5183 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了05月26日从北京飞往上海的航班MU5183，预计在07:25从北京首都机场T2航站楼起飞，09:55到达上海浦东机场T1航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1432596300000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5183\",\"vendorName\":\"TTS单程B2Bcn2\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"5月27日 周三\",\"groupId\":\"3\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"cn2.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"MU5158\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-05-27\",\"arrDateFormat\":\"5月27日 周三\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"23:45\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-05-27\",\"depDateFormat\":\"5月27日 周三\",\"depStatus\":\"\",\"depTerminal\":\"T2\",\"depText\":\"\",\"depTime\":\"21:30\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"913\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"cn2.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=2&goLongitude=116.603407&goLatitude=40.088211&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=cnb150121175310406&sourceOrderStartTime=2015-05-27+21%3A30&sourceOrderArrTime=23%3A45&sourceTripNo=MU5158&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cnb150121175310406&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cnb150121175310406\",\"orderPrice\":\"913\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 17:53:10\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-05-27 23:45\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T2\",\"remindSpace\":\"2015-05-27 18:30\",\"remindStart\":\"2015-05-27 21:30\",\"remindTitle\":\"航班MU5158 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了05月27日从北京飞往上海的航班MU5158，预计在21:30从北京首都机场T2航站楼起飞，23:45到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1432733400000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5158\",\"vendorName\":\"TTS单程B2Bcn2\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"6月10日 周三\",\"groupId\":\"4\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":false,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"host\",\"dptinfo\":[{\"airCode\":\"MU5183\",\"arrAirport\":\"上海浦东国际机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-06-10\",\"arrDateFormat\":\"6月10日 周三\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"\",\"arrText\":\"\",\"arrTime\":\"09:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"北京首都国际机场\",\"depCity\":\"北京\",\"depDate\":\"2015-06-10\",\"depDateFormat\":\"6月10日 周三\",\"depStatus\":\"\",\"depTerminal\":\"\",\"depText\":\"\",\"depTime\":\"07:25\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"620\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"\",\"ifCanShare\":false,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=1&goLongitude=116.594511&goLatitude=40.086221&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E6%B5%A6%E4%B8%9C%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=PVG&desTerminalId=211&desLongitude=121.815352&desLatitude=31.157385&sourceDeliverType=1&sourceOrderId=bta150210203904103&sourceOrderStartTime=2015-06-10+07%3A25&sourceOrderArrTime=09%3A55&sourceTripNo=MU5183&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":20,\"menu\":\"共享记录\",\"scheme\":\"qunaraphone://order/viewRecord?orderNo=bta150210203904103&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"bta150210203904103\",\"orderPrice\":\"620\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-06-10 09:55\",\"remindNote\":\"\",\"remindPoi\":\"北京首都国际机场\",\"remindSpace\":\"2015-06-10 04:25\",\"remindStart\":\"2015-06-10 07:25\",\"remindTitle\":\"航班MU5183 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":true,\"shareText\":\"我预订了06月10日从北京飞往上海的航班MU5183，预计在07:25从北京北京首都国际机场起飞，09:55到达上海上海浦东国际机场。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"仅订单乘机人可以凭共享订单办理登机\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":1,\"sortTime\":1433892300000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5183\",\"vendorName\":\"\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"6月13日 周六\",\"groupId\":\"5\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":false,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"host\",\"dptinfo\":[{\"airCode\":\"MU5183\",\"arrAirport\":\"上海浦东国际机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-06-13\",\"arrDateFormat\":\"6月13日 周六\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"\",\"arrText\":\"\",\"arrTime\":\"09:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"北京首都国际机场\",\"depCity\":\"北京\",\"depDate\":\"2015-06-13\",\"depDateFormat\":\"6月13日 周六\",\"depStatus\":\"\",\"depTerminal\":\"\",\"depText\":\"\",\"depTime\":\"07:25\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"620\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"\",\"ifCanShare\":false,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=1&goLongitude=116.594511&goLatitude=40.086221&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E6%B5%A6%E4%B8%9C%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=PVG&desTerminalId=211&desLongitude=121.815352&desLatitude=31.157385&sourceDeliverType=1&sourceOrderId=bta150210205535671&sourceOrderStartTime=2015-06-13+07%3A25&sourceOrderArrTime=09%3A55&sourceTripNo=MU5183&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":20,\"menu\":\"共享记录\",\"scheme\":\"qunaraphone://order/viewRecord?orderNo=bta150210205535671&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"bta150210205535671\",\"orderPrice\":\"620\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-06-13 09:55\",\"remindNote\":\"\",\"remindPoi\":\"北京首都国际机场\",\"remindSpace\":\"2015-06-13 04:25\",\"remindStart\":\"2015-06-13 07:25\",\"remindTitle\":\"航班MU5183 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":true,\"shareText\":\"我预订了06月13日从北京飞往上海的航班MU5183，预计在07:25从北京北京首都国际机场起飞，09:55到达上海上海浦东国际机场。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"仅订单乘机人可以凭共享订单办理登机\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":1,\"sortTime\":1434151500000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5183\",\"vendorName\":\"\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"6月30日 周二\",\"groupId\":\"6\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"cn2.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"MU5183\",\"arrAirport\":\"浦东机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-06-30\",\"arrDateFormat\":\"6月30日 周二\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T1\",\"arrText\":\"\",\"arrTime\":\"09:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-06-30\",\"depDateFormat\":\"6月30日 周二\",\"depStatus\":\"\",\"depTerminal\":\"T2\",\"depText\":\"\",\"depTime\":\"07:25\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"875\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"cn2.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=2&goLongitude=116.603407&goLatitude=40.088211&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E6%B5%A6%E4%B8%9C%E6%9C%BA%E5%9C%BAT1%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=PVG&desTerminalId=211&desLongitude=121.815352&desLatitude=31.157385&sourceDeliverType=1&sourceOrderId=cnb150121124233320&sourceOrderStartTime=2015-06-30+07%3A25&sourceOrderArrTime=09%3A55&sourceTripNo=MU5183&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cnb150121124233320&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cnb150121124233320\",\"orderPrice\":\"875\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 12:42:33\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-06-30 09:55\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T2\",\"remindSpace\":\"2015-06-30 04:25\",\"remindStart\":\"2015-06-30 07:25\",\"remindTitle\":\"航班MU5183 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了06月30日从北京飞往上海的航班MU5183，预计在07:25从北京首都机场T2航站楼起飞，09:55到达上海浦东机场T1航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1435620300000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5183\",\"vendorName\":\"TTS单程B2Bcn2\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"7月03日 周五\",\"groupId\":\"7\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"host\",\"dptinfo\":[{\"airCode\":\"MU5160\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-07-03\",\"arrDateFormat\":\"7月03日 周五\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"23:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-07-03\",\"depDateFormat\":\"7月03日 周五\",\"depStatus\":\"\",\"depTerminal\":\"T2\",\"depText\":\"\",\"depTime\":\"22:00\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"270\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"bn3.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=2&goLongitude=116.603407&goLatitude=40.088211&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=bnc150120142633833&sourceOrderStartTime=2015-07-03+22%3A00&sourceOrderArrTime=23%3A55&sourceTripNo=MU5160&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=bnc150120142633833&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"bnc150120142633833\",\"orderPrice\":\"270\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-20 14:26:33\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-07-03 23:55\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T2\",\"remindSpace\":\"2015-07-03 19:00\",\"remindStart\":\"2015-07-03 22:00\",\"remindTitle\":\"航班MU5160 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了07月03日从北京飞往上海的航班MU5160，预计在22:00从北京首都机场T2航站楼起飞，23:55到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1435932000000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5160\",\"vendorName\":\"TTS单程B2Bbn3\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"7月09日 周四\",\"groupId\":\"8\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"ct1.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"ZH1589\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-07-09\",\"arrDateFormat\":\"7月09日 周四\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"22:40\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-07-09\",\"depDateFormat\":\"7月09日 周四\",\"depStatus\":\"\",\"depTerminal\":\"T3\",\"depText\":\"\",\"depTime\":\"20:30\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"深圳航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"1065\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"ct1.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT3%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=3&goLongitude=116.620524&goLatitude=40.086221&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=cta150121142338640&sourceOrderStartTime=2015-07-09+20%3A30&sourceOrderArrTime=22%3A40&sourceTripNo=ZH1589&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cta150121142338640&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cta150121142338640\",\"orderPrice\":\"1065\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 14:23:38\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-07-09 22:40\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T3\",\"remindSpace\":\"2015-07-09 17:30\",\"remindStart\":\"2015-07-09 20:30\",\"remindTitle\":\"航班ZH1589 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了07月09日从北京飞往上海的航班ZH1589，预计在20:30从北京首都机场T3航站楼起飞，22:40到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1436445000000,\"sysCode\":\"2801\",\"title\":\"深圳航空 ZH1589\",\"vendorName\":\"TTS单程特惠ct1\",\"vendorTel\":\"\"},{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"ct1.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"ZH1589\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-07-09\",\"arrDateFormat\":\"7月09日 周四\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"22:40\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-07-09\",\"depDateFormat\":\"7月09日 周四\",\"depStatus\":\"\",\"depTerminal\":\"T3\",\"depText\":\"\",\"depTime\":\"20:30\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"深圳航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"1065\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"ct1.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT3%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=3&goLongitude=116.620524&goLatitude=40.086221&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=cta150121142338640&sourceOrderStartTime=2015-07-09+20%3A30&sourceOrderArrTime=22%3A40&sourceTripNo=ZH1589&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cta150121142338640&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cta150121142338640\",\"orderPrice\":\"1065\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 14:23:38\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-07-09 22:40\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T3\",\"remindSpace\":\"2015-07-09 17:30\",\"remindStart\":\"2015-07-09 20:30\",\"remindTitle\":\"航班ZH1589 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了07月09日从北京飞往上海的航班ZH1589，预计在20:30从北京首都机场T3航站楼起飞，22:40到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1436445000000,\"sysCode\":\"2801\",\"title\":\"深圳航空 ZH1589\",\"vendorName\":\"TTS单程特惠ct1\",\"vendorTel\":\"\"},{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"ct1.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"ZH1589\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-07-09\",\"arrDateFormat\":\"7月09日 周四\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"22:40\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-07-09\",\"depDateFormat\":\"7月09日 周四\",\"depStatus\":\"\",\"depTerminal\":\"T3\",\"depText\":\"\",\"depTime\":\"20:30\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"深圳航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"1065\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"ct1.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT3%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=3&goLongitude=116.620524&goLatitude=40.086221&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=cta150121142338640&sourceOrderStartTime=2015-07-09+20%3A30&sourceOrderArrTime=22%3A40&sourceTripNo=ZH1589&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cta150121142338640&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cta150121142338640\",\"orderPrice\":\"1065\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 14:23:38\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-07-09 22:40\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T3\",\"remindSpace\":\"2015-07-09 17:30\",\"remindStart\":\"2015-07-09 20:30\",\"remindTitle\":\"航班ZH1589 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了07月09日从北京飞往上海的航班ZH1589，预计在20:30从北京首都机场T3航站楼起飞，22:40到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1436445000000,\"sysCode\":\"2801\",\"title\":\"深圳航空 ZH1589\",\"vendorName\":\"TTS单程特惠ct1\",\"vendorTel\":\"\"}],\"today\":false},{\"anchor\":false,\"date\":\"7月10日 周五\",\"groupId\":\"9\",\"ordercards\":[{\"addFlightInfo\":false,\"arrinfo\":[],\"available\":true,\"canPay\":false,\"canShare\":true,\"cardType\":1,\"cbeginTime\":\"\",\"cflag\":false,\"checkInDes\":\"\",\"china\":true,\"contactName\":\"\",\"convertPrice\":\"\",\"deal\":true,\"domain\":\"cn2.trade.qunar.com\",\"dptinfo\":[{\"airCode\":\"MU5102\",\"arrAirport\":\"虹桥机场\",\"arrCity\":\"上海\",\"arrCityCode\":\"shanghai_city\",\"arrDate\":\"2015-07-10\",\"arrDateFormat\":\"7月10日 周五\",\"arrDateSpace\":\"\",\"arrStatus\":\"\",\"arrTerminal\":\"T2\",\"arrText\":\"\",\"arrTime\":\"09:55\",\"arrTimeStatus\":\"\",\"arrTimeStatusFlag\":-1,\"baggageTurntable\":\"\",\"boardgate\":\"\",\"checkinCounter\":\"\",\"depAirport\":\"首都机场\",\"depCity\":\"北京\",\"depDate\":\"2015-07-10\",\"depDateFormat\":\"7月10日 周五\",\"depStatus\":\"\",\"depTerminal\":\"T2\",\"depText\":\"\",\"depTime\":\"08:00\",\"depTimeStatus\":\"\",\"depTimeStatusFlag\":-1,\"direction\":\"\",\"flightStatus\":\"\",\"flightStatusCode\":0,\"flightStatusColor\":1812922,\"flightWarning\":\"\",\"ptime\":\"\",\"remainAirCode\":\"\",\"remainAirport\":\"\",\"remainCity\":\"\",\"remainTime\":\"\",\"shortCompany\":\"东方航空\"}],\"extparams\":\"{\\\"originalPrice\\\":\\\"913\\\"}\",\"flightType\":\"1\",\"flightTypeDesc\":\"单程\",\"host\":\"cn2.trade.qunar.com\",\"ifCanShare\":true,\"interNotice\":\"\",\"isTeamPriceOrder\":false,\"moneyTypeView\":\"¥\",\"orderActions\":[{\"actId\":9,\"menu\":\"接送机\",\"scheme\":\"qunaraphone://car/createOrder?passengerPhone=13641118475&passengerName=&ak=989f7b3dfc&goCityCode=beijing_city&goCityName=%E5%8C%97%E4%BA%AC&goTerminal=%E9%A6%96%E9%83%BD%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&goAirportCode=PEK&goTerminalId=2&goLongitude=116.603407&goLatitude=40.088211&desCityCode=shanghai_city&desCityName=%E4%B8%8A%E6%B5%B7&desTerminal=%E8%99%B9%E6%A1%A5%E6%9C%BA%E5%9C%BAT2%E8%88%AA%E7%AB%99%E6%A5%BC&desAirportCode=SHA&desTerminalId=6&desLongitude=121.355619&desLatitude=31.200974&sourceDeliverType=1&sourceOrderId=cnb150121172002796&sourceOrderStartTime=2015-07-10+08%3A00&sourceOrderArrTime=09%3A55&sourceTripNo=MU5102&sourceType=0&cat=179&carExtraInfo=1\"},{\"actId\":14,\"menu\":\"提醒\",\"scheme\":\"\"},{\"actId\":19,\"menu\":\"告诉朋友\",\"scheme\":\"qunaraphone://order/manage?orderNo=cnb150121172002796&mobile=13641118475&bType=1&sysCode=2801\"}],\"orderId\":\"\",\"orderNo\":\"cnb150121172002796\",\"orderPrice\":\"913\",\"orderStatus\":2,\"orderStatusColor\":1812922,\"orderStatusStr\":\"出票完成\",\"orderTime\":\"2015-01-21 17:20:02\",\"otaType\":1,\"phoneAKSign\":\"989f7b3dfc\",\"position\":0,\"problemActionsTipContent\":\"\",\"receiptStatus\":0,\"receiptStatusColor\":0,\"refer\":54,\"remindEnd\":\"2015-07-10 09:55\",\"remindNote\":\"\",\"remindPoi\":\"首都机场T2\",\"remindSpace\":\"2015-07-10 05:00\",\"remindStart\":\"2015-07-10 08:00\",\"remindTitle\":\"航班MU5102 北京到上海\",\"remindUrl\":\"http://d.qunar.com/0OjYP3\",\"shareCount\":0,\"shareOrder\":false,\"shareText\":\"我预订了07月10日从北京飞往上海的航班MU5102，预计在08:00从北京首都机场T2航站楼起飞，09:55到达上海虹桥机场T2航站楼。航班详情尽在去哪儿旅行http://touch.qunar.com\",\"shareTip\":\"\",\"shareTitle\":\"我预订了北京到上海的航班\",\"shareType\":0,\"sortTime\":1436486400000,\"sysCode\":\"2801\",\"title\":\"东方航空 MU5102\",\"vendorName\":\"TTS单程B2Bcn2\",\"vendorTel\":\"\"}],\"today\":false}]}}";
        File cacheDir = new File(getCacheDir(), "qq");
        File cacheDir1 = new File(getCacheDir(), "ww");
        File cacheDir2 = new File(getCacheDir(), "wwe");
        File cacheDir3 = new File(getCacheDir(), "www");
        cache = new FileStorageMemoryCache(cacheDir);
        cache1 = new FileStorageMemche(cacheDir1);
        hi = new HighSynFileStorage(cacheDir2);
        hf = new FilteTest(cacheDir3);
        hf.initialize();
//        cache1.initialize();
        btn1 = (Button) this.findViewById(R.id.btn1);
        btn2 = (Button) this.findViewById(R.id.btn2);
        btn3 = (Button) this.findViewById(R.id.btn3);
        btn4 = (Button) this.findViewById(R.id.btn4);
        btn5 = (TextView) this.findViewById(R.id.btn5);
        btn6 = (Button) this.findViewById(R.id.btn6);
        btn7 = (Button) this.findViewById(R.id.btn7);
        btn8 = (Button) this.findViewById(R.id.btn8);
        btn9 = (Button) this.findViewById(R.id.btn9);
        btn10 = (Button) this.findViewById(R.id.btn10);
        btn11 = (Button) this.findViewById(R.id.btn11);
        btn1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                House h = new House("AT", "A楼房");
                Student s = new Student("AT", "A张文同学", 11);
                ArrayList<Student> stus = new ArrayList<Student>();
                stus.add(s);

                Techcher t = new Techcher("AT", 222, h, stus);
                t.sex = "nan";
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                db.insert(t);
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
            }
        });
        btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                Techcher t = db.getWithAll("AT");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
                System.out.println("t = " + t);
                btn5.setText(t.hose.name);
            }
        });
        btn3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                House h = new House("AT", "A楼房");
                Student s = new Student("AT", "A张文同学", 222222222);
                ArrayList<Student> stus = new ArrayList<Student>();
                stus.add(s);

                Techcher t = new Techcher("AT", 444444444, h, stus);
                t.sex = "nan";
                db.update(t);
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
                System.out.println("t = " + t);
            }
        });
        btn4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
                DataBaseProvider<Techcher> db = DataBaseProvider.getDbProvider(Techcher.class);
                db.delete("AT");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
            }
        });
        btn6.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                House h = new House("AT", "A楼房");
                Student s = new Student("AT", "A张文同学", 222222222);
                ArrayList<Student> stus = new ArrayList<Student>();
                stus.add(s);

               final Techcher t = new Techcher("AT", 444444444, h, stus);
                t.sex = "nan";
//                ValidOrderListResult listResult = JSON.parseObject(ss, ValidOrderListResult.class);
                for (int i = 0; i < 10; i++) {
                    new Thread(){
                        @Override
                        public void run() {
                            long time = System.currentTimeMillis();
                            String ss = JSON.toJSONString(t);
                            System.out.println(ss);
                            cache1.putString("json", ss);
                            cache1.putString("string", "string");
                            cache1.putBytes("bytes", new byte[]{1, 2});
                            cache1.putShort("short", (short) 1);
                            cache1.putInt("int", 3);
                            cache1.putLong("long", 4l);

                            cache1.putFloat("float", 0.5f);
                double d1=8.6;
                            cache1.putDouble("double", d1);
                            cache1.putBoolean("boolean", true);
                            cache1.putSerializable("ser",t);
//                            hi.putString("json", ss);
//                            hi.putString("string", "string");
//                            hi.putBytes("bytes", new byte[]{1, 2});
//                            hi.putShort("short", (short) 1);
//                            hi.putInt("int", 3);
//                            hi.putLong("long", 4l);
//
//                            hi.putFloat("float", 0.5f);
//                            double d1=8.6;
//                            hi.putDouble("double", d1);
//                            hi.putBoolean("boolean", true);
//                            hi.putSerializable("ser",t);
                            long done = System.currentTimeMillis();
                            System.out.println(Thread.currentThread()+"  t = "  + (done - time));
                        }
                    }.start();
                }
//                for (int i = 0; i < 10; i++) {
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            long time = System.currentTimeMillis();
//                            String json = hf.getString("json", null);
//                            System.out.println("json = " + JSON.parseObject(json, Techcher.class));
//
//                            System.out.println("string = " +  hf.getString("string", ""));
//                            System.out.println("bytes = " + hf.getBytes("bytes", null)[1]);
//                            System.out.println("short = " + hf.getShort("short", (short) 0));
//                            System.out.println("int = " +  hf.getInt("int", -1));
//                            System.out.println("long = " + hf.getLong("long", -2l));
//                            System.out.println("float = " + hf.getFloat("float", -3f));
//                            System.out.println("double = " +  hf.getDouble("double", 0));
//                            System.out.println("boolean = " + hf.getBoolean("boolean", false));
//                            System.out.println("ser = " + hf.getSerializable("ser", null));
//                            double f = 11;
//                            System.out.println("dd = " + f);
//
//
////                ValidOrderListResult listResult = JSON.parseObject(qq, ValidOrderListResult.class);
//                            long done = System.currentTimeMillis();
//                            System.out.println("t = " + (done - time));
//                        }
//                    }.start();
//                }
//
            }
        });
        btn7.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                House h = new House("AT", "A楼房");
//                Student s = new Student("AT", "A张文同学", 222222222);
//                ArrayList<Student> stus = new ArrayList<Student>();
//                stus.add(s);
//
//                final Techcher t = new Techcher("AT", 444444444, h, stus);
//                t.sex = "nan";
////                ValidOrderListResult listResult = JSON.parseObject(ss, ValidOrderListResult.class);
//                for (int i = 0; i < 10; i++) {
//                    new Thread(){
//                        @Override
//                        public void run() {
//                            long time = System.currentTimeMillis();
//                            String ss = JSON.toJSONString(t);
//
////                            hf.putString("json", ss);
////                            hf.putString("string", "string");
////                            hf.putBytes("bytes", new byte[]{1, 2});
////                            hf.putShort("short", (short) 1);
////                            hf.putInt("int", 3);
////                            hf.putLong("long", 4l);
////
////                            hf.putFloat("float", 0.5f);
////                            double d1=8.6;
////                            hf.putDouble("double", d1);
////                            hf.putBoolean("boolean", true);
////                            hf.putSerializable("ser",t);
//                            hi.putString("json", ss);
//                            hi.putString("string", "string");
//                            hi.putBytes("bytes", new byte[]{1, 2});
//                            hi.putShort("short", (short) 1);
//                            hi.putInt("int", 3);
//                            hi.putLong("long", 4l);
//
//                            hi.putFloat("float", 0.5f);
//                            double d1=8.6;
//                            hi.putDouble("double", d1);
//                            hi.putBoolean("boolean", true);
//                            hi.putSerializable("ser",t);
//                            long done = System.currentTimeMillis();
//                            System.out.println(Thread.currentThread()+"  t = "  + (done - time));
//                        }
//                    }.start();
//                }
//                for (int i = 0; i < 10; i++) {
//                    new Thread(){
//                        @Override
//                        public void run() {
                            long time = System.currentTimeMillis();
                            String json = cache1.getString("json", null);
                            System.out.println("json = " + json);

                            System.out.println("string = " +  cache1.getString("string", ""));
                            System.out.println("bytes = " + cache1.getBytes("bytes", null)[1]);
                            System.out.println("short = " + cache1.getShort("short", (short) 0));
                            System.out.println("int = " +  cache1.getInt("int", -1));
                            System.out.println("long = " + cache1.getLong("long", -2l));
                            System.out.println("float = " + cache1.getFloat("float", -3f));
                            System.out.println("double = " +  cache1.getDouble("double", 0));
                            System.out.println("boolean = " + cache1.getBoolean("boolean", false));
                            System.out.println("ser = " + cache1.getSerializable("ser", null));
                            double f = 11;
                            System.out.println("dd = " + f);


//                ValidOrderListResult listResult = JSON.parseObject(qq, ValidOrderListResult.class);
                            long done = System.currentTimeMillis();
                            System.out.println("t = " + (done - time));
//                        }
//                    }.start();
//                }

//                System.out.println("qq = " + listResult);
            }
        });
        btn8.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                System.out.println("ss = " + ss);
                ValidOrderListResult listResult = JSON.parseObject(ss, ValidOrderListResult.class);
                long time = System.currentTimeMillis();
//                serDiskCache.put("ss", listResult);
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));

            }
        });
        btn9.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
//                Object qq = serDiskCache.get("ss");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
//                System.out.println("qq = " + qq);
            }
        });
        btn10.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                SharedPreferences s;
                long time = System.currentTimeMillis();
//                Object qq = serDiskCache.get("ss");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
//                System.out.println("qq = " + qq);
            }
        });
        btn11.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                long time = System.currentTimeMillis();
//                Object qq = serDiskCache.get("ss");
                long done = System.currentTimeMillis();
                System.out.println("t = " + (done - time));
//                System.out.println("qq = " + qq);
            }
        });
    }

}
