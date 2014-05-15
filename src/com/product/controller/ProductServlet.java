package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.oreilly.servlet.MultipartRequest;
import com.product.model.ProductService;
import com.product.model.ProductVO;

public class ProductServlet extends HttpServlet {	
	String folderPath;
	public void init() throws ServletException {		
		String folderName = getInitParameter("createPictureFolder").toString();
		folderPath = (getServletContext().getRealPath(folderName))
				.toString();
		File imgDir = new File(folderPath);
		if (!imgDir.exists()) {
			imgDir.mkdirs();
			log("資料夾:" + folderName + "初始化完成!");
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//String action = request.getParameter("action");
		MultipartRequest multi = null;
		String contentType = request.getContentType();
		String action = null;
		/* 檢查表單送進來的contentType是否為multipart/form-data
		 * 如果是，則使用MultipartRequest處裡參數。否則直接使用request.getParameter()
		 * 處理參數。
		 * */
		if(contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(request, folderPath, 10*1024*1024, "Big5");
			action = multi.getParameter("action");
		}
		
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
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			//MultipartRequest multi = new MultipartRequest(request, folderPath, 10*1024*1024, "Big5");
			String requestURL = multi.getParameter("requestURL");
			ProductService proSvc = new ProductService();						
			try{
				Integer prono = new Integer(multi.getParameter("prono"));
				String productname = multi.getParameter("productname").trim();
				String category = multi.getParameter("category").trim();
				Integer price = new Integer(multi.getParameter("price").trim());

				File file1 = multi.getFile("image1");
				File file2 = multi.getFile("image2");
				File file3 = multi.getFile("image3");
				byte[] image1;
				byte[] image2;
				byte[] image3;
				if(file1==null){
					ProductVO productVO = proSvc.getOneProduct(prono);
					image1 = productVO.getImage1();
				}else{
					FileInputStream fis = new FileInputStream(file1);
					int leng = fis.available();
					image1 = new byte[leng];
					fis.read(image1);
					fis.close();
				}
				if(file2==null){
					ProductVO productVO = proSvc.getOneProduct(prono);
					image2 = productVO.getImage2();
				}else{
					FileInputStream fis = new FileInputStream(file2);
					int leng = fis.available();
					image2 = new byte[leng];
					fis.read(image2);
					fis.close();
				}
				if(file3==null){
					ProductVO productVO = proSvc.getOneProduct(prono);
					image3 = productVO.getImage3();
				}else{
					FileInputStream fis = new FileInputStream(file3);
					int leng = fis.available();
					image3 = new byte[leng];
					fis.read(image3);
					fis.close();
				}
				Integer quantity = null;
				try{
					quantity = new Integer(multi.getParameter("quantity").trim());
				}catch(NumberFormatException e){
					quantity = 0;
					errorMsgs.add("商品數量請填寫數字");
				}
				Integer minimumquantity = null;
				try{
					minimumquantity = new Integer(multi.getParameter("minimumquantity").trim());
				}catch(NumberFormatException e){
					minimumquantity = 0;
					errorMsgs.add("商品最低數量請填寫數字");
				}
				
				Integer status = new Integer(multi.getParameter("status"));
				String keyword = multi.getParameter("keyword").trim();
				String description = multi.getParameter("description");
				String relatedProducts = multi.getParameter("relatedProducts").trim();
				Integer priority = new Integer(multi.getParameter("priority"));
				Double discount = null;
				try{
					discount = new Double(multi.getParameter("discount").trim());
				}catch(NumberFormatException e){
					discount = 1.0;
					errorMsgs.add("商品折扣數請填寫數字");
				}
				Integer score = null;
				try{
					score = new Integer(multi.getParameter("score").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("商品評分請填寫數字");
				}				
				String ordno = multi.getParameter("ordno");
				
				ProductVO productVO = new ProductVO();
				productVO.setProno(prono);
				productVO.setProductname(productname);
				productVO.setCategory(category);
				productVO.setPrice(price);
				productVO.setImage1(image1);
				productVO.setImage2(image2);
				productVO.setImage3(image3);
				productVO.setQuantity(quantity);
				productVO.setMinimumquantity(minimumquantity);
				productVO.setStatus(status);
				productVO.setKeyword(keyword);
				productVO.setDescription(description);
				productVO.setRelatedProducts(relatedProducts);
				productVO.setPriority(priority);
				productVO.setDiscount(discount);
				productVO.setScore(score);
				
				if(!errorMsgs.isEmpty()){
					request.setAttribute("productVO", productVO);
					RequestDispatcher failureView = request.getRequestDispatcher("/PRODUCT/update_product_input.jsp");
					failureView.forward(request, response);
					return;
				}
				//開始修改資料
				//ProductService proSvc = new ProductService();
				productVO = proSvc.updateProduct(prono, productname, category, price, image1, image2, image3, quantity, minimumquantity, status, keyword, description, relatedProducts, priority, discount, score);
				
				//修改完成準備轉交
				if(requestURL.equals("/PRODUCT/listAllPorduct.jsp")){
					request.setAttribute("listAllProduct.jsp", proSvc.getOneProduct(prono));
				}
				if(requestURL.equals("/PRODUCT/listPorduct_ByCompositeQuery.jsp")){
					HttpSession session = request.getSession();
					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
					List<ProductVO> list = proSvc.getAll(map);
					request.setAttribute("listPorduct_ByCompositeQuery", list);
				}
				
				String url = requestURL;
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			}catch(Exception e){
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/PRODUCT/update_product_input.jsp");
				failureView.forward(request, response);
			}
		}
		if("insert".equals(action)){ //來自addProduct.jsp
			List<String> errorMsgs = new LinkedList<String>();
			//MultipartRequest multi = new MultipartRequest(request, folderPath, 10*1024*1024, "Big5");
			((ServletContext) multi).setAttribute("errorMsgs", errorMsgs);
			try{
//				Integer prono = new Integer(multi.getParameter("prono"));
				String productname = multi.getParameter("productname");
				String category = multi.getParameter("category");
				Integer price = new Integer (multi.getParameter("price"));
				byte[] image1;
			}catch(Exception e){
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/PRODUCT/addProduct.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
