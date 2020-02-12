package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.dao.CartListDao;
import my.shop.portfolio.dto.CartListDto;
import my.shop.portfolio.service.CartListService;

@Service("cartListService")
public class CartListServiceImpl implements CartListService{

	@Autowired
	private CartListDao cartListDao;
	
	@Override
	// 장바구니 상품 전체 출력
	public ArrayList<CartListDto> selectCartListAll(CartListBean cartListBean) {
		return cartListDao.selectCartListAll(cartListBean);
	}
	
	@Override
	// 장바구니에 중복된 상품이 있는지 확인 
	public int countCheckPrd(CartListBean cartListBean) {
		return cartListDao.countCheckPrd(cartListBean);
	}
	
	@Override
	// 장바구니에서 수량을 변경 한 후 변경한 해당 상품의 가격을 측정하기위해 사용
	public CartListDto selectQuantityNPrice(CartListBean cartListBean) {
		return cartListDao.selectQuantityNPrice(cartListBean);
	}
	
	@Override
	// 장바구니에 품목 추가
	public int insertCartList(CartListBean cartListBean) {
		return cartListDao.insertCartList(cartListBean);
	}
	
	@Override
	// 사이즈가 일치하는 상품의 장바구니의 수량을 변경
	public int updateExistPrdQuantity(CartListBean cartListBean) {
		return cartListDao.updateExistPrdQuantity(cartListBean);
	}
	
	@Override
	// 장바구니에서 개수를 변경하는 경우
	public int updatePrdQuantity(CartListBean cartListBean) {
		return cartListDao.updatePrdQuantity(cartListBean);
	}
	
	@Override
	// 장바구니에서 물품 삭제
	public boolean deleteProduct(CartListBean cartListBean) {
		return cartListDao.deleteProduct(cartListBean);
	}
}
