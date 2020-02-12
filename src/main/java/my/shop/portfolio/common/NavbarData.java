package my.shop.portfolio.common;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.shop.portfolio.dto.BrandDto;
import my.shop.portfolio.dto.CategoryDto;
import my.shop.portfolio.service.BrandService;
import my.shop.portfolio.service.CategoryService;

@Component
public class NavbarData {

	private static BrandService brandService;
	private static CategoryService categoryService;
	private static NavbarData navbarData = null;

	@Autowired
	private NavbarData(BrandService brandService, CategoryService categoryService) {
		NavbarData.brandService = brandService;
		NavbarData.categoryService = categoryService;

	}

	public static NavbarData getInstance() {
		if (navbarData == null) {
			navbarData = new NavbarData(brandService, categoryService);
		}
		return navbarData;
	}

	public ArrayList<BrandDto> getBrand() {
		return brandService.NavbarBrand();
	}

	public ArrayList<CategoryDto> getCategoryM() {
		return categoryService.categoryM();
	}

	public ArrayList<CategoryDto> getCategoryS() {
		return categoryService.categoryS();
	}
}
