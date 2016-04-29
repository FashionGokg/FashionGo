package com.maifeng.fashiongo.base;

import org.json.JSONArray;

/**
 * ��Ʒ����ʵ����
 * 
 * @author liekkas
 * 
 */
public class GoodDetailData {

	private String goodsName; // ��Ʒ����
	private String goodsInfo; // ��Ʒ���
	private String goodsCode; // ��Ʒ���
	private String originalPrice; // �г���
	private String price; // �Żݼ�
	private int isCollect; // �Ƿ����ղ�
	private int totalNum; // �ܿ��
	private int isPackage; // �Ƿ����
	private JSONArray goodsImageList = new JSONArray(); // ��ƷͼƬ(����9��)

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
