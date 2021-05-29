package com.bodyshop.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.RegisterPOJO;
import com.bodyshop.service.RegisterLoginService;
import com.google.gson.Gson;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(LoginServlet.class);
	private Gson gson = new Gson();  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String phone = request.getParameter("mobileNo");
		
		String password=request.getParameter("password");
		String AjaxCall=request.getParameter("Ajax");
		logger.info("inside login servlet"+phone+" password "+password);
		RegisterPOJO pojo=new RegisterPOJO();
		BodyShopDao dao=new BodyShopDao();
		pojo.setMobileNo(phone);
		pojo.setPassword(password);
	
		RegisterLoginService service=new RegisterLoginService();
		
		String message=service.Login(pojo);
		if(AjaxCall==null)
		{
		if(message.equalsIgnoreCase("User Login Successfully"))
		{
			RegisterPOJO pojo2=dao.getLoginDetails(pojo);
			HttpSession session=request.getSession();
			session.setAttribute("mobileNo", phone);
			session.setAttribute("userPojo", pojo2);
			response.sendRedirect("welcome.jsp");
		  
		}
		else
		{
		response.sendRedirect("login.jsp");
		}
		HttpSession session=request.getSession();
		session.setAttribute("loginMessage", message);
		}
		else
		{
		
		HttpSession session=request.getSession();
		session.setAttribute("loginMessage", message);
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(message);
        out.flush();
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
