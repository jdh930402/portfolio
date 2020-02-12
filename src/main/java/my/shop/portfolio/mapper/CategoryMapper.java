package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.dto.CategoryDto;

public interface CategoryMapper {
	// select all문
	
	// categorym 전체를 출력
	@Select("SELECT id, name FROM categorym")
	public ArrayList<CategoryDto> selectCategorymAll();
	
	// categorys 전체를 출력
	@Select("SELECT id, name, categorym_num FROM categorys")
	public ArrayList<CategoryDto> selectCategorysAll();
	
	// 상품 등록시 categorym의 id를 이용하여 관련된 categorys 값만 추출 
	@Select("SELECT id, name FROM categorys WHERE categorym_num = #{categorym_num}")
	public ArrayList<CategoryDto> selectCategorysCluster(String categorym_num);
	
	// select one문
	// insert문
	// update문
	// delete문

}
