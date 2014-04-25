package com.productitem.model;
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
			"INSERT INTO Prod_Item(item_No, item_Qty, item_Memo, ord_No, proNo) VALUES(PROD_ITEM_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT item_No, item_Qty, item_Memo, ord_No, proNo FROM Prod_Item ORDER BY item_No";
	private static final String GET_ONE_STMT = 
			"SELECT item_No, item_Qty, item_Memo, ord_No, proNo FROM Prod_Item WHERE item_No = ?";
	private static final String DELETE = 
			"DELETE FROM Prod_Item WHERE item_No = ?";
	private static final String UPDATE =
			"UPDATE Prod_Item SET item_No = ?, item_Qty = ?, item_Memo = ?, ord_No = ?, proNo = ? FROM Prod_Item WHERE item_No = ?";
	
	@Override
	public int insert(ProdItemVO prodItemVO) {
		int updateCount = 0; //成功更新數目
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, prodItemVO.getItem_Qty());
			pstmt.setString(2, prodItemVO.getItem_Memo());
			pstmt.setLong(3, prodItemVO.getOrd_No());
			pstmt.setInt(4, prodItemVO.getProNo());
			
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
			
			pstmt.setInt(1, prodItemVO.getItem_Qty());
			pstmt.setString(2, prodItemVO.getItem_Memo());
			pstmt.setLong(3, prodItemVO.getOrd_No());
			pstmt.setInt(4, prodItemVO.getProNo());
			
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
	public int delete(Integer itemNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, itemNo);
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
	public ProdItemVO findByPrimaryKey(Integer itemNo) {
		ProdItemVO prodItemVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, itemNo);
			rs = pstmt.executeQuery();
			while(rs.next()){
				prodItemVO = new ProdItemVO();
				prodItemVO.setItemNo(rs.getInt("item_No"));
				prodItemVO.setItem_Qty(rs.getInt("item_Qty"));
				prodItemVO.setItem_Memo(rs.getString("item_Memo"));
				prodItemVO.setOrd_No(rs.getLong("ord_No"));
				prodItemVO.setProNo(rs.getInt("proNo"));
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
			prodItemVO.setItemNo(rs.getInt("item_No"));
			prodItemVO.setItem_Qty(rs.getInt("item_Qty"));
			prodItemVO.setItem_Memo(rs.getString("item_Memo"));
			prodItemVO.setOrd_No(rs.getLong("ord_No"));
			prodItemVO.setProNo(rs.getInt("proNo"));
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
		prodItemVO_insert.setItem_Qty(5);
		prodItemVO_insert.setItem_Memo("請於下午時段送達");
		prodItemVO_insert.setOrd_No(20140411001L);
		prodItemVO_insert.setProNo(100103);
		int updateCount_insert = dao.insert(prodItemVO_insert);
		System.out.println("成功插入:"+updateCount_insert+"筆紀錄.");
		
	}

}
