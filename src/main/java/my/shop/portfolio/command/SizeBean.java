package my.shop.portfolio.command;

import java.util.ArrayList;

public class SizeBean {
	String id, size;
	ArrayList<String> arrSize;

	public ArrayList<String> getArrSize() {
		return arrSize;
	}

	public void setArrSize(ArrayList<String> arrSize) {
		this.arrSize = arrSize;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
