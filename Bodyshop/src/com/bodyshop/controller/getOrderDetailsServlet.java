package com.bodyshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.ProductDetails;
import com.google.gson.Gson;

/**
 * Servlet implementation class getOrderDetailsServlet
 */
@WebServlet("/getOrderDetailsServlet")
public class getOrderDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrderDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String orderId = request.getParameter("orderId");
		BodyShopDao dao =new BodyShopDao();
		List<ProductDetails> productDetails=dao.fetchProductDetailsByOrderId(orderId);
		String jsonResponseString = this.gson.toJson(productDetails);
		
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
