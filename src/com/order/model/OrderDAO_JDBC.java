package com.order.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.sql.*;

/*
 * Use Table: PRO_ORDER
 **/
public class OrderDAO_JDBC implements OrderDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2";
	static GetTimer toDay = new GetTimer("yyyyMMdd");
	static String orderDate = toDay.GetToDay();
	// private static final String INSERT_STMT =
	// "INSERT INTO pro_order(ordNo, ordTime, ordAddr, ordTel, ordGOTime, ordArrTime, ordDelTime, ordState, mem_No, empNo)VALUES(CONCAT("
	// + toDay
	// + ",TO_CHAR(PRO_ORDER_seq.NEXTVAL)), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String INSERT_STMT = "INSERT INTO pro_order(ord_No, ord_Time, ord_Addr, ord_Tel, ord_GOTime, ord_ArrTime, ord_DelTime, ord_State, mem_No, empNo)VALUES(CONCAT("+orderDate+", PRO_ORDER_seq.NEXTVAL), SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GETALL_STMT = "SELECT ord_No, to_char(ord_Time, 'yyyy-mm-dd hh24:mi:ss') AS ord_Time, ord_Addr, ord_Tel, to_char(ord_GOTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_GOTime, to_char(ord_ArrTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_ArrTime ,to_char(ord_DelTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_DelTime ,ord_State, mem_No, empNo FROM pro_order ORDER BY ord_No";
	private static final String GET_ONE_STMT = "SELECT ord_No, to_char(ord_Time, 'yyyy-mm-dd hh24:mi:ss') AS ord_Time, ord_Addr, ord_Tel, to_char(ord_GOTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_GOTime, to_char(ord_ArrTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_ArrTime,to_char(ord_DelTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_DelTime,ord_State, mem_No, empNo FROM pro_order WHERE ord_No = ?";
	private static final String DELETE = "DELETE FROM pro_order WHERE ord_No = ?";
	private static final String UPDATE = "UPDATE pro_order SET ord_Addr = ?, ord_Tel = ?, ord_GOTime = ?, ord_ArrTime = ?, ord_DelTime = ?, ord_State = ?, mem_No = ?, empNo = ? where ord_No =?";

	@Override
	public int insert(OrderVO orderVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			// pstmt.setString(1, orderVO.getOrdNo());
			// pstmt.setTimestamp(1, orderVO.getOrd_Time());
			pstmt.setString(1, orderVO.getOrd_Addr());
			pstmt.setString(2, orderVO.getOrd_Tel());
			pstmt.setTimestamp(3, orderVO.getOrd_GOTime());
			pstmt.setTimestamp(4, orderVO.getOrd_ArrTime());
			pstmt.setTimestamp(5, orderVO.getOrd_DelTime());
			pstmt.setInt(6, orderVO.getOrd_State());
			pstmt.setString(7, orderVO.getMem_No());
			pstmt.setInt(8, orderVO.getEmpNo());

			updateCount = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			se.printStackTrace(System.err);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	}

	@Override
	public int update(OrderVO orderVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, orderVO.getOrd_Addr());
			pstmt.setString(2, orderVO.getOrd_Tel());
			// pstmt.setTimestamp(3, orderVO.getOrd_Time());
			pstmt.setTimestamp(3, orderVO.getOrd_GOTime());
			pstmt.setTimestamp(4, orderVO.getOrd_ArrTime());
			pstmt.setTimestamp(5, orderVO.getOrd_DelTime());
			pstmt.setInt(6, orderVO.getOrd_State());
			pstmt.setString(7, orderVO.getMem_No());
			pstmt.setInt(8, orderVO.getEmpNo());
			pstmt.setString(9, orderVO.getOrd_No());

			updateCount = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	}

	@Override
	public int delete(Integer ordNo) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ordNo);
			updateCount = pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
					+ se.getMessage());
		} finally {
			if (pstmt != null) {

				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return updateCount;
	}

	@Override
	public OrderVO findByPrimaryKey(String ordNo) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, ordNo);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrd_No(rs.getString("ord_No"));
				orderVO.setOrd_Time(rs.getTimestamp("ord_Time"));
				orderVO.setOrd_Addr(rs.getString("ord_Addr"));
				orderVO.setOrd_Tel(rs.getString("ord_Tel"));
				orderVO.setOrd_GOTime(rs.getTimestamp("ord_GOTime"));
				orderVO.setOrd_ArrTime(rs.getTimestamp("ord_ArrTime"));
				orderVO.setOrd_DelTime(rs.getTimestamp("ord_DelTime"));
				orderVO.setOrd_State(rs.getInt("ord_State"));
				orderVO.setMem_No(rs.getString("mem_No"));
				orderVO.setEmpNo(rs.getInt("empNo"));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBC Driver Not found."
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occure:"
					+ se.getMessage());
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
					e.printStackTrace();
				}
			}
		}

		return orderVO;
	}

	@SuppressWarnings("null")
	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GETALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				orderVO = new OrderVO();
				orderVO.setOrd_No(rs.getString("ord_No"));
				orderVO.setOrd_Time(rs.getTimestamp("ord_Time"));
				orderVO.setOrd_Addr(rs.getString("ord_Addr"));
				orderVO.setOrd_Tel(rs.getString("ord_Tel"));
				orderVO.setOrd_GOTime(rs.getTimestamp("ord_GOTime"));
				orderVO.setOrd_ArrTime(rs.getTimestamp("ord_ArrTime"));
				orderVO.setOrd_DelTime(rs.getTimestamp("ord_DelTime"));
				orderVO.setOrd_State(rs.getInt("ord_State"));
				orderVO.setMem_No(rs.getString("mem_No"));
				orderVO.setEmpNo(rs.getInt("empNo"));
				list.add(orderVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("JDBC Driver Not fount."
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Database error occure."
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return list;
	}

	public static void main(String[] args) {
		OrderDAO_JDBC dao = new OrderDAO_JDBC();
		int updateCount = 0;

		// INSERT
		 OrderVO orderVO_INSERT = new OrderVO();
		 orderVO_INSERT.setOrd_Addr("320桃園縣中壢市中大路300-1號");
		 orderVO_INSERT.setOrd_Tel("0978225413");
		 orderVO_INSERT.setOrd_GOTime(null);
		 orderVO_INSERT.setOrd_ArrTime(null);
		 orderVO_INSERT.setOrd_DelTime(null);
		 orderVO_INSERT.setOrd_State(0);
		 orderVO_INSERT.setMem_No("100569");
		 orderVO_INSERT.setEmpNo(1009);
		 updateCount = dao.insert(orderVO_INSERT);
		 System.out.println("成功插入"+updateCount+"筆資料!");

		// UPDATE
		// OrderVO orderVO_UPDATE = new OrderVO();
		// java.util.Date date = new Date();
		// Timestamp ts = new Timestamp(date.getTime());
		//
		// orderVO_UPDATE.setOrd_No("1021");
		// orderVO_UPDATE.setOrd_Addr("320桃園縣中壢市中大路300-1號");
		// orderVO_UPDATE.setOrd_Tel("0978225413");
		// //orderVO_UPDATE.setOrd_GOTime(ts);
		// orderVO_UPDATE.setOrd_ArrTime(null);
		// //orderVO_UPDATE.setOrd_DelTime(null);
		// orderVO_UPDATE.setOrd_State(0);
		// orderVO_UPDATE.setMem_No("100569");
		// orderVO_UPDATE.setEmpNo(1009);
		// updateCount = dao.update(orderVO_UPDATE);
		// System.out.println("成功更新"+updateCount+"筆資料!");

		// DELETE
		// OrderVO orderVO_DELETE = new OrderVO();
		// int updateCount_delete = dao.delete(1021);
		// System.out.println("成功刪除"+updateCount_delete+"筆資料!");

		// SELECT
//		 OrderVO orderVO_SELECT_ONE = dao.findByPrimaryKey("1009");
//		 System.out.println(orderVO_SELECT_ONE.getOrd_No());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_Time());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_Addr());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_Tel());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_GOTime());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_ArrTime());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_DelTime());
//		 System.out.println(orderVO_SELECT_ONE.getOrd_State());
//		 System.out.println(orderVO_SELECT_ONE.getMem_No());
//		 System.out.println(orderVO_SELECT_ONE.getEmpNo());
//		 System.out.println("======================================");
		 
		// SELECT GET ALL
		//OrderVO order_SELECT_ALL = new OrderVO();
//		List<OrderVO> list = dao.getAll();
//
//		for (OrderVO orderVO : list) {
//			System.out.println(orderVO.getOrd_No()+", ");
//			System.out.println(orderVO.getOrd_Time()+", ");
//			System.out.println(orderVO.getOrd_Addr()+", ");
//			System.out.println(orderVO.getOrd_Tel()+", ");
//			System.out.println(orderVO.getOrd_GOTime()+", ");
//			System.out.println(orderVO.getOrd_ArrTime()+", ");
//			System.out.println(orderVO.getOrd_DelTime()+", ");
//			System.out.println(orderVO.getOrd_State()+", ");
//			System.out.println(orderVO.getMem_No()+", ");
//			System.out.println(orderVO.getEmpNo());
//			System.out.println("======================================");
//		}
	}
}
