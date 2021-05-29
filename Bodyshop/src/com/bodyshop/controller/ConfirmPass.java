package com.bodyshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.RegisterPOJO;
import com.mysql.cj.Session;

/**
 * Servlet implementation class ConfirmPass
 */
@WebServlet("/ConfirmPass")
public class ConfirmPass extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfirmPass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		String mobileNo=(String)session.getAttribute("UserId");
		String password=request.getParameter("newPass");
		
		BodyShopDao dao=new BodyShopDao();
		RegisterPOJO rpojo=new RegisterPOJO();
		rpojo=dao.fetchUserDetails(mobileNo);
		rpojo.setPassword(password);
		
		dao.update(rpojo);
		
		response.sendRedirect("welcome.jsp");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
