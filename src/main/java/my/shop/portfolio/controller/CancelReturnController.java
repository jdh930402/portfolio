package my.shop.portfolio.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.common.NavbarData;

@Controller
public class CancelReturnController {
	// 취소/반품내역 페이지 이동
	@RequestMapping(value = "cancelreturn/list", method = RequestMethod.GET)
	public ModelAndView membercancelReturnListMove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("cancelreturnlist");

		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		return modelAndView;
	}
}
