package com.order.model;
import java.sql.Timestamp;
import java.util.*;
public class OrderVO implements java.io.Serializable{
	private String ord_No;
	private Timestamp ord_Time;
	private String ord_Addr;
	private String ord_Tel;
	private Timestamp ord_GOTime;
	private Timestamp ord_ArrTime;
	private Timestamp ord_DelTime;
	private Integer ord_State;
	private String mem_No;
	private Integer empNo;
	public String getOrd_No() {
		return ord_No;
	}
	public void setOrd_No(String ord_No) {
		this.ord_No = ord_No;
	}
	public Timestamp getOrd_Time() {
		return ord_Time;
	}
	public void setOrd_Time(Timestamp ord_Time) {
		this.ord_Time = ord_Time;
	}
	public String getOrd_Addr() {
		return ord_Addr;
	}
	public void setOrd_Addr(String ord_Addr) {
		this.ord_Addr = ord_Addr;
	}
	public String getOrd_Tel() {
		return ord_Tel;
	}
	public void setOrd_Tel(String ord_Tel) {
		this.ord_Tel = ord_Tel;
	}
	public Timestamp getOrd_GOTime() {
		return ord_GOTime;
	}
	public void setOrd_GOTime(Timestamp ord_GOTime) {
		this.ord_GOTime = ord_GOTime;
	}
	public Timestamp getOrd_ArrTime() {
		return ord_ArrTime;
	}
	public void setOrd_ArrTime(Timestamp ord_ArrTime) {
		this.ord_ArrTime = ord_ArrTime;
	}
	public Timestamp getOrd_DelTime() {
		return ord_DelTime;
	}
	public void setOrd_DelTime(Timestamp ord_DelTime) {
		this.ord_DelTime = ord_DelTime;
	}
	public Integer getOrd_State() {
		return ord_State;
	}
	public void setOrd_State(Integer ord_State) {
		this.ord_State = ord_State;
	}
	public String getMem_No() {
		return mem_No;
	}
	public void setMem_No(String mem_No) {
		this.mem_No = mem_No;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
	
	
}
