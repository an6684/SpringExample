package com.mvc.web.controller.content;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.VO.content.contentVO;
import com.mvc.web.service.ContentDAO;

@WebServlet("/board/content/modify")
public class ContentModifyController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		int pid = Integer.parseInt(id);
		ContentDAO cd = new ContentDAO();
		
		contentVO cv = cd.getDetail(pid);
		
		req.setAttribute("detail", cv);
		
		req.getRequestDispatcher("/WEB-INF/board/content/modify.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String paramId = req.getParameter("id");
		System.out.println("param id  : " + paramId);
		int pid = Integer.parseInt(paramId);
		
		contentVO cv = new contentVO();
		cv.setId(pid);
		cv.setTitle(title);
		cv.setContent(content);
		
		ContentDAO cd = new ContentDAO();
		
		int result = cd.updateContent(cv);
		
		resp.sendRedirect("/board/content/list");
	}
}
