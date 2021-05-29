package com.bodyshop.pojo;

import java.util.List;

public class ResponsePOJO {

	List<Product> products;
	String Msg;
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
}
