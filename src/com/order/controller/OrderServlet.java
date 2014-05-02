package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.model.*;
/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("BIG5");
		String action = request.getParameter("action");
		
		if("getOne_For_Display".equals(action)){
			//來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.接收請求參數，輸入格式錯誤處理
				String str = request.getParameter("ordno");
				if(str == null || (str.trim().length()==0)){
					errorMsgs.add("請輸入訂單編號");
				}
				//如果有錯誤發生，則將錯誤訊息送到表單
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; //因發生錯誤將錯誤訊息送到表單，並中斷程式
				}
				
				Integer ordno = null;
				try{
					ordno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("訂單編號必須為數字");
				}
				//如果有錯誤發生，則將錯誤訊息送到表單
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; //因發生錯誤將錯誤訊息送到表單，並中斷程式
				}
				
				//開始查詢資料
				
				OrderService orderService = new OrderService();
				OrderVO orderVO = orderService.getOneOrder(str); //傳入訂單編號，型態為String
				if(orderVO == null){
					errorMsgs.add("查無訂單");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failreView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failreView.forward(request, response);
					return;
				}
				//查詢完成，準備轉交資料給view
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/listOneOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				//以下處理其他可能錯誤
			}catch(Exception e){
				errorMsgs.add("無法取得資料: "+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
	}

}
