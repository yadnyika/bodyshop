package com.bodyshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.AddressDetails;
import com.bodyshop.pojo.Cart;
import com.bodyshop.pojo.Product;
import com.bodyshop.pojo.ResponsePOJO;
import com.google.gson.Gson;

/**
 * Servlet implementation class addproduct
 */
@WebServlet("/addproduct")
public class addproduct extends HttpServlet {
	static Logger logger = Logger.getLogger(addproduct.class);
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//System.out.println("frr " + request.getParameter("cartlength"));
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("mobileNo");
		
		if(mobileNo!=null)
		{
		int cartlength=0;
		if(null!=request.getParameter("cartlength"))
		{
		 cartlength = Integer.parseInt(request.getParameter("cartlength"));
		}
		if(cartlength>0)
		{
		BodyShopDao dao = new BodyShopDao();
		List<Product> productList = new ArrayList<>();
		List<Cart> cartList = new ArrayList<>();
		for (int i = 0; i < cartlength; i++) {
			String prodNameparam = "proname" + i;
			String prodQuantityparam = "proQuant" + i;

			String productName = (String) request.getParameter(prodNameparam);
			String productQuantity = (String) request.getParameter(prodQuantityparam);
			logger.info("prodct Names " + prodNameparam + " :" + request.getParameter(prodNameparam));
			logger.info("prodct Quantity " + prodQuantityparam + " :" + request.getParameter(prodQuantityparam));
			Product p = dao.fetchProductDetails(request.getParameter(prodNameparam));
			p.setQuantity(Integer.parseInt(request.getParameter(prodQuantityparam)));
			productList.add(p);
			Cart cart1 = dao.fetchCartByProductName(mobileNo, p.getProductName());
			
			
			if (null != cart1) {
				cart1.setQuantity(p.getQuantity());
				logger.info("update cart status " + dao.updateCart(cart1));
				
			} /*else {
				Cart cart = new Cart();
				cart.setCartStatus("Active");
				cart.setImage(p.getImage());
				cart.setMobileNo(mobileNo);
				cart.setPrice(p.getPrice());
				cart.setProductName(p.getProductName());
				cart.setQuantity(Integer.parseInt(request.getParameter(prodQuantityparam)));

				System.out.println("insert cart status " + dao.insertCart(cart));
				
			}*/
			
		}
		cartList=dao.fetchCartByMobileNo(mobileNo, "Active");

		List<AddressDetails> addressDetails = dao.fetchAddressDetails(mobileNo);
		AddressDetails address = null;
		if (null != addressDetails && addressDetails.size() > 0) {
			for (AddressDetails addr : addressDetails) {
				if ("Y".equalsIgnoreCase(addr.getIsDefaultAddress())) {
					address = addr;
				}
			}
		}
		
		// String mobileNo=(String)session.getAttribute("UserId");
		session.setAttribute("ProductList", productList);
		session.setAttribute("Address", address);
		session.setAttribute("CartList", cartList);
		ResponsePOJO responsePojo = new ResponsePOJO();
		responsePojo.setProducts(productList);
		responsePojo.setMsg("product ftched success fully");

		String jsonResponseString = this.gson.toJson(productList);

		response.sendRedirect("OrderDetails.jsp");
		}
		else
		{
			response.sendRedirect("welcome.jsp");
		}
	}
		else
		{
			response.sendRedirect("login.jsp");
		}
	}

}
