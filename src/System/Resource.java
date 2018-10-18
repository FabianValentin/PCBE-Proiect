package System;

import java.util.HashMap;

public class Resource {
	private HashMap<String,Integer> resource;
	
	public Resource(HashMap<String, Integer> res) {
		this.resource = res;
	}
	
	public HashMap<String, Integer> getResource() {
		return this.resource;
	}
}
