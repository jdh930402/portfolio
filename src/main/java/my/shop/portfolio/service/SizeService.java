package my.shop.portfolio.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import my.shop.portfolio.dto.SizeDto;

public interface SizeService {
	
	// 사이즈를 모두 출력
	public ArrayList<SizeDto> sizeAll();

	public ArrayList<SizeDto> selectArrSize(@Param("arrSize") String arrSize);
	
}
