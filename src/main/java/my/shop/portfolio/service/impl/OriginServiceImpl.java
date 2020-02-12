package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.dao.OriginDao;
import my.shop.portfolio.dto.OriginDto;
import my.shop.portfolio.service.OriginService;

@Service("originService")
public class OriginServiceImpl implements OriginService {

	@Autowired
	private OriginDao originDao;

	@Override
	// 원산지 모두 출력
	public ArrayList<OriginDto> originAll() {
		return originDao.selectOriginAll();
	}
}
