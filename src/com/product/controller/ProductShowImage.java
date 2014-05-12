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

public class ProductShowImage extends HttpServlet {
	Connection con;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("Big5");
		response.setContentType("image/jpg");
		ServletOutputStream out = response.getOutputStream();

		try{
			Statement stmt = con.createStatement();
			String prono = request.getParameter("prono");
			String prono2 = new String(prono.getBytes("ISO-8859-1"), "Big5");
			ResultSet rs = stmt.executeQuery("SELECT image1, image2, image3 FROM product WHERE prono = '"+prono+"'");
			
			if(rs.next()){
				int columnIndex = 1;
				int len;
				BufferedInputStream in = null;
				byte[] buff = new byte[4 * 1024];
				
				while((columnIndex++)<=3){
					String imageIndex = "image" + columnIndex;
					in = new BufferedInputStream(rs.getBinaryStream(imageIndex));
					
					while((len = in.read(buff))!=-1){
						out.write(buff, 0, len);
					}
					in.close();
				}
				
			}else{
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			rs.close();
			stmt.close();
		}catch(Exception e){
			System.out.println(e);
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
