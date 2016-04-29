package com.maifeng.fashiongo.base;

import java.util.List;

public class GoodDetailType {
	private String errorcode;
	private String message;
	private List<GoodDetailData> data;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<GoodDetailData> getData() {
		return data;
	}

	public void setData(List<GoodDetailData> data) {
		this.data = data;
	}

}
