package System;

public class Resource {
	private String resourceName;
	private int noRes;
	
	public Resource(String res, int nr) {
		this.resourceName = res;
		this.noRes = nr;
	}
	
	public String getType() {
		return this.resourceName;
	}
	
	public int getNo() {
		return this.noRes;
	}
}
