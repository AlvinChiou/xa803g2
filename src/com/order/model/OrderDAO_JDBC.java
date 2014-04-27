package com.order.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.util.Calendar;
import java.sql.Connection;

import javax.management.RuntimeErrorException;

import oracle.jdbc.proxy.annotation.GetDelegate;

public class OrderDAO_JDBC implements OrderDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2";
	static GetTimer getToDay = new GetTimer("yyyyMMdd");
	static String toDay = getToDay.GetToDay();
	private static final String INSERT_STMT = "INSERT INTO pro_order(ordNo, ordTime, ordAddr, ordTel, ordGOTime, ordArrTime, ordDelTime, ordState, memNo, empNo)VALUES(CONCAT("
			+ toDay
			+ ",TO_CHAR(PRO_ORDER_seq.NEXTVAL)), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GETALL_STMT = "SELECT ordNo, to_cahr(ordTime, 'yyyy-mm-dd hh24:mi:ss')ordTime, ordAddr, ordTel, to_char(ordGOTime, 'yyyy-mm-dd hh24:mi:ss')ordGOTime, to_char(ordArrTime, 'yyyy-mm-dd hh24:mi:ss')ordArrTime,to_char(ordDelTime, 'yyyy-mm-dd hh24:mi:ss')ordDelTime,ordState, memNo, empNo FROM pro_order ORDER BY ordNo";
	private static final String GET_ONE_STMT = "SELECT ordNo, to_cahr(ordTime, 'yyyy-mm-dd hh24:mi:ss')ordTime, ordAddr, ordTel, to_char(ordGOTime, 'yyyy-mm-dd hh24:mi:ss')ordGOTime, to_char(ordArrTime, 'yyyy-mm-dd hh24:mi:ss')ordArrTime,to_char(ordDelTime, 'yyyy-mm-dd hh24:mi:ss')ordDelTime,ordState, memNo, empNo FROM pro_order WHERE ordNo = ?";
	private static final String DELETE = "DELETE FROM pro_order WHERE ordNo = ?";
	private static final String UPDATE = "UPDATE pro_order SET ordNo = ?, ordTime = ?, ordAddr = ?, ordTel = ?, ordGOTime = ?, ordArrTime = ?, ordDelTime = ?, ordState = ?, memNo = ?, empNo = ?";

	@Override
	public int insert(OrderVO orderVO) {
		int updateCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderVO.getOrdNo());
			pstmt.setDate(2, orderVO.getOrdTime());
			pstmt.setString(3, orderVO.getOrdAddr());
			pstmt.setString(4, orderVO.getOrdTel());
			pstmt.setDate(5, orderVO.getOrdGOTime());
			pstmt.setDate(6, orderVO.getOrdArrTime());
			pstmt.setDate(7, orderVO.getOrdDelTime());
			pstmt.setInt(8, orderVO.getOrdState());
			pstmt.setString(9, orderVO.getMemNo());
			pstmt.setInt(10, orderVO.getEmpNo());

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

			pstmt.setString(1, orderVO.getOrdNo());
			pstmt.setDate(2, orderVO.getOrdTime());
			pstmt.setString(3, orderVO.getOrdAddr());
			pstmt.setString(4, orderVO.getOrdTel());
			pstmt.setDate(5, orderVO.getOrdGOTime());
			pstmt.setDate(6, orderVO.getOrdArrTime());
			pstmt.setDate(7, orderVO.getOrdDelTime());
			pstmt.setInt(8, orderVO.getOrdState());
			pstmt.setString(9, orderVO.getMemNo());
			pstmt.setInt(10, orderVO.getEmpNo());

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
	public OrderVO findByPrimaryKey(Integer ordNo) {
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			while(rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrdNo(rs.getString("ordNo"));
				orderVO.setOrdTime(rs.getDate("ordTime"));
				orderVO.setOrdAddr(rs.getString("ordAddr"));
				orderVO.setOrdTel(rs.getString("ordTel"));
				orderVO.setOrdGOTime(rs.getDate("ordGOTime"));
				orderVO.setOrdArrTime(rs.getDate ("ordArrTime"));
				orderVO.setOrdDelTime(rs.getDate("ordDelTime"));
				orderVO.setOrdState(rs.getInt("ordState"));
				orderVO.setMemNo(rs.getString("memNo"));
				orderVO.setEmpNo(rs.getInt("empNo"));
			}
			
			
		}catch(ClassNotFoundException e){
			throw new RuntimeException("JDBC Driver Not found."+e.getMessage());
		}catch(SQLException se){
			throw new RuntimeException("A database error occure:"+se.getMessage());
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
					e.printStackTrace();
				}
			}
		}
		
		
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {

		return null;
	}

	public static void main(String[] args) {

	}
}
