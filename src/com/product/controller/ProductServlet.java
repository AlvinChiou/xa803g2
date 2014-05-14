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
		String action = request.getParameter("action");

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
			MultipartRequest multi = new MultipartRequest(request, folderPath, 10*1024*1024, "Big5");
			String requestURL = multi.getParameter("requestURL");
			try{
				
			}catch(Exception e){
				errorMsgs.add("�ק��ƥ���:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/PRODUCT/update_product_input.jsp");
				failureView.forward(request, response);
			}
		}
	}

}
