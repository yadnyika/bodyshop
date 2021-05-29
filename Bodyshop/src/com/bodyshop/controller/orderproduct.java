package com.bodyshop.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.bodyshop.pojo.Order;
import com.bodyshop.pojo.Product;
import com.bodyshop.pojo.ProductDetails;
import com.bodyshop.service.Utility;

/**
 * Servlet implementation class orderproduct
 */
@WebServlet("/orderproduct")
public class orderproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(orderproduct.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public orderproduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String country = request.getParameter("Country");
		String state = request.getParameter("State");
		String phone = request.getParameter("Mobile No");
		String city = request.getParameter("City");
		String address = request.getParameter("Address");
		String payment = request.getParameter("Gateway");
		String addressId = request.getParameter("addrId");
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("mobileNo");
		List<Product> productList = (List<Product>) session.getAttribute("ProductList");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
		String strDate = formatter.format(date);
		logger.info("Date Format with dd-M-yyyy hh:mm:ss : " + strDate);
		BodyShopDao dao = new BodyShopDao();
		Order order = new Order();
		AddressDetails addressDetail = null;

		String OrderId = Utility.getOrderId();
		order.setOrderId(OrderId);
		order.setCountry(country);
		order.setState(state);
		order.setMobileNo(phone);
		order.setCity(city);
		order.setAddress(address);
		order.setGateway(payment);
		order.setOrderDate(strDate);
	
		String productDetails = "";
		int quantity = 0;
		int total = 0;
		for (Product product : productList) {
			ProductDetails orderDetails = new ProductDetails();
			productDetails = productDetails + "," + product.getProductName();
			orderDetails.setOrderId(OrderId);
			orderDetails.setProductName(product.getProductName());
			orderDetails.setQuantity(product.getQuantity());
			orderDetails.setMobileNo(phone);
			logger.info("order details insert status " + dao.insertProductDetails(orderDetails));
			total = total + (product.getPrice() * product.getQuantity());
		}
		logger.info("total :" + total);
		total = Math.round(total * 100) / 100;

		productDetails = "you have orderd " + productDetails;
		order.setProductDetails(productDetails);
		order.setTotal(String.valueOf(total));
		Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
		order.setOrderDeliveryTime(tomorrow.toString());
		order.setOrderStatus("Order is in process");
		//Address table is updated to N if its default address is Y
		
		List<AddressDetails> addressDetails = dao.fetchAddressDetails(mobileNo);

		if (null != addressDetails && addressDetails.size() > 0) {
			for (AddressDetails addr : addressDetails) {
				if ("Y".equalsIgnoreCase(addr.getIsDefaultAddress())) {
					addr.setIsDefaultAddress("N");
					dao.insertAddressDetails(addr);
				}
			}
		}
		
		if (null != addressId) {
			//Update address table if addresId is found and set isDefault address to Y 
			//only one entry with isDefault address with Y status is present rest of all are N
			
			addressDetail = dao.fetchAddressDetailsByAddressId(addressId);
			addressDetail.setAddressId(addressId);
			addressDetail.setAddress(address);
			addressDetail.setState(state);
			addressDetail.setCountry(country);
			addressDetail.setCity(city);
			addressDetail.setMobileNo(mobileNo);
			addressDetail.setIsDefaultAddress("Y");
			addressDetail.setOrderMobileNo(phone);
			logger.info("Address detail when address id is not null insert status " + dao.insertAddressDetails(addressDetail));
		} else {
			addressDetail=new AddressDetails();
			addressDetail.setAddressId(Utility.getAddressId());
			addressDetail.setAddress(address);
			addressDetail.setState(state);
			addressDetail.setCountry(country);
			addressDetail.setCity(city);
			addressDetail.setMobileNo(mobileNo);
			addressDetail.setIsDefaultAddress("Y");
			addressDetail.setOrderMobileNo(phone);
			logger.info("Address detail insert status " + dao.insertAddressDetails(addressDetail));

		}
		order.setAddressId(addressDetail.getAddressId());
		logger.info("order insert status " + dao.insertOrder(order));
		HashMap <String,Object>datamap=new HashMap<>();
		//datamap.put("orders", order);
		datamap.put("orders", order);
		datamap.put("orderid", order.getOrderId());
		datamap.put("addressid", addressDetail.getAddressId());
		datamap.put("ProductList", productList);
		List<Cart> cartList=dao.fetchCartByMobileNo(mobileNo, "Active");
		for(Cart c: cartList)
		{
			logger.info("removing cart ites from cart table "+c.getProductName());
			logger.info("cart removed status "+dao.removeCart(c));
		}
		 
		session.setAttribute("orderMap", datamap);
		response.sendRedirect("bill.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
