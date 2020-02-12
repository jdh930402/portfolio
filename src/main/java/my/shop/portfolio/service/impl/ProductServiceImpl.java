package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.FilterBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.ProductDao;
import my.shop.portfolio.dto.ProductDto;
import my.shop.portfolio.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDao;

	@Override
	// 모든 브랜드의 제품을 인기순으로 6개씩 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductAll(Pagination pagination, FilterBean filterBean) {
		return productDao.selectProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowerProductAll(Pagination pagination, FilterBean filterBean) {
		return productDao.selectLowerProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductAll(Pagination pagination, FilterBean filterBean) {
		return productDao.selectUpperProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductAll(Pagination pagination, FilterBean filterBean) {
		return productDao.selectNewProductAll(pagination, filterBean);
	}
	
	@Override
	// 제품을 브랜드별로 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectLowProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectUpperProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectNewProductBrand(pagination, filterBean, arrBrand);
	}
	
	
	@Override
	// 제품을 카테고리별로 출력 (주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectProductCategory(Pagination pagination, FilterBean filterBean) {
		return productDao.selectProductCategory(pagination, filterBean);
	}
	
	@Override
	// 제품을 카테고리별로 출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductCategory(Pagination pagination, FilterBean filterBean) {
		return productDao.selectLowProductCategory(pagination, filterBean);
	}
	
	@Override
	// 제품을 카테고리별로 출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductCategory(Pagination pagination, FilterBean filterBean) {
		return productDao.selectUpperProductCategory(pagination, filterBean);
	}
	
	@Override
	// 제품을 카테고리별로 출력 높은 가격순(최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductCategory(Pagination pagination, FilterBean filterBean) {
		return productDao.selectNewProductCategory(pagination, filterBean);
	}
	
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때 출력 인기순 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectLowProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectUpperProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productDao.selectNewProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	
	@Override
	// size_id로 productoption을 검색
	public ProductDto changeToPrdOption(ProductBean productBean) {
		return productDao.changeToPrdOption(productBean);
	}
	
	@Override
	// 상품의 총 개수
	public int countProductAll(FilterBean filterBean) {
		return productDao.countProductAll(filterBean);
	}
	
	@Override
	// 상품의 카테고리별 총 개수
	public int countProductCategory(FilterBean filterBean) {
		return productDao.countProductCategory(filterBean);
	}
	
	@Override
	// 상품의 카테고리별 총 개수(브랜드 선택 했을 경우)
	public int countProductBrandNCategory(FilterBean filterBean, String arrBrand) {
		return productDao.countProductBrandNCategory(filterBean, arrBrand);
	}
	
	@Override
	// 상품의 브랜드 총 개수
	public int countProductBrand(FilterBean filterBean, String arrBrand) {
		return productDao.countProductBrand(filterBean, arrBrand);
	}
	
	
	@Override
	// 상품 상세페이지에 상품 정보 출력
	public ProductDto singleProduct(ProductBean productBean) {
		return productDao.singleProduct(productBean);
	}
	
	@Override
	// 상품별 사이즈와 사이즈별 재고량 출력
	public ArrayList<ProductDto> singleProductOption(String id) {
		return productDao.singleProductOption(id);
	}
	
	
	@Override
	// 상품 등록시 product 테이블에 insert하고 image테이블에 insert하기위해 최근 상품의 아이디를 얻어온다.
	public String recentProduct() {
		return productDao.recentProduct();
	}
	
	@Override
	// 상품등록시 사용된다.
	public boolean insertProduct(ProductBean productBean) {
		return productDao.insertProduct(productBean);
	}
	
	@Override
	// 상품옵션인 사이즈를 물품등록 
	public boolean insertPrdOption(ProductBean productBean) {
		return productDao.insertPrdOption(productBean);
	}
	
	@Override
	public boolean updateAmount(ProductBean productBean) {
		return productDao.updateAmount(productBean);
	}

}
