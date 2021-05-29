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
import com.bodyshop.pojo.RegisterPOJO;
import com.bodyshop.service.RegisterLoginService;
import com.bodyshop.service.Utility;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(ForgotPassword.class);
    public ForgotPassword() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		logger.info("inside forgot password");
		String mobileNo=request.getParameter("phone");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		
		String encryptedQuestion=Utility.encrypt(securityQuestion, "Yagami4321");
		String encryptedAnswer=Utility.encrypt(answer, "Yagami4321");
		RegisterPOJO pojo=new RegisterPOJO();
		pojo.setMobileNo(mobileNo);
		pojo.setSecurityQuestion(encryptedQuestion);
		pojo.setAnswer(encryptedAnswer);
		RegisterLoginService service=new RegisterLoginService();
		
		boolean forgotpassstatus=false;
		forgotpassstatus=service.forgotPassword(pojo);
		logger.info("inside forgot password "+forgotpassstatus);
		if(forgotpassstatus!=false)
		{
			HttpSession session = request.getSession();
			session.setAttribute("UserId",mobileNo);
			response.sendRedirect("ConfirmPass.jsp");
		}
		else
		{
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
