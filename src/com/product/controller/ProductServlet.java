package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServlet extends HttpServlet {
//	String folderName = getInitParameter("createPictureFolder").toString();
//	String folderPath = (getServletContext().getRealPath(folderName))
//			.toString();
//	public void init() throws ServletException {		
//		File imgDir = new File(folderPath);
//		if (!imgDir.exists()) {
//			imgDir.mkdirs();
//			log("資料夾:" + folderName + "初始化完成!");
//		}
//	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求參數
				String str = request.getParameter("prono");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入產品編號");
				}
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				
				Integer prono = null;
				try{
					prono = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("產品編號格式不正確");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				//開始查詢商品資料
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				if(productVO == null){
					errorMsgs.add("查無資料");
				}
				//將錯誤訊息轉交予select_page.jsp
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				
				//查詢完成，將結果轉交予listOneProduct.jsp
				request.setAttribute("productVO", productVO);//因查詢結果包入productVO物件，自資料庫productVO物件存入request
				String url = "listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				//以下處裡其他可能錯誤
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		//來自listAllProduct.jsp 或  /PRODUCTITEM/listAllProductItem.jsp的請求
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try{
				//接收請求參數
				Integer prono = new Integer(request.getParameter("prono"));
				
				//開始查詢資料
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				
				//查詢完畢，轉交查詢結果
				request.setAttribute("productVO", productVO);
				String url = "/PRODUCT/update_product_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				//其他可能的錯誤處裡
			}catch(Exception e){
				errorMsgs.add("修改資料取出時失敗"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher(requestURL);
				failureView.forward(request, response);
			}
		}
	}

}
