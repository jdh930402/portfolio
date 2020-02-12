package my.shop.portfolio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.FilterBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.ProductDto;
import my.shop.portfolio.service.ProductService;

@Controller
public class ProductListController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "productlist", method = RequestMethod.GET)
	public ModelAndView CategoryMove(FilterBean filterBean, Pagination pagination) {
		ModelAndView modelAndView = new ModelAndView("productlist");
		ArrayList<ProductDto> product = new ArrayList<ProductDto>();

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		// category별로 상품을 검색한다.
		// 전체상품 출력시
		if (filterBean.getCategorym_id() == null && filterBean.getCategorys_id() == null) {
			// 처음 접속시 기본 페이징처리(6개씩 출력)
			pagination.paginationSetting(pagination.getPage(), 6, 10, productService.countProductAll(filterBean));
			product = productService.selectProductAll(pagination, filterBean);
		} else {
			// 처음 접속시 기본 페이징처리(6개씩 출력) => 카테고리별로
			pagination.paginationSetting(pagination.getPage(), 6, 10, productService.countProductCategory(filterBean));
			product = productService.selectProductCategory(pagination, filterBean);
		}

		modelAndView.addObject("product", product);
		modelAndView.addObject("pagination", pagination);

		return modelAndView;
	}

	@RequestMapping(value = "productlist/checkBrand", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> FilterAjax(@RequestBody FilterBean filterBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		Pagination pagination = new Pagination();
		ArrayList<ProductDto> product = new ArrayList<ProductDto>();
		String brandListToString = filterBean.getArrBrand().toString().toString().substring(1, filterBean.getArrBrand().toString().length() - 1);
		if (filterBean.getCategorym_id() == null && filterBean.getCategorys_id() == null) {	// 전체 상품
			if(filterBean.getArrBrand().size() == 0) { // 브랜드를 선택 안했을 때
				pagination.paginationSetting(filterBean.getPagination().getPage(), filterBean.getPagination().getLength(), 10, productService.countProductAll(filterBean));
				if (filterBean.getSorting().equals("1")) {
					product = productService.selectProductAll(pagination, filterBean);
				}else if(filterBean.getSorting().equals("2")) {
					product = productService.selectLowerProductAll(pagination, filterBean);
				}else if(filterBean.getSorting().equals("3")) {
					product = productService.selectUpperProductAll(pagination, filterBean);
				}else if(filterBean.getSorting().equals("4")) {
					product = productService.selectNewProductAll(pagination, filterBean);
				}
			}else { // 브랜드를 선택 했을 때
				pagination.paginationSetting(filterBean.getPagination().getPage(), filterBean.getPagination().getLength(), 10, productService.countProductBrand(filterBean, brandListToString));
				if (filterBean.getSorting().equals("1")) {
					product = productService.selectProductBrand(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("2")) {
					product = productService.selectLowProductBrand(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("3")) {
					product = productService.selectUpperProductBrand(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("4")) {
					product = productService.selectNewProductBrand(pagination, filterBean, brandListToString);
				}
			}
			
		} else { // 카테고리별 상품
			if(filterBean.getArrBrand().size() == 0) { // 브랜드를 선택 안했을 때
				pagination.paginationSetting(filterBean.getPagination().getPage(), filterBean.getPagination().getLength(), 10, productService.countProductCategory(filterBean));
				if (filterBean.getSorting().equals("1")) {
					product = productService.selectProductCategory(pagination, filterBean);
				}else if(filterBean.getSorting().equals("2")) {
					product = productService.selectLowProductCategory(pagination, filterBean);
				}else if(filterBean.getSorting().equals("3")) {
					product = productService.selectUpperProductCategory(pagination, filterBean);
				}else if(filterBean.getSorting().equals("4")) {
					product = productService.selectNewProductCategory(pagination, filterBean);
				}
			}else { // 브랜드를 선택 했을 때
				pagination.paginationSetting(filterBean.getPagination().getPage(), filterBean.getPagination().getLength(), 10, productService.countProductBrandNCategory(filterBean, brandListToString));
				if (filterBean.getSorting().equals("1")) {
					product = productService.selectProductBrandNCategory(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("2")) {
					product = productService.selectLowProductBrandNCategory(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("3")) {
					product = productService.selectUpperProductBrandNCategory(pagination, filterBean, brandListToString);
				}else if(filterBean.getSorting().equals("4")) {
					product = productService.selectNewProductBrandNCategory(pagination, filterBean, brandListToString);
				}
			}

		}
		map.put("product", product);
		map.put("pagination", pagination);
		return map;
	}

}
