package com.maifeng.fashiongo.base;

import org.json.JSONArray;

/**
 * 商品详情实体类
 * 
 * @author liekkas
 * 
 */
public class GoodDetailData {

	private String goodsName; // 商品名称
	private String goodsInfo; // 商品简介
	private String goodsCode; // 商品编号
	private String originalPrice; // 市场价
	private String price; // 优惠价
	private int isCollect; // 是否已收藏
	private int totalNum; // 总库存
	private int isPackage; // 是否包邮
	private JSONArray goodsImageList = new JSONArray(); // 产品图片(可能9张)

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsInfo() {
		return goodsInfo;
	}

	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(int isCollect) {
		this.isCollect = isCollect;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(int isPackage) {
		this.isPackage = isPackage;
	}

	public JSONArray getGoodsImageList() {
		return goodsImageList;
	}

	public void setGoodsImageList(JSONArray goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

}
