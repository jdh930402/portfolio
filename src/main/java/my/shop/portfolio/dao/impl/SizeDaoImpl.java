package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.dao.SizeDao;
import my.shop.portfolio.dto.SizeDto;
import my.shop.portfolio.mapper.SizeMapper;

@Repository("sizeDao")
public class SizeDaoImpl implements SizeDao{
	
	@Autowired
	private SizeMapper sizeMapper;
	
	@Override
	// 신발 사이즈를 모두 출력
	public ArrayList<SizeDto> selectSizeAll() {
		return sizeMapper.selectSizeAll();
	}
	
	@Override
	public ArrayList<SizeDto> selectArrSize(@Param("arrSize") String arrSize){
		return sizeMapper.selectArrSize(arrSize);
	}
	
}
