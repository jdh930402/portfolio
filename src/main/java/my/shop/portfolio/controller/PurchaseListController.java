package my.shop.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.common.NavbarData;

@Controller
public class PurchaseListController {

	// 주문목록/배송조회 페이지 이동
	@RequestMapping(value = "purchase/list", method = RequestMethod.GET)
	public ModelAndView memberpurchaseListMove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("purchaselist");

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		return modelAndView;
	}
}
