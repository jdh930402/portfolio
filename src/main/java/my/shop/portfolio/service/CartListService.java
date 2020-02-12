package my.shop.portfolio.service;

import java.util.ArrayList;

import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.dto.CartListDto;

public interface CartListService {
	
	// 카트리스트에 전체 상품 검색
	public ArrayList<CartListDto> selectCartListAll(CartListBean cartListBean);
	
	// 장바구니에 중복된 상품이 있는지 확인 
	public int countCheckPrd(CartListBean cartListBean);
	
	// 장바구니에서 수량을 변경 한 후 변경한 해당 상품의 가격을 측정하기위해 사용
	public CartListDto selectQuantityNPrice(CartListBean cartListBean);
	
	// 장바구니에 품목 추가
	public int insertCartList(CartListBean cartListBean);
	
	// 사이즈가 일치하는 상품의 장바구니의 수량을 변경
	public int updateExistPrdQuantity(CartListBean cartListBean);
	
	// 장바구니에서 개수를 변경하는 경우
	public int updatePrdQuantity(CartListBean cartListBean);
	
	// 장바구니에서 물품 삭제
	public boolean deleteProduct(CartListBean cartListBean);
}
