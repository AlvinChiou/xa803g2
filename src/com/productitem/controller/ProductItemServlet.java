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
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
				String str = request.getParameter("itemno");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("�п�J�q�涵�ؽs��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//�{�����_
				}
				
				Integer itemno = null;
				try {
					itemno = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("�q�涵�ؽs���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//�{�����_
				}
				
				/***************************2.�}�l�d�߸��*****************************************/
				ProdItemService proitemSvc = new ProdItemService();
				ProdItemVO proitemVO = proitemSvc.getOnProdItemVO(itemno);
				if (proitemVO == null) {
					errorMsgs.add("�d�L�q�涵�ظ��");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/select_page.jsp");
					failureView.forward(request, response);
					return;//�{�����_
				}
				
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
				request.setAttribute("proitemVO", proitemVO); // ��Ʈw���X��proitemVO����,�s�Jrequest
				String url = "/ORDERITEM/listOneProductItem.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // ���\���listOneProductItem.jsp
				successView.forward(request, response);

				/***************************��L�i�઺���~�B�z*************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp ���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = request.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|: �i�ର�i/ORDERITEM/listAllProductitem.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j		
			
			try {
				/***************************1.�����ШD�Ѽ�****************************************/
				Integer itemno = new Integer(request.getParameter("itemno"));
				
				/***************************2.�}�l�d�߸��****************************************/
				ProdItemService proitemSvc = new ProdItemService();
				ProdItemVO proitemVO = proitemSvc.getOnProdItemVO(itemno);
								
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				request.setAttribute("proitemVO", proitemVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/ORDERITEM/update_productitem_input.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // ���\���update_productitem_input.jsp
				successView.forward(request, response);

				/***************************��L�i�઺���~�B�z************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƨ��X�ɥ���:"+e.getMessage());
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
