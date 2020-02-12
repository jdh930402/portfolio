package my.shop.portfolio.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String LogoutProcess(HttpSession session) {
		if(session.getAttribute("email") != null) {
			session.removeAttribute("email");
		}else if(session.getAttribute("id") != null) {
			session.removeAttribute("id");
		}
		return "redirect:index";
	}
	
}
