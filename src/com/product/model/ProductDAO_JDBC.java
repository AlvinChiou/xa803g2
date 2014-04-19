package com.product.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

import javax.activation.DataSource;
import javax.naming.Context;

import org.apache.catalina.tribes.transport.DataSender;
import org.apache.jasper.tagplugins.jstl.core.Catch;
public class ProductDAO_JDBC implements ProductDAO_Interface{
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2";
	public static DataSource ds = null;
	private static final String INSERT_STMT = "INSERT INTO Product(proNo, productName, category, price, image_1, image_2, image_3,"
			+ "quantity, minimumQuantity, dateAvailable, status, keywords, description, relatedProducts, priority, discount, score)"
			+ "VALUES (PRODUCT_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT proNo, productName, category, price, image_1, image_2, image_3,"
			+ "quantity, minimumQuantity, to_char(dateAvailable, 'yyyy-mm-dd')dateAvailable, status, keywords, description, "
			+ "relatedProducts, priority, discount, score FROM Product ORDER BY proNo";
	private static final String GET_ONE_STMT = "SELECT proNo, productName, category, price, image_1, image_2, image_3,"
			+ "quantity, minimumQuantity, to_char(dateAvailable, 'yyyy-mm-dd')dateAvailable, status, keywords, description, "
			+ "relatedProducts, priority, discount, score FROM Product WHERE proNo = ?";
	private static final String DELETE = "DELETE FROM Product WHERE proNo = ?";
	private static final String UPDATE = "UPDATE Product SET proNo = ?, productName = ?, category = ?, price = ?, image_1 = ?,"
			+ "image_2 = ?, image_3 = ?, quantity = ?, minimumQuantity = ?, dateAvailable = ?, status = ?, keywords = ?, description = ?"
			+ ", relatedProducts = ?, priority = ?, discount = ?, score = ? WHERE proNo = ?";
	@Override
	public int insert(ProductVO productVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = {"PRONO"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			
			pstmt.setString(1, productVO.getProductName());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setByte(4, productVO.getImage_1());
			pstmt.setByte(5, productVO.getImage_2());
			pstmt.setByte(6, productVO.getImage_3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumQuantity());
			pstmt.setDate(9, productVO.getDateAvailable());
			pstmt.setInt(10, productVO.getStatus());
			pstmt.setString(11, productVO.getKeywords());
			pstmt.setString(12, productVO.getDescription());
			pstmt.setString(13, productVO.getRelatedProducts());
			pstmt.setInt(14, productVO.getPriority());
			pstmt.setDouble(15, productVO.getDiscount());
			pstmt.setInt(16, productVO.getScorel());
			
			updateCount = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if(rs.next()){
				do{
					for(int i = 1;i<=columnCount;i++){
						String key = rs.getString(i);
						System.out.println("自增主鍵值(" + i + ") = " + key +"(新增成功的商品編號)");
					}
				}while(rs.next());
			}else{
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();
		}catch(ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch(SQLException se){
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		}finally{
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace(System.err);
			}
		}
		return updateCount;
	}

	@Override	
	public int update(ProductVO productVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, productVO.getProductName());
			pstmt.setString(2, productVO.getCategory());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setByte(4, productVO.getImage_1());
			pstmt.setByte(5, productVO.getImage_2());
			pstmt.setByte(6, productVO.getImage_3());
			pstmt.setInt(7, productVO.getQuantity());
			pstmt.setInt(8, productVO.getMinimumQuantity());
			pstmt.setDate(9, productVO.getDateAvailable());
			pstmt.setInt(10, productVO.getStatus());
			pstmt.setString(11, productVO.getKeywords());
			pstmt.setString(12, productVO.getDescription());
			pstmt.setString(13, productVO.getRelatedProducts());
			pstmt.setInt(14, productVO.getPriority());
			pstmt.setDouble(15, productVO.getDiscount());
			pstmt.setInt(16, productVO.getScorel());

			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return updateCount;
	}

	@Override
	public int delete(Integer proNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, proNo);
			
			updateCount = pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		return updateCount;
	}

	@Override
	public ProductVO findByPrimaryKey(Integer proNo) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, proNo);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProNo(rs.getInt("proNo"));
				productVO.setProductName(rs.getString("productName"));
				productVO.setCategory(rs.getString("category"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setImage_1(rs.getByte("image_1"));
				productVO.setImage_2(rs.getByte("image_2"));
				productVO.setImage_3(rs.getByte("image_3"));
				productVO.setQuantity(rs.getInt("quantity"));
				productVO.setMinimumQuantity(rs.getInt("minimumQuantity"));
				productVO.setDateAvailable(rs.getDate("dateAvailable"));
				productVO.setStatus(rs.getInt("status"));
				productVO.setKeywords(rs.getString("keywords"));
				productVO.setDescription(rs.getString("description"));
				productVO.setRelatedProducts(rs.getString("relatedProducts"));
				productVO.setPriority(rs.getInt("priority"));
				productVO.setDiscount(rs.getDouble("discount"));
				productVO.setScorel(rs.getInt("score"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
		// TODO Auto-generated method stub
		return null;
	}

}
