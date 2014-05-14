package com.product.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.sql.DataSource;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.utilities.ImageUtil;

public class ProductShowImage extends HttpServlet {
	
	Connection con;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("Big5");
		response.setContentType("image/jpg");
		ServletOutputStream out = response.getOutputStream();
		
		Integer prono = new Integer(request.getParameter("prono"));
		Integer image = new Integer(request.getParameter("image"));
		ProductService proSvc = new ProductService();
		ProductVO productVO = proSvc.getOneProduct(prono);

		
		switch (image){
		case 1:
			byte[] image1 = ImageUtil.shrink(productVO.getImage1(), 200);
			out.write(image1);
			break;
		case 2:
			byte[] image2 = ImageUtil.shrink(productVO.getImage2(), 200);
			out.write(image2);
			break;
		case 3:
			byte[] image3 = ImageUtil.shrink(productVO.getImage3(), 200);
			out.write(image3);
			break;
		}

	}
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
			con = ds.getConnection();
		} catch (Exception e) {
			  System.out.println(e.getMessage());
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			  System.out.println(e);
		}
	}

}
