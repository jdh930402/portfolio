package my.shop.portfolio.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import my.shop.portfolio.dto.SizeDto;

public interface SizeDao {
	// 신발 사이즈를 모두 출력
	public ArrayList<SizeDto> selectSizeAll();
	
	public ArrayList<SizeDto> selectArrSize(@Param("arrSize") String arrSize);
}
