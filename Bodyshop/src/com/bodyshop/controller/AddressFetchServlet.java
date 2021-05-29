package com.bodyshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.bodyshop.pojo.Product;
import com.bodyshop.pojo.ResponsePOJO;
import com.google.gson.Gson;

/**
 * Servlet implementation class AddressFetchServlet
 */
@WebServlet("/AddressFetchServlet")
public class AddressFetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(AddressFetchServlet.class);
	private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressFetchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		

		// TODO Auto-generated method stub
			
		//String firstName = request.getParameter("pronameAlbum3");
		String changeAddressId = request.getParameter("changeAddressId");
		String addNewAddress = request.getParameter("addAddress");
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("mobileNo");
		logger.info("Add adress  "+addNewAddress);
		if(changeAddressId==null&&null==addNewAddress)
		{
		BodyShopDao dao=new BodyShopDao();
		List<AddressDetails> addressDetails=dao.fetchAddressDetails(mobileNo);
		
		if(null!=addressDetails&&addressDetails.size()>0)
		{
		for(AddressDetails addr:addressDetails)
		{
			
		}
		}

		ResponsePOJO responsePojo=new ResponsePOJO();

		responsePojo.setMsg("product ftched success fully");
		
		String jsonResponseString = this.gson.toJson(addressDetails);
		
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(jsonResponseString);
        out.flush();
		}
		else if(null!=addNewAddress&&addNewAddress.equalsIgnoreCase("newAddress")) {
			logger.info("Add address method called");
			
			
			session.setAttribute("Address",null);
		response.sendRedirect("OrderDetails.jsp");
		}
		else
		{
			logger.info("change address id "+changeAddressId);
			BodyShopDao dao=new BodyShopDao();
			AddressDetails addressDetails=dao.fetchAddressDetailsByAddressId(changeAddressId);
			
			session.setAttribute("Address",addressDetails);
			response.sendRedirect("OrderDetails.jsp");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
