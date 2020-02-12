package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public ModelAndView registerMove() {
		ModelAndView modelAndView = new ModelAndView("register");

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		return modelAndView;
	}

	// 아이디 중복 확인 ajax
	@RequestMapping(value = "checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkEmail(@RequestBody String email) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("email", userService.isExistEmail(email));
		return map;
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ModelAndView registerProcess(UserBean userBean) {
		ModelAndView modelAndView = new ModelAndView();

		// 메뉴의 데이터를 singleton 객체로 생성
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		// 입력 데이터를 받아서 db에 저장
		boolean isSuccess = userService.userRegiser(userBean);
		if (isSuccess) {
			modelAndView.setViewName("redirect:registerok");
		} else {
			modelAndView.setViewName("redirect:error");
		}
		return modelAndView;
	}

	@RequestMapping(value = "registerok", method = RequestMethod.GET)
	public ModelAndView registerOkProcess() {
		ModelAndView modelAndView = new ModelAndView("registerok");

		// 메뉴의 데이터를 singleton 객체로 생성
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		return modelAndView;
	}

}