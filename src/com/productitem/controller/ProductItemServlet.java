package com.productitem.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productitem.model.*;

/**
 * Servlet implementation class ProductItem
 */
public class ProductItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("itemno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訂單項目編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				Integer itemno = null;
				try {
					itemno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("訂單項目編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ProdItemService proitemSvc = new ProdItemService();
				ProdItemVO proitemVO = proitemSvc.getOnProdItemVO(itemno);
				if (proitemVO == null) {
					errorMsgs.add("查無訂單項目資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("proitemVO", proitemVO); // 資料庫取出的proitemVO物件,存入request
				String url = "/ORDERITEM/listOneProductItem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交listOneProductItem.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/ORDERITEM/listAllProductitem.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				Integer itemno = new Integer(request.getParameter("itemno"));
				
				/***************************2.開始查詢資料****************************************/
				ProdItemService proitemSvc = new ProdItemService();
				ProdItemVO proitemVO = proitemSvc.getOnProdItemVO(itemno);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("proitemVO", proitemVO); // 資料庫取出的empVO物件,存入req
				String url = "/ORDERITEM/update_productitem_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交update_productitem_input.jsp
				successView.forward(request, response);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
		if("insert".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			try{
				Integer itemqty = new Integer(request.getParameter("itemqty"));
				String itemmemo = request.getParameter("itemmemo");
				
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDERITEM/addOrderItem.jsp");
				failureView.forward(request, response);
			}
		}		
	}

}
