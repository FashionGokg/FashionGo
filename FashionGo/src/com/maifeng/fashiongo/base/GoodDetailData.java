package com.maifeng.fashiongo.base;

import org.json.JSONArray;

public class GoodDetailData {

	private String goodsName; //��Ʒ����
	private String goodsCode; //��Ʒ���
	private String originalPrice; //�г���
	private String price; //�Żݼ�
	private String isCollect; //�Ƿ����ղ�
	private String totalNum; //�ܿ��
	private String isPackage; //�Ƿ����
	private JSONArray goodsImageList = new JSONArray(); //��ƷͼƬ(����9��)

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
