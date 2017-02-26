package com.amaranth.structlog.test;


import java.util.Date;

class TestRequest {
	
	
	String s;
	int i;
	Date d;
	double f = 1.2345;
	String nullObject;
	private int x = 4567;
	static private int y = 789;
	
	private TestRequestSubObjectA a = new TestRequestSubObjectA("testObjA",99, new Date());
	
	public TestRequest() {

	}

	public TestRequest(String s, int i, Date d) {
		this.s = s;
		this.d = d;
		this.i = i;
	}

	@Override
	public String toString() {
		return "TestRequest [s=" + s + ", i=" + i + ", d=" + d + ", f=" + f + ", nullObject=" + nullObject + ", x=" + x
				+ ", a=" + a + "]";
	}
	
	
}