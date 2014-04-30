package com.order.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrderDAO implements OrderDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/TestDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	static GetTimer toDay = new GetTimer("yyyyMMdd");
	static String orderDate = toDay.GetToDay();
	private static final String INSERT_STMT = 
			"INSERT INTO pro_order(ord_No, ord_Time, ord_Addr, ord_Tel, ord_GOTime, ord_ArrTime, ord_DelTime, ord_State, mem_No, empNo)VALUES(CONCAT("
			+ orderDate+ ", PRO_ORDER_seq.NEXTVAL), SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT ord_No, to_char(ord_Time, 'yyyy-mm-dd hh24:mi:ss') AS ord_Time, ord_Addr, ord_Tel, to_char(ord_GOTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_GOTime, to_char(ord_ArrTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_ArrTime ,to_char(ord_DelTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_DelTime ,ord_State, mem_No, empNo FROM pro_order ORDER BY ord_No";
	private static final String GET_ONE_STMT = 
			"SELECT ord_No, to_char(ord_Time, 'yyyy-mm-dd hh24:mi:ss') AS ord_Time, ord_Addr, ord_Tel, to_char(ord_GOTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_GOTime, to_char(ord_ArrTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_ArrTime,to_char(ord_DelTime, 'yyyy-mm-dd hh24:mi:ss') AS ord_DelTime,ord_State, mem_No, empNo FROM pro_order WHERE ord_No = ?";
	private static final String DELETE = 
			"DELETE FROM pro_order WHERE ord_No = ?";
	private static final String UPDATE = 
			"UPDATE pro_order SET ord_Addr = ?, ord_Tel = ?, ord_GOTime = ?, ord_ArrTime = ?, ord_DelTime = ?, ord_State = ?, mem_No = ?, empNo = ? where ord_No =?";

	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderVO.getOrd_Addr());
			pstmt.setString(2, orderVO.getOrd_Tel());
			pstmt.setTimestamp(3, orderVO.getOrd_GOTime());
			pstmt.setTimestamp(4, orderVO.getOrd_ArrTime());
			pstmt.setTimestamp(5, orderVO.getOrd_DelTime());
			pstmt.setInt(6, orderVO.getOrd_State());
			pstmt.setString(7, orderVO.getMem_No());
			pstmt.setInt(8, orderVO.getEmpNo());
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured."
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
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
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

			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
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
				try{
				con.close();
				}catch(Exception e){
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer ordNo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, ordNo);
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
	public OrderVO findByPrimaryKey(String ordNo) {
		
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ordNo);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrd_No(rs.getString("ordNo"));
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
		return orderVO;
	}

	@Override
	public List<OrderVO> getAll() {
		List<OrderVO> list = new ArrayList<OrderVO>();
		OrderVO orderVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
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
