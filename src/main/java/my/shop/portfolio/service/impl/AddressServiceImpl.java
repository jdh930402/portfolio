package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.dao.AddressDao;
import my.shop.portfolio.dto.AddressDto;
import my.shop.portfolio.service.AddressService;

@Service("addressService")
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	private AddressDao addressDao;
	
	@Override
	// 해당 유저 배송지 모두 출력
	public ArrayList<AddressDto> UserAddress(String email) {
		return addressDao.selectUserAddress(email);
	}
	
	@Override
	// 기본 배송지를 출력
	public AddressDto selectDefaultAddress(AddressBean addressBean) {
		return addressDao.selectDefaultAddress(addressBean);
	}
	
	@Override
	// 수정할 배송지 정보 출력
	public AddressDto updateAddressData(AddressBean addressBean) {
		return addressDao.selectUpdateAddress(addressBean);
	}
	
	@Override
	// 기본배송지 존재 여부 검색
	public int countDepth(String email) {
		return addressDao.countDepth(email);
	}
	
	@Override
	// 배송지 아이디가 존재하는지 검사한다.
	public String selectPurchaseAddress(AddressBean addressBean) {
		return addressDao.selectPurchaseAddress(addressBean);
	}
	
	@Override
	// 배송지 추가
	public boolean insertAddress(AddressBean addressBean) {
		return addressDao.insertUserAddress(addressBean);
	}
	
	@Override
	// 원래 존재하던 기본배송지를 해제
	public boolean updateDepth(String email) {
		return addressDao.updateDepth(email);
	}
	
	@Override
	// 배송지를 수정
	public boolean updateAddress(AddressBean addressBean) {
		return addressDao.updateAddress(addressBean);
	}
	
	@Override
	// 배송지를 삭제
	public boolean deleteAddress(AddressBean addressBean) {
		return addressDao.deleteAddress(addressBean);
	}
}
