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

import com.order.model.OrderVO;
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
	private static final String UPDATE = "UPDATE Product SET productname = ?, category = ?, price = ?, image1 = ?,"
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
						System.out.println("�ۼW�D���(" + i + ") = " + key
								+ "(�s�W���\���ӫ~�s��)");
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
			pstmt.setInt(16, productVO.getProno());
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
				list.add(productVO);
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

	public static void main(String[] args){
		ProductDAO_JDBC dao = new ProductDAO_JDBC();
		//�s�W�ӫ~
//		File insertFile = new File("D:\\Files\\455.jpg");      //new a file 
//		FileInputStream fis = new FileInputStream(insertFile);
		//�H�U�T��N�Ϥ��ഫ��byte[]�}�C 
		//�̫�g�JDatabase
//		FileInputStream fis = new FileInputStream("D:\\455.jpg");
//		int len = fis.available(); 
//		byte[] buffer = new byte[len];
//		fis.read(buffer); //�o��n�[
//		fis.close();
//		ProductVO productVO_insert = new ProductVO();		
//		productVO_insert.setProductname("�s�@�N���D����������+�ж齭�G�i7.5����j");
//		productVO_insert.setCategory("���}��");
//		productVO_insert.setPrice(1060);
//		productVO_insert.setImage1(buffer);
//		productVO_insert.setQuantity(100);
//		productVO_insert.setMinimumquantity(5);
//		productVO_insert.setStatus(0);
//		productVO_insert.setKeyword("���}��, �ʪ����~");
//		productVO_insert
//				.setDescription("�K�[�j�q���ж齭�G���A�סA�������f�P�A�����D�窺�G�f�C�Ӱt��A���t�ɦ̡B�j���B�p���B�j���K�e���ް_�����L�Ӫ�������������ַk�o����x�Z�A����v��[�X�X�G�G��");
//		productVO_insert.setRelatedProducts(null);
//		productVO_insert.setPriority(1);
//		productVO_insert.setDiscount(1.0);
//		productVO_insert.setScore(0);
//		int updateCount_insert = dao.insert(productVO_insert);
//		System.out.println(updateCount_insert);
		
		//�ק�ӫ~
//		ProductVO productVO_UPDATE = new ProductVO();
//		productVO_UPDATE.setProductname("�s�@�N���D����������+�ж齭�G�i7.5����j");
//		productVO_UPDATE.setCategory("���}��");
//		productVO_UPDATE.setPrice(1500);
//		productVO_UPDATE.setImage1(buffer);
//		productVO_UPDATE.setQuantity(200);
//		productVO_UPDATE.setMinimumquantity(3);
//		productVO_UPDATE.setStatus(1);
//		productVO_UPDATE.setKeyword("���}��, �ʪ����~, �S��");
//		productVO_UPDATE.setDescription("�K�[�j�q���ж齭�G���A�סA�������f�P�A�����D�窺�G�f�C�Ӱt��A���t�ɦ̡B�j���B�p���B�j���K�e���ް_�����L�Ӫ�������������ַk�o����x�Z�A����v��[�X�X�G�G��");
//		productVO_UPDATE.setRelatedProducts(null);
//		productVO_UPDATE.setPriority(2);
//		productVO_UPDATE.setDiscount(0.9);
//		productVO_UPDATE.setScore(1000);
//		productVO_UPDATE.setProno(5001);
//		int updateCount_update = dao.update(productVO_UPDATE);
//		System.out.println(updateCount_update);
		//�R��
//		ProductVO productVO_DELETE = new ProductVO();
//		int updateCount_delete = dao.delete(5001);
//		System.out.println("���\�R��"+updateCount_delete+"�����!");
		
		//�d��
//		ProductVO productVO_SELECT_ONE = dao.findByPrimaryKey(21);
//		System.out.println(productVO_SELECT_ONE.getProno());
//		System.out.println(productVO_SELECT_ONE.getProductname());
//		System.out.println(productVO_SELECT_ONE.getCategory());
//		System.out.println(productVO_SELECT_ONE.getPrice());
//		System.out.println(productVO_SELECT_ONE.getImage1());
//		System.out.println(productVO_SELECT_ONE.getImage2());
//		System.out.println(productVO_SELECT_ONE.getImage3());
//		System.out.println(productVO_SELECT_ONE.getQuantity());
//		System.out.println(productVO_SELECT_ONE.getMinimumquantity());
//		System.out.println(productVO_SELECT_ONE.getStatus());
//		System.out.println(productVO_SELECT_ONE.getKeyword());
//		System.out.println(productVO_SELECT_ONE.getDescription());
//		System.out.println(productVO_SELECT_ONE.getRelatedProducts());
//		System.out.println(productVO_SELECT_ONE.getPriority());
//		System.out.println(productVO_SELECT_ONE.getDiscount());
//		System.out.println(productVO_SELECT_ONE.getScore());
//		System.out.println("================================");
		
		//ProductVO productVO_SELECT_ALL = new ProductVO();
		System.out.println("================================");
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
			System.out.println("==============================");
		}
		
	}
}
