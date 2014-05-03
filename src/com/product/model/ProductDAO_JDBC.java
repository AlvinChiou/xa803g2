package com.product.model;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.activation.DataSource;
import javax.management.RuntimeErrorException;
import javax.naming.Context;

import org.apache.catalina.tribes.transport.DataSender;
import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.sun.xml.internal.ws.wsdl.writer.document.Port;
/*
 * Use Table: PRODUCT
 **/
public class ProductDAO_JDBC implements ProductDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2";
	public static DataSource ds = null;
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
	private static final String UPDATE = "UPDATE Product SET prono = ?, productname = ?, category = ?, price = ?, image1 = ?,"
			+ "image2 = ?, image3 = ?, quantity = ?, minimumquantity = ?, status = ?, keyword = ?, description = ?"
			+ ", relatedproducts = ?, priority = ?, discount = ?, score = ? WHERE prono = ?";

	@Override
	public int insert(ProductVO productVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String cols[] = { "PRONO" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);

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

			updateCount = pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			if (rs.next()) {
				do {
					for (int i = 1; i <= columnCount; i++) {
						String key = rs.getString(i);
						System.out.println("自增主鍵值(" + i + ") = " + key
								+ "(新增成功的商品編號)");
					}
				} while (rs.next());
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			rs.close();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			try {
				con.close();
			} catch (Exception e) {
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
	public int delete(Integer prono) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, prono);

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
	public ProductVO findByPrimaryKey(Integer prono) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver."
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
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
		return list;
	}

	public static void main(String[] args) throws Exception{
		ProductDAO_JDBC dao = new ProductDAO_JDBC();
		//新增商品
//		File insertFile = new File("D:\\Files\\455.jpg");      //new a file 
//		FileInputStream fis = new FileInputStream(insertFile);
		//以下三行將圖片轉換成byte[]陣列 
		//最後寫入Database
		FileInputStream fis = new FileInputStream("D:\\455.jpg");
		int len = fis.available(); 
		byte[] buffer = new byte[len];
		fis.read(buffer); //這行要加
		fis.close();
		ProductVO productVO_insert = new ProductVO();		
		productVO_insert.setProductname("新耐吉斯．成犬火雞肉+田園蔬果【7.5公斤】");
		productVO_insert.setCategory("狗飼料");
		productVO_insert.setPrice(1060);
		productVO_insert.setImage1(buffer);
		productVO_insert.setQuantity(100);
		productVO_insert.setMinimumquantity(5);
		// java.util.Date now = new java.util.Date(); //取得現在時間
		// SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
//		sd.setTimeZone(TimeZone.getTimeZone("GMT"));// 設定時區為格林威治 GMT 時區
		// String sGMT = sd.format(now);
//		String sGMT = "2014-06-01";
//		java.sql.Date availableDate = Date.valueOf(sGMT);// 要轉換成 java.sql.Date
//															// 的物件才可以寫入資料庫
//															// Timestamp
//		productVO_insert.setDateAvailable(availableDate);
		productVO_insert.setStatus(0);
		productVO_insert.setKeyword("狗飼料, 動物食品");
		productVO_insert
				.setDescription("添加大量的田園蔬果及鮮肉，美味的口感，滿足挑剔的胃口低敏配方，不含玉米、大豆、小麥、大豆…容易引起食物過敏的食材讓狗狗減少搔癢落毛困擾，讓毛髮更加柔柔亮亮喲");
		productVO_insert.setRelatedProducts(null);
		productVO_insert.setPriority(1);
		productVO_insert.setDiscount(1.0);
		productVO_insert.setScore(0);
		int updateCount_insert = dao.insert(productVO_insert);
		System.out.println(updateCount_insert);
		//修改商品
		//刪除
		//查詢
		List<ProductVO> list = dao.getAll();
		for(ProductVO product : list){
			System.out.println(product.getProno()+",");
			System.out.println(product.getProductname()+",");
			System.out.println(product.getCategory()+",");
			System.out.println(product.getPrice()+",");
			System.out.println(product.getImage1()+",");
			System.out.println(product.getImage2()+",");
			System.out.println(product.getImage3()+",");
			System.out.println(product.getQuantity()+",");
			System.out.println(product.getMinimumquantity()+",");
			System.out.println(product.getStatus()+",");
			System.out.println(product.getKeyword()+",");
			System.out.println(product.getDescription()+",");
			System.out.println(product.getRelatedProducts()+",");
			System.out.println(product.getPriority()+",");
			System.out.println(product.getDiscount()+",");
			System.out.println(product.getScore()+",");
			System.out.println(product+",");
			System.out.println();
		}
		
	}
}
