package com.order.model;
import java.sql.Date;
public class OrderVO implements java.io.Serializable{
	private String ordNo;
	private Date ordTime;
	private String ordAddr;
	private String ordTel;
	private Date ordGOTime;
	private Date ordArrTime;
	private Date ordDelTime;
	private Integer ordState;
	private String memNo;
	private Integer empNo;
	public String getOrdNo() {
		return ordNo;
	}
	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}
	public Date getOrdTime() {
		return ordTime;
	}
	public void setOrdTime(Date ordTime) {
		this.ordTime = ordTime;
	}
	public String getOrdAddr() {
		return ordAddr;
	}
	public void setOrdAddr(String ordAddr) {
		this.ordAddr = ordAddr;
	}
	public String getOrdTel() {
		return ordTel;
	}
	public void setOrdTel(String ordTel) {
		this.ordTel = ordTel;
	}
	public Date getOrdGOTime() {
		return ordGOTime;
	}
	public void setOrdGOTime(Date ordGOTime) {
		this.ordGOTime = ordGOTime;
	}
	public Date getOrdArrTime() {
		return ordArrTime;
	}
	public void setOrdArrTime(Date ordArrTime) {
		this.ordArrTime = ordArrTime;
	}
	public Date getOrdDelTime() {
		return ordDelTime;
	}
	public void setOrdDelTime(Date ordDelTime) {
		this.ordDelTime = ordDelTime;
	}
	public Integer getOrdState() {
		return ordState;
	}
	public void setOrdState(Integer ordState) {
		this.ordState = ordState;
	}
	public String getMemNo() {
		return memNo;
	}
	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	
	
}
