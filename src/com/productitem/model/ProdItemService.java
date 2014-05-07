package com.productitem.model;
import java.util.*;
public class ProdItemService {
	private ProdItemDAO_interface dao;
	public ProdItemService(){
		dao = new ProdItemDAO();
	}
	public ProdItemVO addProdItem(Integer itemno, Integer itemqty, String itemmemo, String ordno, Integer prono){
		ProdItemVO prodItemVO = new ProdItemVO();
		
		return prodItemVO;
	}
	
}
