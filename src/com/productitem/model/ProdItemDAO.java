package com.productitem.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class ProdItemDAO implements ProdItemDAO_interface{
	private static DataSource ds = null;
	static{
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = "INSERT INTO Prod_Item(itemno, itemqty, itemmemo, ordno, prono, price) VALUES(PROD_ITEM_seq.NEXTVAL, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT itemno, itemqty, itemmemo, ordno, prono, price FROM Prod_Item ORDER BY itemno";
	private static final String GET_ONE_STMT = "SELECT itemno, itemqty, itemmemo, ordno, prono, price FROM Prod_Item WHERE itemno = ?";
	private static final String DELETE = "DELETE FROM Prod_Item WHERE itemno = ?";
	private static final String DELETE_ORDNO = "DELETE FROM Prod_Item WHERE ordno = ?";
	private static final String UPDATE = "UPDATE Prod_Item SET itemqty = ?, itemmemo = ?, ordno = ?, prono = ?, price = ? WHERE itemno = ?";
	
	@Override
	public void insert(ProdItemVO prodItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, prodItemVO.getItemqty());
			pstmt.setString(2, prodItemVO.getItemmemo());
			pstmt.setString(3, prodItemVO.getOrdno());
			pstmt.setInt(4, prodItemVO.getProno());
			pstmt.setInt(5, prodItemVO.getPrice());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured."
					+se.getErrorCode());
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
	public void update(ProdItemVO prodItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, prodItemVO.getItemqty());
			pstmt.setString(2, prodItemVO.getItemmemo());
			pstmt.setString(3, prodItemVO.getOrdno());
			pstmt.setInt(4, prodItemVO.getProno());
			pstmt.setInt(5, prodItemVO.getItemno());
			pstmt.setInt(6, prodItemVO.getPrice());
			pstmt.executeUpdate();
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
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
	public void delete(String ordno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_ORDNO);
			pstmt.setString(4, ordno);
			pstmt.executeUpdate();
		}catch(SQLException se){
			if(con != null){
				try{
					System.err.print("Transaction is being");
					System.err.println("****ROLL BACK BY ProductItem****");
					con.rollback();
				}catch(SQLException excep){
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			
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
	/* (non-Javadoc)
	 * @see com.productitem.model.ProdItemDAO_interface#findByPrimaryKey(java.lang.Integer)
	 */
	@Override
	public ProdItemVO findByPrimaryKey(Integer itemno) {
		ProdItemVO prodItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, itemno);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodItemVO = new ProdItemVO();
				prodItemVO.setItemno(rs.getInt("itemno"));
				prodItemVO.setItemqty(rs.getInt("itemqty"));
				prodItemVO.setItemmemo(rs.getString("itemmemo"));
				prodItemVO.setOrdno(rs.getString("ordno"));
				prodItemVO.setProno(rs.getInt("prono"));
				prodItemVO.setPrice(rs.getInt("price"));
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
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
		
		return prodItemVO;
	}
	@Override
	public List<ProdItemVO> getAll() {
		List<ProdItemVO> list = new ArrayList<ProdItemVO>();
		ProdItemVO prodItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				prodItemVO = new ProdItemVO();
				prodItemVO.setItemno(rs.getInt("itemno"));
				prodItemVO.setItemqty(rs.getInt("itemqty"));
				prodItemVO.setItemmemo(rs.getString("itemmemo"));
				prodItemVO.setOrdno(rs.getString("ordno"));
				prodItemVO.setProno(rs.getInt("prono"));
				prodItemVO.setPrice(rs.getInt("price"));
				list.add(prodItemVO);
			}
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "+se.getMessage());
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
	@Override
	public void insertByOrdNo(ProdItemVO prodItemVO, Connection con) {
		
		PreparedStatement pstmt = null;
		
		try{
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, prodItemVO.getItemqty());
			pstmt.setString(2, prodItemVO.getItemmemo());
			pstmt.setString(3, prodItemVO.getOrdno());
			pstmt.setInt(4, prodItemVO.getProno());
			pstmt.setInt(5, prodItemVO.getPrice());
			pstmt.executeUpdate();
		}catch(SQLException se){
			if(con != null){
				try{
					System.err.print("Transaction is being");
					System.err.println("****ROLL BACK BY ProductItem****");
					con.rollback();
				}catch(SQLException excep){
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
		}
		
	}
		
}
