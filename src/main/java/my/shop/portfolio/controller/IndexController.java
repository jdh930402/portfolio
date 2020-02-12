package my.shop.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.common.NavbarData;

@Controller
public class IndexController {
	@RequestMapping(value = "index")
	public ModelAndView indexMove() {
		ModelAndView modelAndView = new ModelAndView("index");

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		return modelAndView;
	}
}
