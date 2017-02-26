package structlog;


import java.util.Date;

class TestRequest {
	
	
	String s;
	int i;
	Date d;
	double f = 1.2345;
	String nullObject;
	private int x = 456;
	static private int y = 789;
	

	public TestRequest() {

	}

	public TestRequest(String s, int i, Date d) {
		this.s = s;
		this.d = d;
		this.i = i;
	}
}