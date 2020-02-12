package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.dto.CartListDto;

public interface CartListMapper {
	
	// 카트리스트에 전체 상품 검색
	@Select("SELECT cartlist.id, cartlist.product_id, categorym.name AS categorym_name, categorys.name AS categorys_name, product.name, price, brand.name AS brand_name, origin.country AS origin_country, DATE_FORMAT(product.regdate, '%Y-%m-%d') AS regdate, cartlist.productoption_id, size, quantity, image.name AS image_name FROM cartlist, product, categorym, categorys, brand, origin, productoption, size,image WHERE cartlist.product_id = product.id AND categorym_id = categorym.id AND categorys_id = categorys.id AND brand.id = brand_id AND origin.id = origin_id AND productoption.id = cartlist.productoption_id AND size.id = productoption.size_id AND cartlist.product_id = image.product_id AND image.depth = 0 AND user_email = #{user_email}")
	public ArrayList<CartListDto> selectCartListAll(CartListBean cartListBean);
	
	// 장바구니에 중복된 상품이 있는지 확인 
	@Select("SELECT count(cartlist.id) FROM cartlist, productoption WHERE productoption_id = productoption.id AND cartlist.product_id = #{product_id} AND productoption.size_id = #{size_id} AND user_email = #{user_email}")
	public int countCheckPrd(CartListBean cartListBean);
	
	// 장바구니에서 수량을 변경 한 후 변경한 해당 상품의 가격을 측정하기위해 사용
	@Select("SELECT quantity, price FROM cartlist, product WHERE product_id = product.id AND cartlist.id = #{id} AND user_email = #{user_email}")
	public CartListDto selectQuantityNPrice(CartListBean cartListBean);
	
	
	// 장바구니에 품목 추가
	@Insert("INSERT INTO cartlist(user_email, product_id, productoption_id, quantity) VALUES ( #{user_email}, #{product_id}, (SELECT id from productoption where size_id = #{size_id}), #{quantity})")	
	public int insertCartList(CartListBean cartListBean);
	
	// 기존에 상품이 존재할 경우 추가되는 만큼 개수를 합한다
	@Update("UPDATE cartlist SET quantity = quantity + #{quantity} WHERE product_id = #{product_id} AND user_email = #{user_email} AND productoption_id = (SELECT id FROM productoption WHERE size_id = #{size_id})")
	public int updateExistPrdQuantity(CartListBean cartListBean);

	// 장바구니에서 개수를 변경하는 경우
	@Update("UPDATE cartlist SET quantity = #{quantity} WHERE id = #{id} AND user_email = #{user_email}")
	public int updatePrdQuantity(CartListBean cartListBean);
	
	// 장바구니에서 물품 삭제
	@Delete("DELETE FROM cartlist WHERE id = #{id} AND user_email = #{user_email}")
	public boolean deleteProduct(CartListBean cartListBean);

	
}
