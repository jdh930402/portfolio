package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.dto.UserDto;
import my.shop.portfolio.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView loginMove() {
		ModelAndView modelAndView = new ModelAndView("login");
		
		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		return modelAndView;
	}
	
	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkLogin(@RequestBody UserBean userBean) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		UserDto user = userService.loginCheck(userBean);
		map.put("user", user);
		return map;
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public ModelAndView loginProcess(UserBean userBean, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		UserDto user = userService.loginCheck(userBean);
		
		if (user != null) {
			String email = user.getEmail();
			String name = user.getName();
			session.setAttribute("email", email);
			session.setAttribute("name", name);
			modelAndView.setViewName("redirect:index");
		} else {
			modelAndView.setViewName("redirect:login");
		}
		return modelAndView;
	}
}
