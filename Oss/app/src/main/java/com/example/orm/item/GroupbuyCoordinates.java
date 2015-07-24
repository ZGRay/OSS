package com.example.orm.item;


public class GroupbuyCoordinates implements BaseResult.BaseData {
    private static final long serialVersionUID = 1L;

    /***
     * 团品分店地址
     */
    public String title;
    public String address;

	/***
	 * 团品分店距离
	 */
	public int distance;

    /***
     * 团品分店经纬度
     */
    public String xy;
    /** 酒店名称 2012十月版本新增 */
    public String hotelName;
    public String name;

    /** 酒店电话 2012十月版本新增 */
    public String hotelTel;
    public String tels;

    /** 当前团品ID 2014七月版本新增 */
    public String itemId;
    /** 分店距离 2014七月版本新增 */
    public String distanceStr;
    /** 当前分店所在城市 2014七月版本新增 */
	public String city;
    /** 地标类型 2014七月版本新增 */
    public String type;
    /** 酒店SEQ 2014七月版本新增 */
    public String hotel_seq;

}
