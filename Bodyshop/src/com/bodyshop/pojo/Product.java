package com.bodyshop.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table (name = "BODY_SHOP_PRODUCT")
public class Product {
	@Id
	@GeneratedValue
	int id;
	String image;
	int Price;
    String ProductName;
    int quantity;
    
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", image=" + image + ", Price=" + Price + ", ProductName=" + ProductName
				+ ", quantity=" + quantity + "]";
	}

}
