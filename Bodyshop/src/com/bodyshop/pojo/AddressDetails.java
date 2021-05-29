package com.bodyshop.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BODY_SHOP_ADDRESS")
public class AddressDetails {
	@Id
	@GeneratedValue
	int id;
	String addressId;
	String mobileNo;
	String country;
	String city;
	String state;
	String address;
	String isDefaultAddress;

	public String getOrderMobileNo() {
		return orderMobileNo;
	}

	public void setOrderMobileNo(String orderMobileNo) {
		this.orderMobileNo = orderMobileNo;
	}

	String orderMobileNo;

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getIsDefaultAddress() {
		return isDefaultAddress;
	}

	public void setIsDefaultAddress(String isDefaultAddress) {
		this.isDefaultAddress = isDefaultAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "AddressDetails [id=" + id + ", addressId=" + addressId + ", mobileNo=" + mobileNo + ", country="
				+ country + ", city=" + city + ", state=" + state + ", address=" + address + ", isDefaultAddress="
				+ isDefaultAddress + "]";
	}

}
