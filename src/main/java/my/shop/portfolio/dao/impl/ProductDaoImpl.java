package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.FilterBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.ProductDao;
import my.shop.portfolio.dto.ProductDto;
import my.shop.portfolio.mapper.ProductMapper;

@Repository("productDao")
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	// 모든 브랜드의 제품을 인기순으로 6개씩 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductAll(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowerProductAll(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectLowerProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductAll(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectUpperProductAll(pagination, filterBean);
	}
	
	@Override
	// 모든 브랜드의 제품을 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductAll(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectNewProductAll(pagination, filterBean);
	}
	
	
	@Override
	// 제품을 브랜드별로 출력 (주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectLowProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 높은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectUpperProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 브랜드별 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectNewProductBrand(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductCategory(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectProductCategory(pagination, filterBean);
	}

	@Override
	// 제품을 카테고리별로 출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductCategory(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectLowProductCategory(pagination, filterBean);
	}
	
	@Override
	// 제품을 카테고리별로 출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductCategory(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectUpperProductCategory(pagination, filterBean);
	}
	
	@Override
	// 제품을 카테고리별로 출력 높은 가격순(최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductCategory(Pagination pagination, FilterBean filterBean) {
		return productMapper.selectNewProductCategory(pagination, filterBean);
	}
	
	
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때 출력 인기순 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectLowProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectUpperProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// 제품을 카테고리별로 브랜드 선택 했을 때 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand) {
		return productMapper.selectNewProductBrandNCategory(pagination, filterBean, arrBrand);
	}
	
	@Override
	// size_id로 productoption을 검색
	public ProductDto changeToPrdOption(ProductBean productBean) {
		return productMapper.changeToPrdOption(productBean);
	}
	
	@Override
	// 상품의 총 개수
	public int countProductAll(FilterBean filterBean) {
		return productMapper.countProductAll(filterBean);
	}
	
	@Override
	// 상품의 카테고리별 총 개수(브랜드 선택 안했을 경우)
	public int countProductCategory(FilterBean filterBean) {
		return productMapper.countProductCategory(filterBean);
	}
	
	@Override
	// 상품의 카테고리별 총 개수(브랜드 선택 했을 경우)
	public int countProductBrandNCategory(FilterBean filterBean, String arrBrand) {
		return productMapper.countProductBrandNCategory(filterBean, arrBrand);
	}
	
	@Override
	// 상품의 브랜드별 총 개수
	public int countProductBrand(FilterBean filterBean, String arrBrand) {
		return productMapper.countProductBrand(filterBean, arrBrand);
	}
	
	
	
	
	@Override
	// 상품 상세페이지에 상품 정보 출력
	public ProductDto singleProduct(ProductBean productBean) {
		return productMapper.singleProduct(productBean);
	}
	
	@Override
	// 상품별 사이즈와 사이즈별 재고량 출력
	public ArrayList<ProductDto> singleProductOption(String id) {
		return productMapper.singleProductOption(id);
	}
	
	
	
	@Override
	// 상품 등록시 product 테이블에 insert하고 image테이블에 insert하기위해 최근 상품의 아이디를 얻어온다.
	public String recentProduct() {
		return productMapper.recentProduct();
	}
	
	@Override
	// 상품등록시 사용된다.
	public boolean insertProduct(ProductBean productBean) {
		return productMapper.insertProduct(productBean);
	}
	
	@Override
	// 상품옵션인 사이즈를 물품등록 
	public boolean insertPrdOption(ProductBean productBean) {
		return productMapper.insertPrdOption(productBean);
	}
	
	@Override
	// 물품 구매시 재고 수량 변경
	public boolean updateAmount(ProductBean productBean) {
		return productMapper.updateAmount(productBean);
	}
	
}
