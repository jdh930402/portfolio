package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.dto.ProductDto;
import my.shop.portfolio.service.AddressService;
import my.shop.portfolio.service.CartListService;
import my.shop.portfolio.service.ProductService;

@Controller
public class CheckoutContorller {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private CartListService cartListService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "checkout", method = RequestMethod.GET)
	public ModelAndView membercheckoutMove(AddressBean addressBean, CartListBean cartListBean, ProductBean productBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		addressBean.setEmail((String) session.getAttribute("email"));
		cartListBean.setUser_email((String) session.getAttribute("email"));

		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		// 기본 배송지를 가져온다.
		modelAndView.addObject("defaultAddr", addressService.selectDefaultAddress(addressBean));
		
		if(cartListBean.getProduct_id() == null) { // 장바구니에서 checkout 접근
			modelAndView.addObject("cartlist", cartListService.selectCartListAll(cartListBean));
		}else { // 상품상세 내역에서 즉시구매시
			productBean.setId(cartListBean.getProduct_id());
			ProductDto prdOption = productService.changeToPrdOption(productBean);
			ProductDto product = productService.singleProduct(productBean);
			product.setQuantity("1");
			
			modelAndView.addObject("product", product);
			modelAndView.addObject("prdOption", prdOption);
		}
		return modelAndView;
	}

	@RequestMapping(value = "checkaddr", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> checkAddr(AddressBean addressBean, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		addressBean.setEmail((String) session.getAttribute("email"));
		map.put("defaultAddr", addressService.selectDefaultAddress(addressBean));
		return map;
	}
	
	@RequestMapping(value = "addropener", method = RequestMethod.GET)
	public ModelAndView memberaddrOpenerMove(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("opener/addrlist");
		
		String email = (String) session.getAttribute("email");
		modelAndView.addObject("address", addressService.UserAddress(email));
		return modelAndView;
	}
}
