package com.bodyshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.Cart;
import com.bodyshop.service.Utility;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(AddToCartServlet.class);
	private Gson gson = new Gson(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		String prodName = request.getParameter("prodName");
		String image = request.getParameter("image");
		String quantity=request.getParameter("quantity");
		String price=request.getParameter("price");
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("mobileNo");
		if(mobileNo==null)
		{
			mobileNo=request.getParameter("mobileNo");
		}
		BodyShopDao dao=new BodyShopDao();
		//session.setAttribute("Address",addressDetails);
		
		boolean inserFlag=true;	
		List<Cart> cartList=dao.fetchCartByMobileNo(mobileNo, "Active");
		for(Cart c:cartList)
		{
			if(c.getProductName().equalsIgnoreCase(prodName))
			{
				c.setQuantity(c.getQuantity()+Integer.parseInt(quantity));
				logger.info("Cart update status "+dao.updateCart(c));
				inserFlag=false;
			}
		}
				if(inserFlag)
				{
					logger.info("inside insert flag true ");
				Cart cart=new Cart();
				cart.setProductName(prodName);
				cart.setImage(image);
				cart.setPrice(Integer.parseInt(price.replace("Rs.", "")));
				cart.setCartStatus("Active");
				cart.setQuantity(Integer.parseInt(quantity));
				cart.setMobileNo(mobileNo);
				logger.info("Cart insert status "+dao.insertCart(cart));
				}
				
				
				
				
			
					
		
		
		
		//Cookie cookie=new Cookie("cookieId",cart );
		
		//logger.info("cart added status"+dao.insertCart(cart));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
