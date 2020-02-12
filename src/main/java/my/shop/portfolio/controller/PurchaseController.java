package my.shop.portfolio.controller;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.command.PurchaseBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.dto.CartListDto;
import my.shop.portfolio.service.AddressService;
import my.shop.portfolio.service.CartListService;
import my.shop.portfolio.service.ProductService;
import my.shop.portfolio.service.PurchaseService;

@Controller
public class PurchaseController {
	
	@Autowired
	private AddressService addressService;
	
	@Autowired 
	private CartListService cartListService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "purchaseok", method = RequestMethod.GET)
	public ModelAndView memberpurchaseMove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("purchaseok");
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "purchaseok", method = RequestMethod.POST)
	public ModelAndView memberaddPurchase(AddressBean addressBean, PurchaseBean purchaseBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("redirect:purchaseok");
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		String email = (String)session.getAttribute("email");		
		addressBean.setEmail(email);
		// 주소를 검색하고 있을경우 주소id를 구매목록 DB에 ID값을 저장하고, 없을 경우 주소를 저장하고 구매목록 DB에 ID값을 저장
		String address_id = addressService.selectPurchaseAddress(addressBean);
		if(address_id == null) {
			addressBean.setDepth("2");
			addressService.insertAddress(addressBean);
			address_id = addressService.selectPurchaseAddress(addressBean);
		}
		
		// 체크아웃의 상품을 구매목록에 저장하기
		Calendar calendar = Calendar.getInstance();
		StringBuffer purchaseNumber = new StringBuffer();
		CartListBean cartListBean = new CartListBean();
		ProductBean productBean = new ProductBean();
		
		purchaseNumber.append(calendar.get(Calendar.YEAR));
		purchaseNumber.append(calendar.get(Calendar.MONTH) + 1);
		purchaseNumber.append(calendar.get(Calendar.DATE));
		purchaseNumber.append(calendar.getTimeInMillis());
		purchaseBean.setPurchaseNumber(purchaseNumber.toString());
		purchaseBean.setUser_email(email);
		purchaseBean.setAddress_id(address_id);
		if(purchaseBean.getCartlist().equals("n")) { // 상품 즉시 구매일 경우
			purchaseService.insertPurchaseList(purchaseBean);
			
			// 재고 수량 변경
			productBean.setAmount(purchaseBean.getQuantity());
			productService.updateAmount(productBean);
			
			
		}else if(purchaseBean.getCartlist().equals("y")){ // 카트리스트의 상품목록 구매일 경우
			// 카트에 있는 아이템을 불러온다.
			cartListBean.setUser_email(email);
			ArrayList<CartListDto> cartList = cartListService.selectCartListAll(cartListBean);
			int price = 0;
			for( CartListDto cartListDto : cartList) {
				// 장바구니의 물품을 구매목록에 옮기기
				purchaseBean.setProduct_id(cartListDto.getProduct_id());
				purchaseBean.setProductoption_id(cartListDto.getProductoption_id());
				purchaseBean.setQuantity(cartListDto.getQuantity());
				price = Integer.parseInt(cartListDto.getPrice()) * Integer.parseInt(cartListDto.getQuantity());
				purchaseBean.setPrice(String.valueOf(price));
				purchaseService.insertPurchaseList(purchaseBean);
				
				// 장바구니에 있던 기존 물품 삭제
				cartListBean.setId(cartListDto.getId());
				cartListService.deleteProduct(cartListBean);
				
				// 재고 수량 변경
				productBean.setId(purchaseBean.getProductoption_id());
				productBean.setAmount(cartListDto.getQuantity());
				productService.updateAmount(productBean);
			}
			
		}
		return modelAndView;
	}
}
