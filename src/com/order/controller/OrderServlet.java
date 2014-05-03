package com.order.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
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
		
		if("getone_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.接收請求參數****************************************/
				String ordno = new String(request.getParameter("ordno"));
				/***************************2.開始查詢資料****************************************/
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.getOneOrder(ordno);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/updateOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				/***************************其他可能的錯誤處理**********************************/
			}catch(Exception e){
				errorMsgs.add("無法取得要修改的資料:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//接收請求參數，輸入錯誤格式處裡
				String orderno = new String(request.getParameter("orderno").trim());
				Timestamp ordertime = java.sql.Timestamp.valueOf(request.getParameter("ordertime").trim());
				String orderaddress = new String(request.getParameter("orderaddress").trim());
				String ordertel = new String(request.getParameter("ordertel").trim());	
				
				
				
				Integer orderstate = new Integer(request.getParameter("orderstate").trim());
				String memno = new String(request.getParameter("memno").trim());
				Integer empno = new Integer(request.getParameter("empno").trim());
				
				java.sql.Timestamp ordergotime = null;
				try{
					ordergotime = java.sql.Timestamp.valueOf(request.getParameter("ordergotime").trim());
				}catch(IllegalArgumentException e){
					ordergotime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確的出貨日期時間:yyyy-MM-dd hh:mm24:ss");
				}
				
				java.sql.Timestamp orderarrtime = null;
				try{
					orderarrtime = java.sql.Timestamp.valueOf(request.getParameter("orderarrtime").trim());
				}catch(IllegalArgumentException e){
					orderarrtime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確的送貨抵達日期時間:yyyy-MM-dd hh:mm24:ss");
				}
				//Not Finish.
				java.sql.Timestamp orderdeltime = null;
				try{
					orderdeltime = java.sql.Timestamp.valueOf(request.getParameter("orderdeltime").trim());
				}catch(IllegalArgumentException e){
					orderdeltime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確的銷單日期時間:yyyy-MM-dd hh:mm24:ss");
				}
				
				/***************************其他可能的錯誤處理*************************************/
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/updateOrder.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
