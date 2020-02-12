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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.service.UserService;

@Controller
public class UserModifyController {

	@Autowired
	private UserService userService;

	// 유저정보 확인 및 수정페이지 이동
	@RequestMapping(value = "login/userModify", method = RequestMethod.GET)
	public ModelAndView memberuserModifyMove(@RequestParam(value = "check", defaultValue = "n") String check, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("usermodify");

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		return modelAndView;
	}

	// 유저 재확인 ajax
	@RequestMapping(value = "login/modifyCheck", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> modifyCheck(@RequestBody UserBean userBean, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		userBean.setEmail((String) session.getAttribute("email"));
		map.put("user", userService.loginCheck(userBean));
		return map;
	}

	// 유저 재확인 통과시 유저 정보 출력페이지 이동
	@RequestMapping(value = "login/userModify", method = RequestMethod.POST)
	public ModelAndView memberuserModifyProcess(HttpSession session, @RequestParam("check") String check, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("usermodify");
		String email = ((String) session.getAttribute("email"));

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		// 재확인시 넘겨받은 check값과 user정보를 전달
		modelAndView.addObject("check", check);
		modelAndView.addObject("user", userService.userData(email));
		return modelAndView;
	}

	// 아이디 중복 확인 ajax
	@RequestMapping(value = "login/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkEmail(@RequestBody String email) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("email", userService.isExistEmail(email));
		return map;
	}

	@RequestMapping(value = "login/modifyEmail", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> modifyEmail(@RequestBody UserBean userBean, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		userBean.setEmail((String) session.getAttribute("email"));
		boolean isSuccess = userService.updateEmail(userBean);
		
		if (isSuccess) {
			String email = userBean.getNewEmail();
			session.setAttribute("email", email);
			map.put("email", email);
		}
		return map;
	}
	
	@RequestMapping(value = "login/checkPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkPassword(@RequestBody UserBean userBean, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		userBean.setEmail((String) session.getAttribute("email"));
		boolean isPassword = userService.isPasswordMatch(userBean);
		if(isPassword) {
			map.put("password", isPassword);
		}
		return map;
	}
	
	@RequestMapping(value = "login/modifyPassword", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> modifyPassword(@RequestBody UserBean userBean, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		userBean.setEmail((String) session.getAttribute("email"));
		boolean isSuccess = userService.updatePassword(userBean);
		if(isSuccess) {
			map.put("password", isSuccess);
		}
		return map;
	}
	
	@RequestMapping(value = "login/modifyTel", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> modifyTel(@RequestBody UserBean userBean, HttpSession session) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		userBean.setEmail((String) session.getAttribute("email"));
		boolean isSuccess = userService.updateTel(userBean);
		if(isSuccess) {
			map.put("tel", isSuccess);
		}
		return map;
	}
	
	
	@RequestMapping(value = "login/withdrawal", method = RequestMethod.POST)
	public String memberwithdrawal(UserBean userBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		userBean.setEmail((String)session.getAttribute("email"));
		boolean isSuccess = userService.withdrawal(userBean);
		if(isSuccess) {
			session.removeAttribute("email");
		}
		return "redirect:/index";
	}
}