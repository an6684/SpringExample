package com.mvc.web.controller.content;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.web.VO.content.CommentVO;
import com.mvc.web.VO.content.contentVO;
import com.mvc.web.service.ContentDAO;
@WebServlet("/board/content/detail")
public class ContentDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String paramId = req.getParameter("id");    //글 id
        
        String userID = (String) req.getSession().getAttribute("userID");
        String userRank = (String) req.getSession().getAttribute("userRank");
        
        int pid = 0;
        
        if(paramId != null && !paramId.equals("")){
            pid = Integer.parseInt(paramId);
        }  
        
        ContentDAO cd = new ContentDAO();
        
        contentVO cv = cd.getDetail(pid);
        
        if(cv != null) {
            if("1".equals(userRank) || cv.getWriteid().equals(userID)) {
                req.setAttribute("right", "1");
            } else {
                req.setAttribute("right", "2");
            }
        }
        
        List<CommentVO> cmList = cd.getCommentList(pid);//댓글 목록 리스트 저장
        
        req.setAttribute("detail", cv);
        req.setAttribute("cmList", cmList);
        req.getRequestDispatcher("/WEB-INF/board/content/Detail.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
    	String userid=req.getSession().getAttribute("userID").toString();
    	String comment = req.getParameter("comment");
    	String contentid_=req.getParameter("contentid");
    	
    	int contentid = Integer.parseInt(contentid_);
    	
    	System.out.println("values : "+userid+comment+contentid);
    	
    	ContentDAO cd = new ContentDAO();
    	
    	CommentVO cm = new CommentVO(contentid, 0, userid, null, comment);
    
    	int result = cd.insertContent(cm);
    	resp.sendRedirect("detail?id="+contentid_);
    	
    }
}

