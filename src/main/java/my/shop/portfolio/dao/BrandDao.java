package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.dto.BrandDto;

public interface BrandDao {
	// select all문
	public ArrayList<BrandDto> selectBrandAll();

}
