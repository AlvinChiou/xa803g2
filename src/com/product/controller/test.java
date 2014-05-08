package com.product.controller;
import java.io.*;
public class test {

	public static void main(String[] args) {
		String folderName = "ProductPicture";
		String folderPath = "D:\\WTP_Workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\xa803g2";		
		File imgDir = new File(folderPath+","+folderName);
		
		if(!imgDir.exists()){
			imgDir.mkdirs();
		}
		System.out.println("OK");

	}

}
