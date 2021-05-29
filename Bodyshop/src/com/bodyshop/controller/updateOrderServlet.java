package com.bodyshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.Order;

/**
 * Servlet implementation class updateOrderServlet
 */
@WebServlet("/updateOrderServlet")
public class updateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(updateOrderServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String orderId = request.getParameter("orderId");
		String OrderStatus = request.getParameter("OrderStatus");
		String orderDate = request.getParameter("orderDate");
		String orderDeliveryTime = request.getParameter("orderDeliveryTime");
		String orderAddresss = request.getParameter("orderAddresss");
		
		BodyShopDao dao=new BodyShopDao(); 
		Order order=dao.fetchOrdersDetailsByOrderId(orderId);
		order.setAddress(orderAddresss);
		order.setOrderStatus(OrderStatus);
		order.setOrderDate(orderDate);
		order.setOrderDeliveryTime(orderDeliveryTime);
		
		
		logger.info("update table update status"+ dao.updateOrder(order)) ;
	
		response.sendRedirect("productDelivery.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
