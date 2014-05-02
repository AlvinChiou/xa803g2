package com.order.model;
import java.sql.Timestamp;
import java.util.*;
public class OrderVO implements java.io.Serializable{
	private String ordno;
	private Timestamp ordtime;
	private String ordaddr;
	private String ordtel;
	private Timestamp ordgotime;
	private Timestamp ordarrtime;
	private Timestamp orddeltime;
	private Integer ordstate;
	private String memno;
	private Integer empno;
	public String getOrdno() {
		return ordno;
	}
	public void setOrdno(String ordno) {
		this.ordno = ordno;
	}
	public Timestamp getOrdtime() {
		return ordtime;
	}
	public void setOrdtime(Timestamp ordtime) {
		this.ordtime = ordtime;
	}
	public String getOrdaddr() {
		return ordaddr;
	}
	public void setOrdaddr(String ordaddr) {
		this.ordaddr = ordaddr;
	}
	public String getOrdtel() {
		return ordtel;
	}
	public void setOrdtel(String ordtel) {
		this.ordtel = ordtel;
	}
	public Timestamp getOrdgotime() {
		return ordgotime;
	}
	public void setOrdgotime(Timestamp ordgotime) {
		this.ordgotime = ordgotime;
	}
	public Timestamp getOrdarrtime() {
		return ordarrtime;
	}
	public void setOrdarrtime(Timestamp ordarrtime) {
		this.ordarrtime = ordarrtime;
	}
	public Timestamp getOrddeltime() {
		return orddeltime;
	}
	public void setOrddeltime(Timestamp orddeltime) {
		this.orddeltime = orddeltime;
	}
	public Integer getOrdstate() {
		return ordstate;
	}
	public void setOrdstate(Integer ordstate) {
		this.ordstate = ordstate;
	}
	public String getMemno() {
		return memno;
	}
	public void setMemno(String memno) {
		this.memno = memno;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	
	
	
	
}
