package com.productitem.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.order.model.OrderService;
import com.order.model.OrderVO;
import com.product.model.ProductVO;
import com.productitem.model.*;

/**
 * Servlet implementation class ProductItem
 */
public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if ("ADD".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			List<String> ststusMsgs = new LinkedList<String>();
			request.setAttribute("ststusMsgs", ststusMsgs);
			try {
				Integer prono = new Integer(request.getParameter("prono")
						.trim());
				String productname = new String(request.getParameter(
						"productname").trim());
				Integer price = new Integer(request.getParameter("price")
						.trim());
				Integer itemqty = new Integer(request.getParameter("itemqty")
						.trim());

				ProdItemVO prodItemVO = new ProdItemVO();
				prodItemVO.setItemqty(itemqty);
				prodItemVO.setProno(prono);

				Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
						.getAttribute("shoppingcart");
				boolean match = false;

				if (buylist == null) {
					buylist = new Vector<ProdItemVO>();
					buylist.add(prodItemVO);
				} else {
					for (int i = 0; i < buylist.size(); i++) {
						ProdItemVO prodItemVO2 = buylist.get(i);
						if (prodItemVO2.getProno()
								.equals(prodItemVO.getProno())) {
							prodItemVO2.setItemqty(prodItemVO2.getItemqty()
									+ prodItemVO.getItemqty());
							buylist.setElementAt(prodItemVO2, i);
							match = true;
						}// end if
					}// end for loop
					if (!match) {
						buylist.add(prodItemVO);
					}
				}// end else
				session.setAttribute("shoppingcart", buylist);
				ststusMsgs.add("�s�W���\");
				String url = "/ORDERITEM/item.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(url);
				rd.forward(request, response);
				// ��L�i����~�B�z
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDERITEM/item.jsp");
				failureView.forward(request, response);
				return;
			}
		}

		if ("CHECKOUT".equals(action)) {
			Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
					.getAttribute("shoppingcart");
			Integer total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				ProdItemVO prodItemVO = buylist.get(i);
				Integer price = prodItemVO.getPrice();
				Integer itemqty = new Integer(request.getParameter("itemqty")
						+ i);
				prodItemVO.setItemqty(itemqty);
				buylist.add(prodItemVO);
				total += (price * itemqty);
			}
			String amount = String.valueOf(total);
			session.setAttribute("amount", amount);
			session.setAttribute("shoppingcart", buylist);
			String url = "/ORDERITEM/checkout.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if ("DELETE".equals(action)) {
			Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
					.getAttribute("shoppingcart");

			String del = request.getParameter("del");
			int d = Integer.parseInt(del);
			buylist.removeElementAt(d);

			session.setAttribute("shoppingcart", buylist);
			String url = "/ORDERITEM/item.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(url);
			rd.forward(request, response);
		}
		if ("COMPLETE_ORDERS".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);
			
			List<String> ststusMsgs = new LinkedList<String>();
			request.setAttribute("ststusMsgs", ststusMsgs);
			try {
				String ordaddr = new String(request.getParameter("ordaddr")
						.trim());
				String ordtel = new String(request.getParameter("ordtel")
						.trim());
				Timestamp ordgotime = null;
				Timestamp ordarrtime = null;
				Timestamp orddeltime = null;
				Integer ordstate = 0;
				Integer memno_int = new Integer(request.getParameter("memno"));
				String memno = memno_int.toString();
				Integer empno = null;

				OrderVO orderVO = new OrderVO();
				orderVO.setOrdaddr(ordaddr);
				orderVO.setOrdtel(ordtel);
				orderVO.setOrdgotime(ordgotime);
				orderVO.setOrdarrtime(ordarrtime);
				orderVO.setOrddeltime(orddeltime);
				orderVO.setOrdstate(ordstate);
				orderVO.setMemno(memno);
				orderVO.setEmpno(empno);

				Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session
						.getAttribute("shoppingcart");
				Vector<ProdItemVO> list = new Vector<ProdItemVO>();
				for (int i = 0; i < buylist.size(); i++) {
					ProdItemVO prodItemVO = buylist.get(i);
					prodItemVO.setItemqty(prodItemVO.getItemqty());
					prodItemVO.setItemmemo(prodItemVO.getItemmemo());
					prodItemVO.setProno(prodItemVO.getProno());
					prodItemVO.setPrice(prodItemVO.getPrice());
					list.add(prodItemVO);
				}
				
				if(!errorMsgs.isEmpty()){
					String url = "/ORDERITEM/OrderProcessResult.jsp";
					RequestDispatcher failureView = request.getRequestDispatcher(url);
					failureView.forward(request, response);
					return;
				}
				//�}�l�d�߸�ơA�ھڦۼW�D��
				OrderService ordSvc = new OrderService();
				ordSvc.addOrdersByGenKey(orderVO, list);
				
				if(!errorMsgs.isEmpty()){
					String url = "/ORDERITEM/OrderProcessResult.jsp";
					RequestDispatcher failureView = request.getRequestDispatcher(url);
					failureView.forward(request, response);
					return;
				}
				
				//�d�ߧ����ǳ����
				String url = "/ORDERITEM/OrderProcessResult.jsp";
				ststusMsgs.add("���±z���f�U!�ڭ̤w�g����դU���q��!");
				RequestDispatcher successView = request.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDERITEM/OrderProcessResult.jsp");
				failureView.forward(request, response);				
			}
		}

	}

}
