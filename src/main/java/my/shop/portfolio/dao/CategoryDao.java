package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.dto.CategoryDto;


public interface CategoryDao {
	// categorym 전체를 출력
	public ArrayList<CategoryDto> selectCategorymAll();
	
	
	// categorys를 공통의 categorym_num을 가진 것들을 그룹으로 출력
	public ArrayList<CategoryDto> selectCategorysAll();
	
	// 상품 등록시 categorym의 id를 이용하여 관련된 categorys 값만 추출 
	public ArrayList<CategoryDto> selectCategorysCluster(String categorym_num);

}
