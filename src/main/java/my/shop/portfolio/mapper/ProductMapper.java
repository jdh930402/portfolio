package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import my.shop.portfolio.command.FilterBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.ProductDto;

public interface ProductMapper {
	/* 전체 출력 */
	// 모든 브랜드의 제품을 인기순으로 출력 (주문수 desc, 이름 ASC, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY count_purchase DESC, product.name ASC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectProductAll(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);
	
	// 모든 브랜드의 제품을 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price ASC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectLowerProductAll(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);

	// 모든 브랜드의 제품을 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price DESC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectUpperProductAll(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);

	// 모든 브랜드의 제품을 최신순으로 출력 (최신 desc, 주문수 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY regdate DESC, count_purchase DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectNewProductAll(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);
	
	
	/* 브랜드별 출력 */
	// 브랜드별로 제품 출력 (주문수 desc, 이름 ASC, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY count_purchase DESC, product.name ASC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectProductBrand(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 브랜드별 낮은 가격순으로 출력 (낮은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price ASC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectLowProductBrand(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 브랜드별 높은 가격순으로 출력 (높은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price DESC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectUpperProductBrand(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 브랜드별 최신순으로 출력 (최신 desc, 주문수 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY regdate DESC, count_purchase DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectNewProductBrand(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	
	/* 카테고리별 출력
	 * 브랜드 선택안한 경우 */
	// 제품을 카테고리별로 브랜드 선택 안했을 때 출력 인기순 (주문수 desc, 이름 ASC, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY count_purchase DESC, product.name ASC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectProductCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);
	
	// 제품을 카테고리별로 브랜드 선택 안했을 때  출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price ASC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectLowProductCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);

	// 제품을 카테고리별로 브랜드 선택 안했을 때  출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price DESC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectUpperProductCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);

	// 제품을 카테고리별로 브랜드 선택 안했을 때 최신순으로 출력 (최신 desc, 주문수 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY regdate DESC, count_purchase DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectNewProductCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean);

	/* 카테고리별 출력
	 * 브랜드 선택한 경우 */
	// 제품을 카테고리별로 브랜드 선택 했을 때 출력 인기순 (주문수 desc, 이름 ASC, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY count_purchase DESC, product.name ASC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectProductBrandNCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 낮은 가격순(주문수 desc, 낮은 가격 asc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price ASC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectLowProductBrandNCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때  출력 높은 가격순(높은 가격 asc, 주문수 desc, 최신 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY price DESC, count_purchase DESC, regdate DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectUpperProductBrandNCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 제품을 카테고리별로 브랜드 선택 했을 때  최신순으로 출력 (최신 desc, 주문수 desc)
	@Select("SELECT product.id, categorym.name as categorym_name, categorys.name as categorys_name, product.name, product.price, brand.name as brand_name, origin.country as origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, image.name AS image_name, count(purchase.id) as count_purchase "
			+ "FROM product inner JOIN image ON image.product_id = product.id LEFT OUTER JOIN purchase ON product.id = purchase.product_id INNER JOIN categorym ON categorym.id = categorym_id INNER JOIN categorys ON categorys.id = categorys_id INNER JOIN brand ON brand.id = brand_id INNER JOIN origin ON origin.id = origin_id "
			+ "WHERE depth = 0 AND brand_id in ( ${arrBrand} ) AND categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND product.price Between #{filter.lower} AND #{filter.upper} GROUP BY product.id ORDER BY regdate DESC, count_purchase DESC LIMIT #{paging.start}, #{paging.length}")
	public ArrayList<ProductDto> selectNewProductBrandNCategory(@Param("paging") Pagination pagination, @Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	/* pagination시 총 개수를 알기위해 사용 */
	// 상품의 총 개수
	@Select("SELECT COUNT(id) FROM product WHERE product.price Between #{lower} AND #{upper}")
	public int countProductAll(FilterBean filterBean);
	
	// 상품의 브랜드별 총 개수
	@Select("SELECT COUNT(id) FROM product WHERE brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper}")
	public int countProductBrand(@Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	
	// 상품의 카테고리별 총 개수(브랜드 선택 안했을 경우)
	@Select("SELECT COUNT(id) FROM product WHERE categorym_id = #{categorym_id} AND categorys_id = #{categorys_id} AND product.price Between #{lower} AND #{upper}")
	public int countProductCategory(FilterBean filterBean);

	// 상품의 카테고리별 총 개수(브랜드 선택 했을 경우)
	@Select("SELECT COUNT(id) FROM product WHERE categorym_id = #{filter.categorym_id} AND categorys_id = #{filter.categorys_id} AND brand_id in ( ${arrBrand} ) AND product.price Between #{filter.lower} AND #{filter.upper}")
	public int countProductBrandNCategory(@Param("filter") FilterBean filterBean, @Param("arrBrand") String arrBrand);
	


	// 상품 상세페이지에 상품 정보 출력
	@Select("SELECT product.id, categorys.name AS categorys_name, product.name, product.price, brand.name AS brand_name, origin.country AS origin_country FROM product, categorys, brand, origin where categorys_id = categorys.id AND brand_id = brand.id AND origin_id = origin.id AND product.id = #{id}")
	public ProductDto singleProduct(ProductBean productBean);
	
	// 상품별 사이즈와 사이즈별 재고량 출력
	@Select("SELECT size_id, size, amount FROM productoption, size WHERE size_id = size.id AND product_id = #{id} ORDER BY size ASC")
	public ArrayList<ProductDto> singleProductOption(String id);

	// size_id로 productoption을 검색
	@Select("SELECT productoption.id, size FROM productoption, size WHERE size_id = size.id AND product_id = #{product_id} AND size_id = #{size_id}")
	public ProductDto changeToPrdOption(ProductBean productBean);
	
	
	// 상품 등록시 product 테이블에 insert하고 image테이블에 insert하기위해 최근 상품의 아이디를 얻어온다.
	@Select("SELECT id FROM product ORDER BY regdate DESC LIMIT 1")
	public String recentProduct(); 
	
	// 상품등록시 사용된다.
	@Insert("INSERT INTO product(categorym_id, categorys_id, name, price, brand_id, origin_id) Values(#{categorym_id}, #{categorys_id}, #{name}, #{price}, #{brand_id}, #{origin_id})")
	public boolean insertProduct(ProductBean productBean);
	
	// 상품옵션인 사이즈를 물품등록 
	@Insert ("INSERT INTO productoption(product_id, size_id, amount) values(#{product_id}, #{size_id}, #{amount})")
	public boolean insertPrdOption(ProductBean productBean);
	
	// 물품 구매시 재고 수량 변경
	@Update("UPDATE productoption SET amount = amount - #{amount} WHERE id = #{id} AND amount >= 0")
	public boolean updateAmount(ProductBean productBean);
	
}
