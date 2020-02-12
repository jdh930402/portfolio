package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.dto.BrandDto;

public interface BrandMapper {
	// select all문
	
	// 브랜드 전체를 검색
	@Select("SELECT id, name, image FROM brand")
	public ArrayList<BrandDto> selectBrandAll();
	
	// select one문
	// insert문
	// update문
	// delete문
}
