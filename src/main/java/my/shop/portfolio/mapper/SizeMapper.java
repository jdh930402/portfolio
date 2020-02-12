package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.dto.SizeDto;

public interface SizeMapper {
	// 신발 사이즈를 출력
	@Select("SELECT id, size FROM size ORDER BY size")
	public ArrayList<SizeDto> selectSizeAll();
	
	// 상품 등록시 사용
	@Select("SELECT id, size FROM size WHERE id not in ( ${arrSize} )") // attention: here is $ not #, $ is use to replace with string, # is use to replace ? with param
    public ArrayList<SizeDto> selectArrSize(@Param("arrSize") String arrSize);
	
}
