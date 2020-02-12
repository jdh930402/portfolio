package my.shop.portfolio.command;

import java.util.ArrayList;

public class ProductBean {
	String id, categorym_id, categorys_id, name, price, brand_id, origin_id, regdate;
	String product_id, size_id, amount;
	String img_name, depth;
	ArrayList<String> sizeList, amountList;

	public ArrayList<String> getSizeList() {
		return sizeList;
	}

	public void setSizeList(ArrayList<String> sizeList) {
		this.sizeList = sizeList;
	}

	public ArrayList<String> getAmountList() {
		return amountList;
	}

	public void setAmountList(ArrayList<String> amountList) {
		this.amountList = amountList;
	}

	public String getImg_name() {
		return img_name;
	}

	public void setImg_name(String img_name) {
		this.img_name = img_name;
	}

	public String getDepth() {
		return depth;
	}

	public void setDepth(String depth) {
		this.depth = depth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategorym_id() {
		return categorym_id;
	}

	public void setCategorym_id(String categorym_id) {
		this.categorym_id = categorym_id;
	}

	public String getCategorys_id() {
		return categorys_id;
	}

	public void setCategorys_id(String categorys_id) {
		this.categorys_id = categorys_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getOrigin_id() {
		return origin_id;
	}

	public void setOrigin_id(String origin_id) {
		this.origin_id = origin_id;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public String getSize_id() {
		return size_id;
	}

	public void setSize_id(String size_id) {
		this.size_id = size_id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}
