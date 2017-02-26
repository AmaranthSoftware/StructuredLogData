package com.amaranth.structlog.test;

import java.util.Date;

public class TestRequestSubObjectA {
	String s;
	int i;
	Date d;
	double f = 1.2345;
	String nullObject;
	private int x = 456;
	static private int y = 789;
	

	public TestRequestSubObjectA() {

	}

	public TestRequestSubObjectA(String s, int i, Date d) {
		this.s = s;
		this.d = d;
		this.i = i;
	}
}
