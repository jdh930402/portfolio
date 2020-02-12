package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.command.FilterBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.ProductDto;

public interface ProductDao {
	/* 전체 상품 출력 START */
	// 모든 브랜드의 제품을 인기순으로 6개씩 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductAll(Pagination pagination, FilterBean filterBean);
	
	// 모든 브랜드의 제품을 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowerProductAll(Pagination pagination, FilterBean filterBean);
	
	// 모든 브랜드의 제품을 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductAll(Pagination pagination, FilterBean filterBean);
	
	// 모든 브랜드의 제품을 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductAll(Pagination pagination, FilterBean filterBean);
	
	
	/* 브랜드별 상품 출력 START */
	// 제품을 브랜드별로 출력 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductBrand(Pagination pagination, FilterBean filterBean, String arrBrand);
	
	// 브랜드별 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrand(Pagination pagination, FilterBean filterBean , String arrBrand);

	// 브랜드별 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrand(Pagination pagination, FilterBean filterBean , String arrBrand);
	
	// 브랜드별 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrand(Pagination pagination, FilterBean filterBean , String arrBrand);
	
	// size_id로 productoption을 검색
	public ProductDto changeToPrdOption(ProductBean productBean);
	
	/* 제품을 카테고리별로 브랜드 선택 안했을 시 출력 */
	// 제품을 카테고리별로 출력 (주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectProductCategory(Pagination pagination, FilterBean filterBean);
	
	// 제품을 카테고리별로 출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductCategory(Pagination pagination, FilterBean filterBean);
	
	// 제품을 카테고리별로 출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductCategory(Pagination pagination, FilterBean filterBean);
	
	// 제품을 카테고리별로 출력 높은 가격순(최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductCategory(Pagination pagination, FilterBean filterBean);
	
	
	/* 제품을 카테고리별로 브랜드 선택 했을 시 출력 */
	// 제품을 카테고리별로 브랜드 선택 했을 때 출력 인기순 (주문수 desc, 이름 ASC, 최신 desc)
	public ArrayList<ProductDto> selectProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	public ArrayList<ProductDto> selectLowProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	public ArrayList<ProductDto> selectUpperProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때 최신순으로 출력 (최신 desc, 주문수 desc)
	public ArrayList<ProductDto> selectNewProductBrandNCategory(Pagination pagination, FilterBean filterBean, String arrBrand);
		
	/* 제품의 count */
	// 상품의 총 개수
	public int countProductAll(FilterBean filterBean);
	
	// 상품의 카테고리별 총 개수(브랜드 선택 안했을 경우)
	public int countProductCategory(FilterBean filterBean);
	
	// 상품의 카테고리별 총 개수(브랜드 선택 했을 경우)
	public int countProductBrandNCategory(FilterBean filterBean, String arrBrand);
	
	// 상품의 브랜드별 총 개수
	public int countProductBrand(FilterBean filterBean, String arrBrand);

	
	/* 상품 상세페이지 */
	// 상품 상세페이지에 상품 정보 출력
	public ProductDto singleProduct(ProductBean productBean);
	
	// 상품별 사이즈와 사이즈별 재고량 출력
	public ArrayList<ProductDto> singleProductOption(String id);
	
	
	/* 상품 등록시 사용 */
	// 상품 등록시 product 테이블에 insert하고 image테이블에 insert하기위해 최근 상품의 아이디를 얻어온다.
	public String recentProduct(); 
	
	// 상품등록시 사용된다.
	public boolean insertProduct(ProductBean productBean);
	
	// 상품옵션인 사이즈를 물품등록 
	public boolean insertPrdOption(ProductBean productBean);

	// 물품 구매시 재고 수량 변경
	public boolean updateAmount(ProductBean productBean);
}
