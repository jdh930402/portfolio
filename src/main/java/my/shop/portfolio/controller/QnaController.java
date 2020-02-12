package my.shop.portfolio.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.QnaBean;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.QnaDto;
import my.shop.portfolio.service.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value = "qnawrite", method = RequestMethod.GET)
	public ModelAndView memberqnaWriteMove(QnaBean qnaBean , Pagination pagination, HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("qnawrite");
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		modelAndView.addObject("product_id", qnaBean.getProduct_id());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "qnawrite", method = RequestMethod.POST)
	public String memberqnaWrite(QnaBean qnaBean, HttpSession session, HttpServletRequest request,HttpServletResponse response) {
		qnaBean.setUser_email((String)session.getAttribute("email"));
		if(qnaService.selectLastGnum() == null) {
			// qna에 등록된 글이 없을 경우
			qnaBean.setGnum(0);
		}else {
			// qna에 등록된 글이 있는 경우
			qnaBean.setGnum(Integer.parseInt(qnaService.selectLastGnum()) + 1) ;
		}
		qnaService.insertQna(qnaBean);
		return "redirect:singleproduct?id=" + qnaBean.getProduct_id();
	}
	
	@RequestMapping(value = "qnamanager", method = RequestMethod.GET)
	public ModelAndView qnaManagerMove(Pagination pagination) {
		ModelAndView modelAndView = new ModelAndView("admin/qnamanager");
		System.out.println(pagination.getPage());
		pagination.paginationSetting(pagination.getPage(), 10, 10, qnaService.countQnaAll());
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		ArrayList<QnaDto> qnalist = qnaService.selectQnaAll(pagination);
		ArrayList<QnaDto> qna = new ArrayList<QnaDto>();
		for(QnaDto qnaDto : qnalist) {
			qnaDto.setContent(qnaDto.getContent().replace("\r\n", "<br>"));
			qna.add(qnaDto);
		}
		
		modelAndView.addObject("qna", qna);
		
		modelAndView.addObject("pagination", pagination);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "qnamanager/qnaanswer", method = RequestMethod.GET)
	public ModelAndView qnaRead(QnaBean qnaBean, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin/qnaanswer");
		if(session.getAttribute("id") == null) {
			modelAndView = new ModelAndView("admin/qnaanswer");
			return modelAndView;
		}
		
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());
		
		QnaDto qnaDto = qnaService.selectQnaOne(qnaBean);
		qnaDto.setContent(qnaDto.getContent().replace("\r\n", "<br>"));
		
		modelAndView.addObject("qna", qnaDto);
		modelAndView.addObject("gnum", qnaBean.getGnum());
		modelAndView.addObject("answer", qnaService.selectAnswerOne(qnaBean));
		
		return modelAndView;
	}
	
	@RequestMapping(value = "qnamanager/qnaanswer", method = RequestMethod.POST)
	public String qnaAnswer(QnaBean qnaBean) {
		if(qnaBean.getIs_answer().equals("0")) {
			// qna 답변을 등록
			qnaService.insertAnswer(qnaBean);
		}else {
			// qna 답변을 업데이트
			qnaService.updateAnswer(qnaBean);
		}
		
		//답변 대기에서 답변 완료로 변경
		qnaBean.setIs_answer("1");
		qnaService.updateAnswerOk(qnaBean);
		return "redirect:/qnamanager";
	}
	
	@RequestMapping(value = "qnamanager/qnaexist", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> qnaExist(@RequestBody QnaBean qnaBean){
		Map<Object, Object> map = new HashMap<Object, Object>();
		// qna is_answer을 이용하여 답변 여부를 판별
		map.put("qna", qnaService.selectQnaOne(qnaBean));
		return map;
	}
	
	@RequestMapping(value = "qnamanager/deleteanswer", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> deleteAnswer(@RequestBody QnaBean qnaBean){
		Map<Object, Object> map = new HashMap<Object, Object>();
		
		// qna is_answer을 이용하여 답변 여부를 판별
		map.put("qna", qnaService.deleteAnswer(qnaBean));
		
		qnaBean.setIs_answer("0");
		qnaService.updateAnswerOk(qnaBean);
		
		return map;
	}
		
}
