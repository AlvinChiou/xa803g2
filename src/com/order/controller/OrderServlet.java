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
			//�Ӧ�select_page.jsp���ШD
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//1.�����ШD�ѼơA��J�榡���~�B�z
				String str = request.getParameter("ordno");
				if(str == null || (str.trim().length()==0)){
					errorMsgs.add("�п�J�q��s��");
				}
				//�p�G�����~�o�͡A�h�N���~�T���e����
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; //�]�o�Ϳ��~�N���~�T���e����A�ä��_�{��
				}
				
				Integer ordno = null;
				try{
					ordno = new Integer(str);
				}catch(Exception e){
					errorMsgs.add("�q��s���������Ʀr");
				}
				//�p�G�����~�o�͡A�h�N���~�T���e����
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; //�]�o�Ϳ��~�N���~�T���e����A�ä��_�{��
				}
				
				//�}�l�d�߸��
				
				OrderService orderService = new OrderService();
				OrderVO orderVO = orderService.getOneOrder(str); //�ǤJ�q��s���A���A��String
				if(orderVO == null){
					errorMsgs.add("�d�L�q��");
				}
				
				if(!errorMsgs.isEmpty()){
					RequestDispatcher failreView = request.getRequestDispatcher("/ORDER/select_page.jsp");
					failreView.forward(request, response);
					return;
				}
				//�d�ߧ����A�ǳ�����Ƶ�view
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/listOneOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				//�H�U�B�z��L�i����~
			}catch(Exception e){
				errorMsgs.add("�L�k���o���: "+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/select_page.jsp");
				failureView.forward(request, response);
			}
		}
		
		if("getone_For_Update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				/***************************1.�����ШD�Ѽ�****************************************/
				String ordno = new String(request.getParameter("ordno"));
				/***************************2.�}�l�d�߸��****************************************/
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.getOneOrder(ordno);
				/***************************3.�d�ߧ���,�ǳ����(Send the Success view)************/
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/updateOrder.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
				/***************************��L�i�઺���~�B�z**********************************/
			}catch(Exception e){
				errorMsgs.add("�L�k���o�n�ק諸���:"+e.getMessage());
				RequestDispatcher failureView = request.getRequestDispatcher("/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if("update".equals(action)){
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			try{
				//�����ШD�ѼơA��J���~�榡�B��
				Timestamp ordergotime = java.sql.Timestamp.valueOf(request.getParameter("ordergotime").trim());
				Timestamp orderarrtime = java.sql.Timestamp.valueOf(request.getParameter("orderarrtime").trim());
				Timestamp orderdeltime = java.sql.Timestamp.valueOf(request.getParameter("orderdeltime").trim());
				Integer orderstate = new Integer(request.getParameter("orderstate"));
				Integer empno = new Integer(request.getParameter("empno").trim());
				
			}catch(Exception e){
				System.out.println();
			}
		}
	}

}
