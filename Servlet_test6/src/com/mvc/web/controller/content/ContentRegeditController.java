package com.mvc.web.controller.content;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mvc.web.service.ContentDAO;

@MultipartConfig(
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*5,
		maxRequestSize = 1024*1024*5*5
)

@WebServlet("/board/content/regedit")
public class ContentRegeditController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String userNm= req.getSession().getAttribute("userNm").toString();
		
		req.setAttribute("name", userNm);
		req.getRequestDispatcher("/WEB-INF/board/content/regedit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ContentDAO cd = new ContentDAO();
		
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
		String puserNm = req.getSession().getAttribute("userNm").toString();
		String puserId = req.getSession().getAttribute("userID").toString();
		String ptitle = req.getParameter("title");
		String pcontent = req.getParameter("content");
		
		Part filePart=req.getPart("uploadFile");
		String fileName = filePart.getSubmittedFileName();
		
		InputStream ism = filePart.getInputStream(); //파일파트의 inputStream을 읽어서 ism에 참조
		String realPath = req.getServletContext().getRealPath("/upload");
		System.out.println("real path: "+realPath);
		
		String filePath=realPath + File.separator + fileName;
		System.out.println("filePath: "+filePath);
		
		FileOutputStream fos= new FileOutputStream(filePath);
		byte[] buf = new byte[1024];
		int size=0;
		while((size = ism.read(buf))!=-1) {
			fos.write(buf,0,size);
		}
		System.out.println("size: "+ size);
		
		fos.close();
		ism.close();
		
		int result = cd.insertContent(ptitle, pcontent, puserId, fileName);
		
		resp.sendRedirect("list");
		
	}
}
