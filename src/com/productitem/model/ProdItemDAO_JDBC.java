package com.productitem.model;
/*
 * Use Table: PROD_ITEM
 **/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
public class ProdItemDAO_JDBC implements ProdItemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2"; 
	
	private static final String INSERT_STMT = 
			"INSERT INTO Prod_Item(itemno, itemqty, itemmemo, ordno, prono) VALUES(PROD_ITEM_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT itemno, itemqty, itemmemo, ordno, prono FROM Prod_Item ORDER BY itemno";
	private static final String GET_ONE_STMT = 
			"SELECT itemno, itemqty, itemmemo, ordno, prono FROM Prod_Item WHERE itemno = ?";
	private static final String DELETE = 
			"DELETE FROM Prod_Item WHERE itemno = ?";
	private static final String UPDATE =
			"UPDATE Prod_Item SET itemno = ?, itemqty = ?, itemmemo = ?, ordno = ?, prono = ? FROM Prod_Item WHERE itemno = ?";
	
	@Override
	public int insert(ProdItemVO prodItemVO) {
		int updateCount = 0; //成功更新數目
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, prodItemVO.getItemqty());
			pstmt.setString(2, prodItemVO.getItemmemo());
			pstmt.setLong(3, prodItemVO.getOrdno());
			pstmt.setInt(4, prodItemVO.getProno());
			
			updateCount = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}	
			}
		}
		return updateCount;
	}


	@Override
	public int update(ProdItemVO prodItemVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, prodItemVO.getItemqty());
			pstmt.setString(2, prodItemVO.getItemmemo());
			pstmt.setLong(3, prodItemVO.getOrdno());
			pstmt.setInt(4, prodItemVO.getProno());
			
			updateCount = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			se.printStackTrace(System.err);
		}finally{
			try{
				if(pstmt != null){
					pstmt.close();
				}
				if(con != null){
					con.close();
				}
			}catch(SQLException se){
				se.printStackTrace(System.err);
			}
		}
		return updateCount;
	}


	@Override
	public int delete(Integer itemno) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itemno);
			updateCount = pstmt.executeUpdate();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally{
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if(con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
		return updateCount;
	}


	@Override
	public ProdItemVO findByPrimaryKey(Integer itemno) {
		ProdItemVO prodItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, itemno);
			rs = pstmt.executeQuery();
			while(rs.next()){
				prodItemVO = new ProdItemVO();
				prodItemVO.setItemno(rs.getInt("itemno"));
				prodItemVO.setItemqty(rs.getInt("itemqty"));
				prodItemVO.setItemmemo(rs.getString("itemmemo"));
				prodItemVO.setOrdno(rs.getLong("ordno"));
				prodItemVO.setProno(rs.getInt("prono"));
			}
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			prodItemVO.setItemno(rs.getInt("itemno"));
			prodItemVO.setItemqty(rs.getInt("itemqty"));
			prodItemVO.setItemmemo(rs.getString("itemmemo"));
			prodItemVO.setOrdno(rs.getLong("ordno"));
			prodItemVO.setProno(rs.getInt("prono"));
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "+e.getMessage());
		}catch(SQLException se){
			se.printStackTrace(System.err);
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(pstmt != null){
				try{
					pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return list;
	}


	public static void main(String[] args) {
		ProdItemDAO_JDBC dao = new ProdItemDAO_JDBC();
		
		//INSERT DATA.
		ProdItemVO prodItemVO_insert = new ProdItemVO();
		prodItemVO_insert.setItemqty(5);
		prodItemVO_insert.setItemmemo("請於下午時段送達");
		prodItemVO_insert.setOrdno(20140411001L);
		prodItemVO_insert.setProno(100103);
		int updateCount_insert = dao.insert(prodItemVO_insert);
		System.out.println("成功插入:"+updateCount_insert+"筆紀錄.");
		
	}

}
