package com.productitem.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
public class ProdItemDAO_JDBC implements ProdItemDAO_interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl2";
	String userid = "xa803g2";
	String passwd = "xa803g2"; 
	
	private static final String INSERT_STMT = 
			"INSERT INTO Prod_Item(itemNo, itemQty, itemMemo, ordNo, proNo) VALUES(PROD_ITEM_seq.NEXTVAL, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT =
			"SELECT itemNo, itemQty, itemMemo, ordNo, proNo FROM Prod_Item ORDER BY itemNo";
	private static final String GET_ONE_STMT = 
			"SELECT itemNo, itemQty, itemMemo, ordNo, proNo FROM Prod_Item WHERE itemNo = ?";
	private static final String DELETE = 
			"DELETE FROM Prod_Item WHERE itemNo = ?";
	private static final String UPDATE =
			"UPDATE Prod_Item SET itemNo = ?, itemQty = ?, itemMemo = ?, ordNo = ?, proNo = ? FROM Prod_Item WHERE itemNo = ?";
	
	@Override
	public int insert(ProdItemVO prodItemVO) {
		int updateCount = 0; //成功更新數目
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, prodItemVO.getItemQty());
			pstmt.setString(2, prodItemVO.getItemMemo());
			pstmt.setInt(3, prodItemVO.getOrdNo());
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
			
			pstmt.setInt(1, prodItemVO.getItemQty());
			pstmt.setString(2, prodItemVO.getItemMemo());
			pstmt.setInt(3, prodItemVO.getOrdNo());
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
		}catch(ClassNotFoundException e){
			
		}catch(SQLException se){
			
		}finally{}
		return 0;
	}


	@Override
	public ProdItemVO findByPrimaryKey(Integer itemNo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ProdItemVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}


	public static void main(String[] args) {
		

	}

}
