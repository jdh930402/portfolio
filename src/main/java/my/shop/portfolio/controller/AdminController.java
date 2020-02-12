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

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dto.AdminDto;
import my.shop.portfolio.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@RequestMapping(value = "admin/login", method = RequestMethod.GET)
	public String LoginMove() {
		return "admin/login";
	}
	
	@RequestMapping(value = "admin/checkLogin", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkLogin(@RequestBody AdminBean adminBean) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		AdminDto admin = adminService.loginAdmin(adminBean);
		map.put("admin", admin);
		return map;		
	}
	
	@RequestMapping(value = "admin/login", method = RequestMethod.POST)
	public ModelAndView LoginProcess(AdminBean adminBean, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("redirect:/index");
		AdminDto admin = adminService.loginAdmin(adminBean);
		if(admin != null) {
			session.setAttribute("id", admin.getId());
		}
		return modelAndView;
	}
}
