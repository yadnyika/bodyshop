package com.bodyshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.gson.Gson;

/**
 * Servlet implementation class CartFetchServlet
 */
@WebServlet("/CartFetchServlet")
public class CartFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(CartFetchServlet.class);
	private Gson gson = new Gson();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Cookie cookies[]=request.getCookies();
		
		BodyShopDao dao=new BodyShopDao();
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("mobileNo");
		String jsonString=null;
		List<Cart> cookieCartList=null;
		
		
		if(mobileNo!=null)
		{
			 cookieCartList=dao.fetchCartByMobileNo(mobileNo, "Active");
			
		}
		
		
		
		
		String jsonResponseString = this.gson.toJson(cookieCartList);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonResponseString);
        out.flush();
		
				

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
