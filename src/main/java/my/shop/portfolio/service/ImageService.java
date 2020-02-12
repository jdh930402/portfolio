package my.shop.portfolio.service;

import java.util.ArrayList;

import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.dto.ImageDto;

public interface ImageService {
	
	// 상품 상세페이지의 이미지
	public ArrayList<ImageDto> singleProductImage(String product_id);
	
	// 상품 등록시 이미지 추가
	public boolean insertImage(ProductBean productBean);
}
