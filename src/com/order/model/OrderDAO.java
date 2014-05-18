package com.order.model;

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

import com.productitem.model.ProdItemDAO;
import com.productitem.model.ProdItemVO;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

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
			"INSERT INTO pro_order(ordno, ordtime, ordaddr, ordtel, ordgotime, ordarrtime, orddeltime, ordstate, memno, empno)VALUES(CONCAT("
			+ orderDate+ ", PRO_ORDER_seq.NEXTVAL), SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT ordno, to_char(ordtime, 'yyyy-mm-dd hh24:mi:ss') AS ordtime, ordaddr, ordtel, to_char(ordgotime, 'yyyy-mm-dd hh24:mi:ss') AS ordgotime, to_char(ordarrtime, 'yyyy-mm-dd hh24:mi:ss') AS ordarrtime ,to_char(orddeltime, 'yyyy-mm-dd hh24:mi:ss') AS orddeltime ,ordstate, memno, empno FROM pro_order ORDER BY ordno";
	private static final String GET_ONE_STMT = 
			"SELECT ordno, to_char(ordtime, 'yyyy-mm-dd hh24:mi:ss') AS ordtime, ordaddr, ordtel, to_char(ordgotime, 'yyyy-mm-dd hh24:mi:ss') AS ordgotime, to_char(ordarrtime, 'yyyy-mm-dd hh24:mi:ss') AS ordarrtime,to_char(orddeltime, 'yyyy-mm-dd hh24:mi:ss') AS orddeltime,ordstate, memno, empno FROM pro_order WHERE ordno = ?";
	private static final String DELETE = 
			"DELETE FROM pro_order WHERE ordno = ?";
	private static final String UPDATE = 
			"UPDATE pro_order SET ordaddr = ?, ordtel = ?, ordgotime = ?, ordarrtime = ?, orddeltime = ?, ordstate = ?, memno = ?, empno = ? where ordno =?";

	@Override
	public void insert(OrderVO orderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, orderVO.getOrdaddr());
			pstmt.setString(2, orderVO.getOrdtel());
			pstmt.setTimestamp(3, orderVO.getOrdgotime());
			pstmt.setTimestamp(4, orderVO.getOrdarrtime());
			pstmt.setTimestamp(5, orderVO.getOrddeltime());
			pstmt.setInt(6, orderVO.getOrdstate());
			pstmt.setString(7, orderVO.getMemno());
			pstmt.setInt(8, orderVO.getEmpno());
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
			pstmt.setString(1, orderVO.getOrdaddr());
			pstmt.setString(2, orderVO.getOrdtel());
			// pstmt.setTimestamp(3, orderVO.getOrd_Time());
			pstmt.setTimestamp(3, orderVO.getOrdgotime());
			pstmt.setTimestamp(4, orderVO.getOrdarrtime());
			pstmt.setTimestamp(5, orderVO.getOrddeltime());
			pstmt.setInt(6, orderVO.getOrdstate());
			pstmt.setString(7, orderVO.getMemno());
			pstmt.setInt(8, orderVO.getEmpno());
			pstmt.setString(9, orderVO.getOrdno());

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
	public void delete(String ordno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			pstmt.setString(1, ordno);
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
	public OrderVO findByPrimaryKey(String ordno) {
		
		OrderVO orderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setString(1, ordno);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				orderVO = new OrderVO();
				orderVO.setOrdno(rs.getString("ordno"));
				orderVO.setOrdtime(rs.getTimestamp("ordtime"));
				orderVO.setOrdaddr(rs.getString("ordaddr"));
				orderVO.setOrdtel(rs.getString("ordtel"));
				orderVO.setOrdgotime(rs.getTimestamp("ordgOtime"));
				orderVO.setOrdarrtime(rs.getTimestamp("ordarrtime"));
				orderVO.setOrddeltime(rs.getTimestamp("orddeltime"));
				orderVO.setOrdstate(rs.getInt("ordstate"));
				orderVO.setMemno(rs.getString("memno"));
				orderVO.setEmpno(rs.getInt("empno"));
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
				orderVO.setOrdno(rs.getString("ordno"));
				orderVO.setOrdtime(rs.getTimestamp("ordtime"));
				orderVO.setOrdaddr(rs.getString("ordaddr"));
				orderVO.setOrdtel(rs.getString("ordtel"));
				orderVO.setOrdgotime(rs.getTimestamp("ordgotime"));
				orderVO.setOrdarrtime(rs.getTimestamp("ordarrtime"));
				orderVO.setOrddeltime(rs.getTimestamp("orddeltime"));
				orderVO.setOrdstate(rs.getInt("ordstate"));
				orderVO.setMemno(rs.getString("memno"));
				orderVO.setEmpno(rs.getInt("empno"));
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

	@Override
	public void insertWithOrderItems(OrderVO orderVO, Vector<ProdItemVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			//先新增訂單編號
			String cols[]={"ordno"}; //如果是複合主鍵則在陣列之內以逗號區隔
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, orderVO.getOrdaddr());
			pstmt.setString(2, orderVO.getOrdtel());
			pstmt.setTimestamp(3, orderVO.getOrdgotime());
			pstmt.setTimestamp(4, orderVO.getOrdarrtime());
			pstmt.setTimestamp(5, orderVO.getOrddeltime());
			pstmt.setInt(6, orderVO.getOrdstate());
			pstmt.setString(7, orderVO.getMemno());
			pstmt.setInt(8, orderVO.getEmpno());
			pstmt.executeUpdate();
			
			String thisOrderNo = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if(rs.next()){
				thisOrderNo = rs.getString(1);
				System.out.println("取得自增主鍵值 ="+thisOrderNo);
			}else{
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			//同時再新增訂單明細
			ProdItemDAO dao = new ProdItemDAO();
			for(ProdItemVO orderDetails : list){
				orderDetails.setOrdno(thisOrderNo);
				dao.insertByOrdNo(orderDetails, con);
			}
			//設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增訂單編號" + thisOrderNo + "時,共有訂單明細" + list.size()
					+ "筆同時被新增");
		}catch(SQLException se){
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

}
