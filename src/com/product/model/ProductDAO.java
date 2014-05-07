package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;
public class ProductDAO implements ProductDAO_Interface{
	
	private static DataSource ds = null;
	static {
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/TestDB");
		}catch(NamingException e){
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Product(prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, description, relatedproducts, priority, discount, score)"
			+ "VALUES (PRODUCT_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, description, "
			+ "relatedproducts, priority, discount, score FROM Product ORDER BY prono";
	private static final String GET_ONE_STMT = "SELECT prono, productname, category, price, image1, image2, image3,"
			+ "quantity, minimumquantity, status, keyword, description, "
			+ "relatedproducts, priority, discount, score FROM Product WHERE prono = ?";
	private static final String DELETE = "DELETE FROM Product WHERE prono = ?";
	private static final String UPDATE = "UPDATE Product SET productname = ?, category = ?, price = ?, image1 = ?,"
			+ "image2 = ?, image3 = ?, quantity = ?, minimumquantity = ?, status = ?, keyword = ?, description = ?"
			+ ", relatedproducts = ?, priority = ?, discount = ?, score = ? WHERE prono = ?";
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, productVO.getProductname());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setBytes(4, productVO.getImage1());
			pstmt.setBytes(5, productVO.getImage2());
			pstmt.setString(6, productVO.getImage3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumquantity());
			pstmt.setInt(9, productVO.getStatus());
			pstmt.setString(10, productVO.getKeyword());
			pstmt.setString(11, productVO.getDescription());
			pstmt.setString(12, productVO.getRelatedProducts());
			pstmt.setInt(13, productVO.getPriority());
			pstmt.setDouble(14, productVO.getDiscount());
			pstmt.setInt(15, productVO.getScore());
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, productVO.getProductname());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setBytes(4, productVO.getImage1());
			pstmt.setBytes(5, productVO.getImage2());
			pstmt.setString(6, productVO.getImage3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumquantity());
			pstmt.setInt(9, productVO.getStatus());
			pstmt.setString(10, productVO.getKeyword());
			pstmt.setString(11, productVO.getDescription());
			pstmt.setString(12, productVO.getRelatedProducts());
			pstmt.setInt(13, productVO.getPriority());
			pstmt.setDouble(14, productVO.getDiscount());
			pstmt.setInt(15, productVO.getScore());
			pstmt.setInt(16, productVO.getProno());
			
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try{
				con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer prono) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, prono);
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public ProductVO findByPrimaryKey(Integer prono) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, prono);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getInt("prono"));
				productVO.setProductname(rs.getString("productname"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage1(rs.getBytes("image1"));
				productVO.setImage2(rs.getBytes("image2"));
				productVO.setImage3(rs.getString("image3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumquantity(rs.getInt("minimumquantity"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeyword(rs.getString("keyword"));
				productVO.setDescription(rs.getString("description"));
				productVO.setRelatedProducts(rs.getString("relatedproducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScore(rs.getInt("score"));
			}
			
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return productVO;
	}
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProno(rs.getInt("prono"));
				productVO.setProductname(rs.getString("productname"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage1(rs.getBytes("image1"));
				productVO.setImage2(rs.getBytes("image2"));
				productVO.setImage3(rs.getString("image3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumquantity(rs.getInt("minimumquantity"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeyword(rs.getString("keyword"));
				productVO.setDescription(rs.getString("description"));
				productVO.setRelatedProducts(rs.getString("relatedproducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScore(rs.getInt("score"));
				list.add(productVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally{
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
		
}
