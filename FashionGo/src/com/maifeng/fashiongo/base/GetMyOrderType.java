package com.maifeng.fashiongo.base;

import java.util.ArrayList;
import java.util.List;

public class GetMyOrderType {
	public String errorcode;
	public String message;
	public List<GetMyOrderDaya> list = new ArrayList<GetMyOrderDaya>();
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
	public List<GetMyOrderDaya> getList() {
		return list;
	}
	public void setList(List<GetMyOrderDaya> list) {
		this.list = list;
	}
	
}
