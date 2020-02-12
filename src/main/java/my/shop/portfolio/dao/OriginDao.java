package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.dto.OriginDto;

public interface OriginDao {
	// 원산지 전체 출력
	public ArrayList<OriginDto> selectOriginAll();
}
