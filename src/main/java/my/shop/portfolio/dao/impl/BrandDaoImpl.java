	package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.dao.BrandDao;
import my.shop.portfolio.dto.BrandDto;
import my.shop.portfolio.mapper.BrandMapper;

@Repository("brandDao")
public class BrandDaoImpl implements BrandDao{
	
	@Autowired
	private BrandMapper brandMapper;
	
	@Override
	// Navbar에 Brand 출력시 사용
	public ArrayList<BrandDto> selectBrandAll(){
		return brandMapper.selectBrandAll();
	}
	
}
