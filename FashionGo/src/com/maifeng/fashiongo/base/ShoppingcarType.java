package com.maifeng.fashiongo.base;

public class ShoppingcarType {

	private int	id; //���ﳵid            
	private	String	goodsName;//��Ʒ����
	private	int	goodsImage;//��ƷͼƬ 
//	private	String	goodsCode;//��Ʒcode
	private	Double	price;//�Żݼ�
	private	int	number;//����
//private	int	specificationsId;//����id
	private	String	model;//�ͺţ���ɫ�ȵȣ�
	private String	size;//����
	
	//��ʱ����
	public ShoppingcarType(int id, String goodsName, int goodsImage,
			Double price, int number, String model, String size) {
		super();
		this.id = id;
		this.goodsName = goodsName;
		this.goodsImage = goodsImage;
		this.price = price;
		this.number = number;
		this.model = model;
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsImage() {
		return goodsImage;
	}

	public void setGoodsImage(int goodsImage) {
		this.goodsImage = goodsImage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
	
	

	
	
	
	
	
}
