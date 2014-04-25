package com.order.model;

public class test {

	public static void main(String[] args) {
		GetTimer getToDay = new GetTimer("yyyy-MM-dd HH:mm:ss");
		String toDay = getToDay.GetToDay();
		System.out.println(toDay);

	}

}
