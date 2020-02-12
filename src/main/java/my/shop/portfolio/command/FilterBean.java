package my.shop.portfolio.command;

import java.util.ArrayList;

import my.shop.portfolio.common.Pagination;

public class FilterBean {
	String categorym_id, categorys_id;
	ArrayList<String> arrBrand;
	String lower="0", upper="200000";
	String sorting;
	Pagination pagination;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
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

	public ArrayList<String> getArrBrand() {
		return arrBrand;
	}

	public void setArrBrand(ArrayList<String> arrBrand) {
		this.arrBrand = arrBrand;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

	public String getSorting() {
		return sorting;
	}

	public void setSorting(String sorting) {
		this.sorting = sorting;
	}
}
