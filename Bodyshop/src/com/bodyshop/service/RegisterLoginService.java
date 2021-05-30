package com.bodyshop.service;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.RegisterPOJO;

public class RegisterLoginService {
	
	public String Register(RegisterPOJO register)
	{
		BodyShopDao bodyShop=new BodyShopDao();
		boolean registerStaus=false;
		String message="";
		
		registerStaus=bodyShop.register(register);
		if(registerStaus==true)
		{
			message="User Register Successfully";
		}
		else
		{
			message="User failed to Register Successfully";
		}
		return message;
		
	}

	public String Login(RegisterPOJO pojo) {
		BodyShopDao bodyShop=new BodyShopDao();
		String message=null;
		RegisterPOJO rPojo=bodyShop.getLoginDetails(pojo);
		if(rPojo!=null)
		{
			//please dont changed the msg logic is depend on this msg.
			message="User Login Successfully";
		}
		else
		{
			message="Invalid username and password";
		}
		
		return message;
	}
	
	public boolean forgotPassword(RegisterPOJO pojo)
	{

		BodyShopDao bodyShop=new BodyShopDao();
		boolean status=false;
		RegisterPOJO rPojo=bodyShop.forgotPassword(pojo);
		if(rPojo!=null)
		{
			status=true;
		}
		
		return status;
	
	}
	

}
