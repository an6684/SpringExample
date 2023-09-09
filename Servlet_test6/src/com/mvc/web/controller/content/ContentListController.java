package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.VO.content.contentVO;
import com.mvc.web.service.ContentDAO;

@WebServlet("/board/content/list")
public class ContentListController extends HttpServlet {


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userID = req.getSession().getAttribute("userID").toString();
		String userNm = req.getSession().getAttribute("userNm").toString();
		
		
		
		int page =1;
		String page_ = req.getParameter("p");
		String cds_ = req.getParameter("cds");
		String sw_ = req.getParameter("sw");
		String boardid_=req.getParameter("boardid");
		
		String boardid="1";
		String cds = "title";
		String sw ="";
		
		if(boardid_ != null && !boardid_.equals("")){
			boardid = boardid_;
		}
		
		if(page_ != null && !page_.equals("")){
			page = Integer.parseInt(page_);
		}
		
		if(cds_ != null && !cds_.equals("")){
			cds = cds_;
		}  //cds null 이 아니거나, 빈값이 아니면
		
		if(sw_!= null && !sw_.equals("")){
			sw = sw_;
		} //sw null 이 아니거나, 빈값이 아니면
		
		
		System.out.println("boardid : " + boardid);
//		System.out.println("cds : " + cds);
//		System.out.println("sw : " + sw);
		
		
		ContentDAO cd = new ContentDAO();
		
		//아무 입력 값을 주지 않았을 때 조회
		int count = cd.getCount(boardid,cds,sw);
		
		List<contentVO> list = cd.getList(boardid,page,cds,sw);
		
		req.setAttribute("name", userNm);
		req.setAttribute("list", list);
		req.setAttribute("count", count);
		req.getRequestDispatcher("/WEB-INF/board/content/noticeList.jsp").forward(req, resp);
	}
}
