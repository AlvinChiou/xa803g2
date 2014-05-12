package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.oreilly.servlet.MultipartRequest;
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void init()throws ServletException{
		String folderName = getInitParameter("createPictureFolder").toString();
		String folderPath = (getServletContext().getRealPath(folderName)).toString();		
		File imgDir = new File(folderPath);
		
		if(!imgDir.exists()){
			imgDir.mkdirs();
			log("資料夾:"+folderName+"初始化完成!");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if("getOne_For_Display".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				
			}catch(Exception e){
				errorMsgs.add("無法取得資料:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("select_page.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
