package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.dao.AddressDao;
import my.shop.portfolio.dto.AddressDto;
import my.shop.portfolio.mapper.AddressMapper;

@Repository("addressDao")
public class AddressDaoImpl implements AddressDao{
	
	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	// 등록한 배송지 정보 출력(기본배송지, 등록번호 순으로 정렬)
	public ArrayList<AddressDto> selectUserAddress(String email) {
		return addressMapper.selectUserAddress(email);
	}
	
	@Override
	// 기본 배송지를 출력
	public AddressDto selectDefaultAddress(AddressBean addressBean) {
		return addressMapper.selectDefaultAddress(addressBean);
	}
	
	@Override
	// 수정할 배송지 정보 출력
	public AddressDto selectUpdateAddress(AddressBean addressBean) {
		return addressMapper.selectUpdateAddress(addressBean);
	}
	
	@Override
	// 기본배송지 존재 여부 검색
	public int countDepth(String email) {
		return addressMapper.countDepth(email);
	}
	
	@Override
	// 배송지 아이디가 존재하는지 검사한다.
	public String selectPurchaseAddress(AddressBean addressBean) {
		return addressMapper.selectPurchaseAddress(addressBean);
	}
	
	@Override
	// 배송지 추가
	public boolean insertUserAddress(AddressBean addressBean) {
		return addressMapper.insertUserAddress(addressBean);
	}
	
	@Override
	// 원래 존재하던 기본배송지를 해제
	public boolean updateDepth(String email) {
		return addressMapper.updateDepth(email);
	}
	
	@Override
	// 수정할 배송지를 업데이트
	public boolean updateAddress(AddressBean addressBean) {
		return addressMapper.updateAddress(addressBean);
	}
	
	@Override
	// 배송지를 삭제
	public boolean deleteAddress(AddressBean addressBean) {
		return addressMapper.deleteAddress(addressBean);
	}
}
