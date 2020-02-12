package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.dao.OriginDao;
import my.shop.portfolio.dto.OriginDto;
import my.shop.portfolio.mapper.OriginMapper;

@Repository("originDao")
public class OriginDaoImpl implements OriginDao{
	
	@Autowired
	private OriginMapper originMapper;
	
	@Override
	// 원산지 전부 출력
	public ArrayList<OriginDto> selectOriginAll() {
		return originMapper.selectOriginAll();
	}
}
