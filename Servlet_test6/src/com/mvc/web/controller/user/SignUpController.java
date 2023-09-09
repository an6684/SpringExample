package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.entity.user.RegisterVO;
import com.mvc.web.service.UserDAO;

@WebServlet("/user/signup")
public class SignUpController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String pid = req.getParameter("id");
		String ppass = req.getParameter("password");
		String pname = req.getParameter("name");
		String pemail = req.getParameter("email");
		
//		System.out.println("parameter :" + pid+ppass+pname+pemail);
		
		RegisterVO rv = new RegisterVO(pid, ppass, pname, pemail);
		
		UserDAO ua = new UserDAO();
		
		//DAO 에서 등록로직 생성
		int result = ua.signUp(rv);
		
		resp.sendRedirect("/user/login");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/board/user/signUp.jsp").forward(req, resp);
	}
}
