package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.dao.ImageDao;
import my.shop.portfolio.dto.ImageDto;
import my.shop.portfolio.service.ImageService;

@Service("imageService")
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageDao imageDao;
	
	@Override
	// 상품 상세페이지의 이미지
	public ArrayList<ImageDto> singleProductImage(String product_id) {
		// TODO Auto-generated method stub
		return imageDao.singleProductImage(product_id);
	}
	
	@Override
	// 상품 등록시 이미지 추가
	public boolean insertImage(ProductBean productBean) {
		return imageDao.insertImage(productBean);
	}

}
