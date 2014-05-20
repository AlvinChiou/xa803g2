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
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");

		if ("getOne_For_Display".equals(action)) {
			// 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 1.接收請求參數，輸入格式錯誤處理
				String str = request.getParameter("ordno");
				if (str == null || (str.trim().length() == 0)) {
					errorMsgs.add("請輸入訂單編號");
				}
				// 如果有錯誤發生，則將錯誤訊息送到表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; // 因發生錯誤將錯誤訊息送到表單，並中斷程式
				}

				Long ordno = null;
				try {
					ordno = new Long(str);
				} catch (Exception e) {
					errorMsgs.add("訂單編號必須為數字");
				}
				// 如果有錯誤發生，則將錯誤訊息送到表單
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/ORDER/select_page.jsp");
					failureView.forward(request, response);
					return; // 因發生錯誤將錯誤訊息送到表單，並中斷程式
				}

				// 開始查詢資料

				OrderService orderService = new OrderService();
				OrderVO orderVO = orderService.getOneOrder(str); // 傳入訂單編號，型態為String
				if (orderVO == null) {
					errorMsgs.add("查無訂單");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failreView = request
							.getRequestDispatcher("/ORDER/select_page.jsp");
					failreView.forward(request, response);
					return;
				}
				// 查詢完成，準備轉交資料給view
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/listOneOrder.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				// 以下處理其他可能錯誤
			} catch (Exception e) {
				errorMsgs.add("無法取得資料: " + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDER/select_page.jsp");
				failureView.forward(request, response);
			}
		}

		if ("getone_For_Update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				String ordno = new String(request.getParameter("ordno"));
				/*************************** 2.開始查詢資料 ****************************************/
				OrderService ordSvc = new OrderService();
				OrderVO orderVO = ordSvc.getOneOrder(ordno);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				request.setAttribute("orderVO", orderVO);
				String url = "/ORDER/updateOrder.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求參數，輸入錯誤格式處裡
				String ordno = new String(request.getParameter("ordno").trim());
				Timestamp ordtime = java.sql.Timestamp.valueOf(request
						.getParameter("ordtime").trim());
				String ordaddr = new String(request.getParameter("ordaddr")
						.trim());
				String ordtel = new String(request.getParameter("ordtel")
						.trim());
				Integer ordstate = new Integer(request.getParameter("ordstate")
						.trim());
				String memno = new String(request.getParameter("memno").trim());
				Integer empno = new Integer(request.getParameter("empno")
						.trim());

				// 判斷發貨時間格式有無錯誤
				java.sql.Timestamp ordgotime = null;
				try {
					ordgotime = java.sql.Timestamp.valueOf(request
							.getParameter("ordgotime").trim());
				} catch (IllegalArgumentException e) {
					// ordgotime = new
					// java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入正確的發貨日期時間:yyyy-MM-dd hh:mm24:ss");
				}

				// 判斷到貨時間是否為空，如果不是則進行格式判斷處理
				java.sql.Timestamp ordarrtime = null;
				if (!(request.getParameter("ordarrtime").trim().isEmpty())) {
					try {
						ordarrtime = java.sql.Timestamp.valueOf(request
								.getParameter("ordarrtime").trim());
					} catch (IllegalArgumentException e) {
						// ordarrtime = new java.sql.Timestamp(
						// System.currentTimeMillis());
						errorMsgs.add("請輸入正確的到貨日期時間:yyyy-MM-dd hh:mm24:ss");
					}
				} 
				// 判斷銷單時間是否為空，如果不是則進行格式判斷處理
				java.sql.Timestamp orddeltime = null;
				if (!(request.getParameter("orddeltime").trim().isEmpty())) {
					try {
						orddeltime = java.sql.Timestamp.valueOf(request
								.getParameter("orddeltime").trim());
					} catch (IllegalArgumentException e) {
						// orddeltime = new java.sql.Timestamp(
						// System.currentTimeMillis());
						errorMsgs.add("請輸入正確的銷單日期時間:yyyy-MM-dd hh:mm24:ss");
					}
				} else {
					orddeltime = null;
				}
				OrderVO orderVO = new OrderVO();
				orderVO.setOrdno(ordno);
				orderVO.setOrdtime(ordtime);
				orderVO.setOrdaddr(ordaddr);
				orderVO.setOrdtel(ordtel);
				orderVO.setOrdgotime(ordgotime);
				orderVO.setOrdarrtime(ordarrtime);
				orderVO.setOrddeltime(orddeltime);
				orderVO.setOrdstate(ordstate);
				orderVO.setMemno(memno);
				orderVO.setEmpno(empno);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("orderVO", orderVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/ORDER/updateOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				// 開始修改資料
				OrderService ordSvc = new OrderService();
				orderVO = ordSvc.updateOrder(ordno, ordtime, ordaddr, ordtel,
						ordgotime, ordarrtime, orddeltime, ordstate, memno,
						empno);

				// 修改完成轉交(Send the Success view)
				request.setAttribute("orderVO", orderVO);// 資料庫update成功後,正確的的empVO物件,存入request
				String url = "/ORDER/listOneOrder.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);

				/*************************** 其他可能的錯誤處理 *************************************/

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDER/updateOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				String ordaddr = request.getParameter("ordaddr").trim();
				String ordtel = request.getParameter("ordtel").trim();
				Integer ordstate = new Integer(request.getParameter("ordstate"));
				// String memno = request.getParameter("memno").trim();
				// Integer empno = new
				// Integer(request.getParameter("empno").trim());
				Timestamp ordgotime = null;
				Timestamp ordarrtime = null;
				Timestamp orddeltime = null;
				Integer memno_int = null;
				String memno = null;
				try {
					memno_int = new Integer(request.getParameter("memno")
							.trim());
					memno = memno_int.toString();
				} catch (NumberFormatException e) {
					errorMsgs.add("會員編號須為數字");
				}

				Integer empno = null;
				try {
					empno = new Integer(request.getParameter("empno").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("員工編號須為數字");
				}

				OrderVO orderVO = new OrderVO();
				orderVO.setOrdaddr(ordaddr);
				orderVO.setOrdtel(ordtel);
				orderVO.setOrdstate(ordstate);
				orderVO.setMemno(memno);
				orderVO.setEmpno(empno);

				if (!errorMsgs.isEmpty()) {
					request.setAttribute("OrderVO", orderVO);
					RequestDispatcher failureView = request
							.getRequestDispatcher("/ORDER/addOrder.jsp");
					failureView.forward(request, response);
					return;
				}
				// 開始新增資料
				OrderService ordSvc = new OrderService();
				orderVO = ordSvc.addOrder(ordaddr, ordtel, ordgotime,
						ordarrtime, orddeltime, ordstate, memno, empno);
				// 新增完畢轉交(Send the Success view)
				String url = "/ORDER/listAllOrder.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDER/addOrder.jsp");
				failureView.forward(request, response);
			}
		}
		if ("delete".equals(action)) { // 來自listAllOrder.jsp之參數
			List<String> errorMsgs = new LinkedList<String>();
			request.setAttribute("errorMsgs", errorMsgs);

			try {
				// 接收請求參數
				String ordno = new String(request.getParameter("ordno"));

				// 開始刪除資料
				OrderService ordSvc = new OrderService();
				ordSvc.deleteOrder(ordno);

				// 刪除完畢轉交成功畫面
				String url = "/ORDER/listAllOrder.jsp";
				RequestDispatcher successView = request
						.getRequestDispatcher(url);
				successView.forward(request, response);

			} catch (Exception e) {
				errorMsgs.add("刪除訂單失敗" + e.getMessage());
				RequestDispatcher failureView = request
						.getRequestDispatcher("/ORDER/listAllOrder.jsp");
				failureView.forward(request, response);

			}
		}
	}
}
