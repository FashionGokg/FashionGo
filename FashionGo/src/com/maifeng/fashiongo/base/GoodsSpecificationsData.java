package com.maifeng.fashiongo.base;

import java.util.ArrayList;
import java.util.List;

/**
 * ��Ʒ���ʵ����
 * 
 * @author Administrator
 * 
 */

public class GoodsSpecificationsData {

	private String model;
	private List<GoodSizeData> size = new ArrayList<GoodSizeData>();

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public List<GoodSizeData> getSize() {
		return size;
	}

	public void setSize(List<GoodSizeData> size) {
		this.size = size;
	}

}
