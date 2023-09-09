package com.mvc.web.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.User;
import com.mvc.web.service.UserDAO;
@WebServlet("/user/login")
public class LoginController extends HttpServlet{
	private static final Integer cookieExpire = 60*60*24*30; //1 month = 30day
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		String pid = req.getParameter("id");
		String ppass = req.getParameter("pass");
		String remember = req.getParameter("remember");	
		Login lg = new Login(pid, ppass);  //id 와 password 담은 객체 생성
	
		User ur = (UserDAO.getInstance().loginCheck(lg));
		int result = ur.getNumber(); //user 조회 후 결과에 대한 number
		System.out.println("result : " + result);
		
		switch(result) {
		case -1: 
			//login 창으로 다시 보낸다.
			req.setAttribute("ment", "존재하지 않는 ID 입니다.");
			doGet(req, resp);
			break;
		
		case 0:
			//login 창으로 다시 보낸다.
			req.setAttribute("ment", "PASSWORD 가 틀렸습니다.");
			doGet(req, resp);
			break;
		
		case 1:// 로그인 성공
			
			String userId = ur.getId();
			String userNm = ur.getName();
			String userRank = ur.getRank();
			String userEmail = ur.getEmail();
			
			HttpSession hs = req.getSession();
			hs.setAttribute("userID", userId);
			hs.setAttribute("userNm", userNm);
			hs.setAttribute("userRank", userRank);
			hs.setAttribute("userEmail", userEmail);
			
			resp.sendRedirect("/board/content/list");
			
			break;
		}		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/board/user/login.jsp").forward(req, resp);
	}
}
