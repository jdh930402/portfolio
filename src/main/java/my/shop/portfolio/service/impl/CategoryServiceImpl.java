package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.dao.CategoryDao;
import my.shop.portfolio.dto.CategoryDto;
import my.shop.portfolio.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public ArrayList<CategoryDto> categoryM() {
		return categoryDao.selectCategorymAll();
	}
	
	@Override
	public ArrayList<CategoryDto> categoryS() {
		return categoryDao.selectCategorysAll();
	}
	
	@Override
	// 상품 등록시 categorym의 id를 이용하여 관련된 categorys 값만 추출
	public ArrayList<CategoryDto> categorysCluster(String categorym_num) {
		return categoryDao.selectCategorysCluster(categorym_num);
	}
}
