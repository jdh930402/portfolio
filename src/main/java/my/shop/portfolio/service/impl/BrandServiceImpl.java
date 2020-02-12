package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.dao.BrandDao;
import my.shop.portfolio.dto.BrandDto;
import my.shop.portfolio.service.BrandService;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandDao brandDao;

	// Navbar에 Brand 정보를 출력시 사용
	@Override
	public ArrayList<BrandDto> NavbarBrand() {
		return brandDao.selectBrandAll();
	}
}
