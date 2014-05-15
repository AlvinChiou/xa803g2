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
			log("��Ƨ�:" + folderName + "��l�Ƨ���!");
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
		/* �ˬd���e�i�Ӫ�contentType�O�_��multipart/form-data
		 * �p�G�O�A�h�ϥ�MultipartRequest�B�̰ѼơC�_�h�����ϥ�request.getParameter()
		 * �B�z�ѼơC
		 * */
		if(contentType.startsWith("multipart/form-data")){
			multi = new MultipartRequest(request, folderPath, 10*1024*1024, "Big5");
			action = multi.getParameter("action");
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// �����ШD�Ѽ�
				String str = request.getParameter("prono");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J���~�s��");
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
					errorMsgs.add("���~�s���榡�����T");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				//�}�l�d�߰ӫ~���
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				if(productVO == null){
					errorMsgs.add("�d�L���");
				}
				//�N���~�T����椩select_page.jsp
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;
				}
				
				//�d�ߧ����A�N���G��椩listOneProduct.jsp
				request.setAttribute("productVO", productVO);//�]�d�ߵ��G�]�JproductVO����A�۸�ƮwproductVO����s�Jrequest
				String url = "listOneProduct.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				
				//�H�U�B�̨�L�i����~
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		//�Ӧ�listAllProduct.jsp ��  /PRODUCTITEM/listAllProductItem.jsp���ШD
		if("getOne_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL");
			
			try{
				//�����ШD�Ѽ�
				Integer prono = new Integer(request.getParameter("prono"));
				
				//�}�l�d�߸��
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneProduct(prono);
				
				//�d�ߧ����A���d�ߵ��G
				request.setAttribute("productVO", productVO);
				String url = "/PRODUCT/update_product_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				//��L�i�઺���~�B��
			}catch(Exception e){
				errorMsgs.add("�ק��ƨ��X�ɥ���"+e.getMessage());
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
					errorMsgs.add("�ӫ~�ƶq�ж�g�Ʀr");
				}
				Integer minimumquantity = null;
				try{
					minimumquantity = new Integer(multi.getParameter("minimumquantity").trim());
				}catch(NumberFormatException e){
					minimumquantity = 0;
					errorMsgs.add("�ӫ~�̧C�ƶq�ж�g�Ʀr");
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
					errorMsgs.add("�ӫ~�馩�ƽж�g�Ʀr");
				}
				Integer score = null;
				try{
					score = new Integer(multi.getParameter("score").trim());
				}catch(NumberFormatException e){
					errorMsgs.add("�ӫ~�����ж�g�Ʀr");
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
				//�}�l�ק���
				//ProductService proSvc = new ProductService();
				productVO = proSvc.updateProduct(prono, productname, category, price, image1, image2, image3, quantity, minimumquantity, status, keyword, description, relatedProducts, priority, discount, score);
				
				//�ק粒���ǳ����
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
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/PRODUCT/update_product_input.jsp");
				failureView.forward(request, response);
			}
		}
		if("insert".equals(action)){ //�Ӧ�addProduct.jsp
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
