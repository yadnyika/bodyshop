package com.bodyshop.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "BODY_SHOP_REGISTER")
public class RegisterPOJO {
	@Id
	@GeneratedValue
private int id;
private String mobileNo;
private String name;
private String email;
private String password;
private String gender;
private String place;
private String securityQuestion;
private String answer;

public String getSecurityQuestion() {
	return securityQuestion;
}
public void setSecurityQuestion(String securityQuestion) {
	this.securityQuestion = securityQuestion;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getPlace() {
	return place;
}
public void setPlace(String place) {
	this.place = place;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getMobileNo() {
	return mobileNo;
}
public void setMobileNo(String mobileNo) {
	this.mobileNo = mobileNo;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
@Override
public String toString() {
	return "RegisterPOJO [id=" + id + ", mobileNo=" + mobileNo + ", name=" + name + ", email=" + email + ", password="
			+ password + ", gender=" + gender + ", place=" + place + ", securityQuestion=" + securityQuestion
			+ ", answer=" + answer + "]";
}

}
