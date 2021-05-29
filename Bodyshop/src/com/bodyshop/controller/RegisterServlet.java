package com.bodyshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bodyshop.pojo.RegisterPOJO;
import com.bodyshop.service.RegisterLoginService;
import com.bodyshop.service.Utility;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());	
		String firstName = request.getParameter("first_name");
		String lastName= request.getParameter("last_name");
		String gender = request.getParameter("gender");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String place=request.getParameter("place");
		String password=request.getParameter("password");
		String securityQuestion=request.getParameter("securityQuestion");
		String answer=request.getParameter("answer");
		
		String encryptedQuestion=Utility.encrypt(securityQuestion, "Yagami4321");
		String encryptedAnswer=Utility.encrypt(answer, "Yagami4321");
		RegisterPOJO pojo=new RegisterPOJO();
		pojo.setName(firstName+" "+lastName);
		pojo.setGender(gender);
		pojo.setEmail(email);
		pojo.setMobileNo(phone);
		pojo.setPassword(password);
		pojo.setPlace(place);
		pojo.setSecurityQuestion(encryptedQuestion);
		pojo.setAnswer(encryptedAnswer);
		RegisterLoginService service=new RegisterLoginService();
		
		String message=service.Register(pojo);
		if(message.equalsIgnoreCase("User failed to Register Successfully"))
		{
			response.sendRedirect("register.jsp");
		}
		else
		{
		response.sendRedirect("login.jsp");
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
