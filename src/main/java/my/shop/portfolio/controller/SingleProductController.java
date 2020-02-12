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

import my.shop.portfolio.command.CartListBean;
import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.service.CartListService;
import my.shop.portfolio.service.ImageService;
import my.shop.portfolio.service.ProductService;
import my.shop.portfolio.service.QnaService;

@Controller
public class SingleProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private CartListService cartListService;
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "singleproduct", method = RequestMethod.GET)
	public ModelAndView productDetailMove(Pagination pagination, ProductBean productBean) {
		ModelAndView modelAndView = new ModelAndView("singleproduct");
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		// 상품의 정보를 출력
		modelAndView.addObject("product", productService.singleProduct(productBean));
		modelAndView.addObject("image", imageService.singleProductImage(productBean.getId()));
		modelAndView.addObject("option", productService.singleProductOption(productBean.getId()));
		
		// Q&A를 출력
		Pagination qnaPagination = new Pagination();
		QnaBean qnaBean = new QnaBean();
		qnaBean.setProduct_id(productBean.getId());
		
		qnaPagination.paginationSetting(qnaPagination.getPage(), 10, 10, qnaService.countPrdQnaAll(qnaBean));
		modelAndView.addObject("qna", qnaService.selectPrdQnaAll(qnaPagination, qnaBean));
		modelAndView.addObject("qnaPagination", qnaPagination);
		modelAndView.addObject("qnaAllLength", qnaService.countQnaAll());
		return modelAndView;
	}
	
	@RequestMapping(value = "addcart", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> addToCart(@RequestBody CartListBean cartListBean, HttpSession session){
		Map<Object, Object> map = new HashMap<Object, Object>();

		cartListBean.setUser_email((String)session.getAttribute("email"));
		int isSuccess;
		// 카트에 기존 상품이 있는지 검사한다.
		if(cartListService.countCheckPrd(cartListBean) > 0) {
			// 기존 상품이 있는 경우
			isSuccess = cartListService.updateExistPrdQuantity(cartListBean);
		}else {
			// 기존 상품이 없는 경우
			isSuccess = cartListService.insertCartList(cartListBean);
		}
		map.put("isSuccess", isSuccess);
		return map;
	}
	
	//qna 페이징 Ajax
	@RequestMapping(value = "qnalist", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> qnaPaging(@RequestBody QnaBean qnaBean){
		Map<Object, Object> map = new HashMap<Object, Object>();
		qnaBean.getPagination().paginationSetting(qnaBean.getPagination().getPage(), 10, 10, qnaService.countQnaAll());
		map.put("qna", qnaService.selectPrdQnaAll(qnaBean.getPagination(), qnaBean));
		map.put("pagination", qnaBean.getPagination());
		map.put("qnaAllLength", qnaService.countQnaAll());
		return map;
	}
	
	@RequestMapping(value = "qnaanswer", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> qnaAnswer(@RequestBody QnaBean qnaBean){
		System.out.println(qnaBean.getGnum());
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("qna", qnaService.selectQnaOne(qnaBean));
		map.put("answer", qnaService.selectAnswerOne(qnaBean));
		return map;
	}
}
