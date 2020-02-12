package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.command.ProductBean;
import my.shop.portfolio.dto.ImageDto;

public interface ImageMapper {
	// 상품 상세페이지의 이미지
	@Select("SELECT name, depth FROM image WHERE product_id = #{product_id}")
	public ArrayList<ImageDto> singleProductImage(String product_id);
	
	
	// 상풍 등록시 이미지 추가
	@Insert("INSERT INTO image(product_id, name, depth) VALUES(#{product_id}, #{img_name}, #{depth})")
	public boolean insertImage(ProductBean productBean);
	

	
}
