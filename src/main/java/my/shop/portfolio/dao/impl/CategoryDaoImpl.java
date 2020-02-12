package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.dao.CategoryDao;
import my.shop.portfolio.dto.CategoryDto;
import my.shop.portfolio.mapper.CategoryMapper;

@Repository("categoryDao")
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	// categorym 전체를 출력	
	public ArrayList<CategoryDto> selectCategorymAll() {
		return categoryMapper.selectCategorymAll();
	}
	
	@Override
	// categorys를 공통의 categorym_num을 가진 것들을 그룹으로 출력
	public ArrayList<CategoryDto> selectCategorysAll() {
		return categoryMapper.selectCategorysAll();
	}
	
	@Override
	// 상품 등록시 categorym의 id를 이용하여 관련된 categorys 값만 추출 
	public ArrayList<CategoryDto> selectCategorysCluster(String categorym_num) {
		return categoryMapper.selectCategorysCluster(categorym_num);
	}
}
