package my.shop.portfolio.service;

import java.util.ArrayList;

import my.shop.portfolio.dto.CategoryDto;

public interface CategoryService {
	// Navbar에 Categorym 정보를 출력
	public ArrayList<CategoryDto> categoryM();
	
	// Navbar에 Categorys 정보를 출력
	public ArrayList<CategoryDto> categoryS();
	
	// 상품 등록시 categorym의 id를 이용하여 관련된 categorys 값만 추출 
	public ArrayList<CategoryDto> categorysCluster(String categorym_num);
}
