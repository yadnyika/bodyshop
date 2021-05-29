package com.bodyshop.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name = "BODY_SHOP_PRODUCT_DETAILS")
public class ProductDetails {
	@Id
	@GeneratedValue
	int id;
	String orderId;
	String mobileNo;
	String productName;
	int Quantity;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	@Override
	public String toString() {
		return "ProductDetails [id=" + id + ", orderId=" + orderId + ", mobileNo=" + mobileNo + ", productName="
				+ productName + ", Quantity=" + Quantity + "]";
	}
	
	
	
}
