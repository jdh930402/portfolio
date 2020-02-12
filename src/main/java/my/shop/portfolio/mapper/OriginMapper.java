package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.dto.OriginDto;

public interface OriginMapper {
	// 원산지를 모두 읽어옴
	@Select("SELECT id, country FROM origin ORDER BY country")
	public ArrayList<OriginDto> selectOriginAll();
}
