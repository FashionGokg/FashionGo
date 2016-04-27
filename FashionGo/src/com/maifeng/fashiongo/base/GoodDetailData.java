package com.maifeng.fashiongo.base;

import org.json.JSONArray;

public class GoodDetailData {

	private String goodsName; //商品名称
	private String goodsCode; //商品编号
	private String originalPrice; //市场价
	private String price; //优惠价
	private String isCollect; //是否已收藏
	private String totalNum; //总库存
	private String isPackage; //是否包邮
	private JSONArray goodsImageList = new JSONArray(); //产品图片(可能9张)

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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

	public String getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(String isCollect) {
		this.isCollect = isCollect;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}

	public String getIsPackage() {
		return isPackage;
	}

	public void setIsPackage(String isPackage) {
		this.isPackage = isPackage;
	}

	public JSONArray getGoodsImageList() {
		return goodsImageList;
	}

	public void setGoodsImageList(JSONArray goodsImageList) {
		this.goodsImageList = goodsImageList;
	}

}
