package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.dao.SizeDao;
import my.shop.portfolio.dto.SizeDto;
import my.shop.portfolio.service.SizeService;

@Service("sizeService")
public class SizeServiceImpl implements SizeService{
	
	@Autowired
	private SizeDao sizeDao;
	
	@Override
	public ArrayList<SizeDto> sizeAll() {
		return sizeDao.selectSizeAll();
	}
	
	@Override
	public ArrayList<SizeDto> selectArrSize(@Param("arrSize") String arrSize){	
		return sizeDao.selectArrSize(arrSize);		
	}
}
