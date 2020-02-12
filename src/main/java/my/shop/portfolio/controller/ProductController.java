package my.shop.portfolio.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.command.SizeBean;
import my.shop.portfolio.common.FileRename;
import my.shop.portfolio.common.NavbarData;
import my.shop.portfolio.dto.CategoryDto;
import my.shop.portfolio.dto.SizeDto;
import my.shop.portfolio.service.CategoryService;
import my.shop.portfolio.service.ImageService;
import my.shop.portfolio.service.OriginService;
import my.shop.portfolio.service.ProductService;
import my.shop.portfolio.service.SizeService;

@Controller
public class ProductController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private OriginService originService;

	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "product/register", method = RequestMethod.GET)
	public ModelAndView registerMove() {
		ModelAndView modelAndView = new ModelAndView("admin/prdregister");

		// 메뉴의 데이터를 singleton 객체로 생성한다.
		NavbarData navbarData = NavbarData.getInstance();
		modelAndView.addObject("brand", navbarData.getBrand());
		modelAndView.addObject("categorym", navbarData.getCategoryM());
		modelAndView.addObject("categorys", navbarData.getCategoryS());

		// 원산지를 출력
		modelAndView.addObject("origin", originService.originAll());
		modelAndView.addObject("size", sizeService.sizeAll());

		return modelAndView;
	}

	@RequestMapping(value = "checkCategoryM", method = RequestMethod.GET)
	@ResponseBody
	public Map<Object, Object> checkCategoryM(@RequestParam("categorym_num") String categorym_num) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		ArrayList<CategoryDto> categorys = categoryService.categorysCluster(categorym_num);
		map.put("categorys", categorys);
		return map;
	}

	@RequestMapping(value = "checkSize", method = RequestMethod.POST)
	@ResponseBody
	public Map<Object, Object> checkSize(@RequestBody SizeBean sizeBean) {
		Map<Object, Object> map = new HashMap<Object, Object>();
		if (sizeBean.getArrSize().size() == 0) {
			ArrayList<SizeDto> size = sizeService.sizeAll();
			map.put("size", size);
		} else {
			String idListToString = sizeBean.getArrSize().toString().toString().substring(1,
					sizeBean.getArrSize().toString().length() - 1);
			ArrayList<SizeDto> size = sizeService.selectArrSize(idListToString);
			map.put("size", size);
		}
		return map;
	}

	@RequestMapping(value = "product/register", method = RequestMethod.POST)
	public ModelAndView registerProcess(@RequestParam("main") MultipartFile mainImg,
			MultipartHttpServletRequest mtfRequest, ProductBean productBean) {
		ModelAndView modelAndView = new ModelAndView("redirect:register");

		List<MultipartFile> OptionImgList = null;
		List<MultipartFile> DetailImgList = null;

		String reMainImg = new FileRename().restore(mainImg);
		OptionImgList = mtfRequest.getFiles("option");
		DetailImgList = mtfRequest.getFiles("detail");
		
		// product 저장
		productService.insertProduct(productBean);
		String product_id = productService.recentProduct();
		productBean.setProduct_id(product_id);
		
		//메인 image 저장
		productBean.setImg_name(reMainImg);
		productBean.setDepth("0");
		imageService.insertImage(productBean);
		
		/* 이미지 rename and db저장 구간 start */
		// 옵션이미지가 있는 경우
		if (OptionImgList.get(0).getOriginalFilename() != "") {
			for (MultipartFile mf : OptionImgList) {
				String reOptionImg = new FileRename().restore(mf);
				productBean.setImg_name(reOptionImg);
				productBean.setDepth("1");
				imageService.insertImage(productBean);
			}
		}

		// 디테일 이미지가 있는 경우
		if (DetailImgList.get(0).getOriginalFilename() != "") {
			for (MultipartFile mf : DetailImgList) {
				String reDetailImg = new FileRename().restore(mf);
				productBean.setImg_name(reDetailImg);
				productBean.setDepth("2");
				imageService.insertImage(productBean);
			}
		}
		/* 이미지 rename and db저장 구간 end */
		
		/* productoption 저장 구간 */
		// SizeList,amountList 값이 없을 경우
		if(productBean.getSizeList() == null) {
			productService.insertPrdOption(productBean);
		
		}else{ // SizeList,amountList 값이 있을 경우

			// 사이즈/재고량 추가버튼을 눌러서 입력하고 또 입력한 경우
			
			if( !(productBean.getSize_id().equals("0") && productBean.getAmount().equals("")) ) {
				productService.insertPrdOption(productBean);
			}
			
			ArrayList<String> sizeList = productBean.getSizeList();
			ArrayList<String> amountList = productBean.getAmountList();
			
			int arrSize = sizeList.size();
			for(int i = 0 ; i < arrSize ; i++) {
				productBean.setSize_id(sizeList.get(i));
				productBean.setAmount(amountList.get(i));
				productService.insertPrdOption(productBean);
			}
			
		}
		
		return modelAndView;
	}
}
