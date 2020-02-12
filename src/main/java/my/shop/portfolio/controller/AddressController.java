package my.shop.portfolio.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.dto.AddressDto;
import my.shop.portfolio.service.AddressService;

@Controller
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@RequestMapping(value = "addressManage", method = RequestMethod.GET)
	public ModelAndView memberaddressMove(HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("address");

		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		// 해당 유저의 주소를 반환한다.
		String email = (String) session.getAttribute("email");
		modelAndView.addObject("address", addressService.UserAddress(email));
		return modelAndView;
	}
	
	@RequestMapping(value = "addressManage/checkAddrLength", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkAddrLength(HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();
		String email = (String)session.getAttribute("email");
		map.put("addrLength", addressService.UserAddress(email).size());
		return map;
	}
	
	// 유저의 주소를 추가한다.
	@RequestMapping(value = "addressManage/add", method = RequestMethod.GET)
	public ModelAndView memberaddressAddMove(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("addressadd");
		
		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "addressManage/add", method = RequestMethod.POST)
	public ModelAndView memberaddressAddProcess(AddressBean addressBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		String email = (String)session.getAttribute("email");
		// 주소를 추가한다.
		
		addressBean.setEmail(email);
		if(addressBean.getDepth() == null) {
			addressBean.setDepth("1");
		}else {
			// 기본배송지 존재 여부 검색
			if(addressService.countDepth(email) > 0) {
				// 존재 할 경우, 이전에 존재하던 기본배송지를 해제하고 새로운 배송지를 기본배송지로 변경한다.
				addressService.updateDepth(email);
			}
		}
		boolean isSuccess = addressService.insertAddress(addressBean);
		if(isSuccess) {
			modelAndView.setViewName("redirect:/addressManage");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "addressManage/modify", method = RequestMethod.GET)
	public ModelAndView memberaddressUpdateMove(AddressBean addressBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("addressmodify");
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		// 유저의 배송지 정보를 전달한다.
		addressBean.setEmail((String)session.getAttribute("email"));
		AddressDto address = addressService.updateAddressData(addressBean);
		modelAndView.addObject("address", address);
		
		return modelAndView;
	}
	
	// 수정버튼 클릭시
	@RequestMapping(value = "addressManage/modify", method = RequestMethod.POST)
	public String memberaddressUpdateProcess(AddressBean addressBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		String email = (String)session.getAttribute("email");
		addressBean.setEmail(email);
		
		if(addressBean.getDepth() == null) {
			addressBean.setDepth("1");
		}else {
			// 기본배송지 존재 여부 검색
			if(addressService.countDepth(email) > 0) {
				// 존재 할 경우, 이전에 존재하던 기본배송지를 해제하고 새로운 배송지를 기본배송지로 변경한다.
				addressService.updateDepth(email);
			}
		}
		
		addressService.updateAddress(addressBean);
		return "redirect:/addressManage";
	}
	
	// 제거버튼 클릭시
	@RequestMapping(value = "addressManage/delete", method = RequestMethod.GET)
	public String memberaddressDeleteProcess(AddressBean addressBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		String email = (String)session.getAttribute("email");
		addressBean.setEmail(email);
		addressService.deleteAddress(addressBean);
		return "redirect:/addressManage";
	}
}

