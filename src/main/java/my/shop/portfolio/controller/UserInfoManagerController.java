package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.service.AdminService;
import my.shop.portfolio.service.UserService;

@Controller
public class UserInfoManagerController {
	
	@Autowired
	private UserService userService; 
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "userInfoManager", method = RequestMethod.GET)
	public ModelAndView userInfoMove(Pagination pagination) {
		ModelAndView modelAndView = new ModelAndView("admin/userinfomanager");
		
		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		// 유저정보를 페이지네이션 처리한 후 출력
		pagination.paginationSetting(pagination.getPage(), 10, 10, userService.countUserAll());
		modelAndView.addObject("userInfoCount", userService.countUserAll());
		modelAndView.addObject("userInfo", userService.selectUserAll(pagination));
		modelAndView.addObject("pagination", pagination);
		return modelAndView;
	}
	
	@RequestMapping(value = "checkAdmin", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkAdmin(@RequestBody AdminBean adminBean, HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		adminBean.setId((String)session.getAttribute("id"));
		map.put("admin",adminService.loginAdmin(adminBean));
		return map;
	}
	
	@RequestMapping(value = "kickOut",  method = RequestMethod.POST)
	public String kickOut(@RequestParam("email")String email){
		userService.updateAdminKickOut(email);
		return "redirect:userInfoManager";
	}
	
	
}
