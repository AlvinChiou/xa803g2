package com.order.model;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;
import java.sql.Date;
import java.util.Calendar;

public class OrderDAO_JDBC implements OrderDAO_Interface {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String userid = "xa803g2";
	String passwd = "xa803g2";
	static GetTimer getToDay = new GetTimer("yyyyMMdd");
	static String toDay = getToDay.GetToDay();
	private static final String INSERT_STMT = "INSERT INTO pro_order(ordNo, ordTime, ordAddr, ordTel, ordGOTime, ordArrTime, ordDelTime, ordState, memNo, empNo)VALUES(CONCAT("+toDay+",TO_CHAR(PRO_ORDER_seq.NEXTVAL)), ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static void main(String[] args) {
		//以下是取得現在時間
		 //String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
//		 String DATE_FORMAT_NOW = "yyyyMMdd";
//		 Calendar tmpCal = Calendar.getInstance();
//		 SimpleDateFormat tmpSDF = new SimpleDateFormat(DATE_FORMAT_NOW);
//		 String nowTime = tmpSDF.format(tmpCal.getTime()).toString();
	}
}
