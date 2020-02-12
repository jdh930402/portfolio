package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.dao.ImageDao;
import my.shop.portfolio.dto.ImageDto;
import my.shop.portfolio.mapper.ImageMapper;

@Repository("imageDao")
public class ImageDaoImpl implements ImageDao{
	
	@Autowired
	private ImageMapper imageMapper;
	
	@Override
	// 상품 상세페이지의 이미지
	public ArrayList<ImageDto> singleProductImage(String product_id) {
		// TODO Auto-generated method stub
		return imageMapper.singleProductImage(product_id);
	}
	
	@Override
	// 상품 등록시 이미지 추가
	public boolean insertImage(ProductBean productBean) {
		return imageMapper.insertImage(productBean);
	}
}
