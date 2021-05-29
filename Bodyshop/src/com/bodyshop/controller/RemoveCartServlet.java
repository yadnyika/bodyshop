package com.bodyshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.Cart;

/**
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/RemoveCartServlet")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(RemoveCartServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String productName = request.getParameter("productName");
		String mobileNo = request.getParameter("mobileNo");
		HttpSession session = request.getSession();
		if(mobileNo==null)
		{
		 mobileNo=(String)session.getAttribute("mobileNo");
		}
		BodyShopDao dao=new BodyShopDao();
		Cart cart=dao.fetchCartByProductName(mobileNo, productName);
		
		logger.info("Remove cart Items status "+dao.removeCart(cart));
		
		List<Cart> cartList=dao.fetchCartByMobileNo(mobileNo, "Active");
		session.setAttribute("CartList", cartList);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
