package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.service.CartListService;

@Controller
public class CartListController {
	
	@Autowired
	private CartListService cartListService;
	
	@RequestMapping(value = "cartlist", method = RequestMethod.GET)
	public ModelAndView membercartListMove(CartListBean cartListBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cartlist");
		cartListBean.setUser_email((String)session.getAttribute("email"));
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		// 해당 유저의 장바구니 전체를 출력
		modelAndView.addObject("cartlist", cartListService.selectCartListAll(cartListBean));
		return modelAndView;
	}
	
	@RequestMapping(value = "changeQuantity", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> changeQuantity(@RequestBody CartListBean cartListBean, HttpSession session){
		Map<String, Object> map = new HashMap<String, Object>();
		cartListBean.setUser_email((String)session.getAttribute("email"));
		map.put("isSuccess", cartListService.updatePrdQuantity(cartListBean));
		map.put("quantityNPrice", cartListService.selectQuantityNPrice(cartListBean));
		return map;
	}
	
	@RequestMapping(value = "deleteProduct", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> deleteProduct(@RequestBody CartListBean cartListBean, HttpSession session, HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		cartListBean.setUser_email((String)session.getAttribute("email"));
		map.put("deleteProduct", cartListService.deleteProduct(cartListBean));
		return map;
	}
	
	
	
}
