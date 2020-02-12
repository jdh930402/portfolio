package my.shop.portfolio.service;

import java.util.ArrayList;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.dto.AddressDto;

public interface AddressService {
	// 해당 유저 배송지를 모두 출력
	public ArrayList<AddressDto> UserAddress(String email);
	
	// 기본 배송지를 출력
	public AddressDto selectDefaultAddress(AddressBean addressBean);
	
	// 수정할 배송지 정보 출력
	public AddressDto updateAddressData(AddressBean addressBean);
	
	// 기본배송지 존재 여부 검색
	public int countDepth(String email);
	
	// 배송지 아이디가 존재하는지 검사한다.
	public String selectPurchaseAddress(AddressBean addressBean);
	
	// 배송지 추가
	public boolean insertAddress(AddressBean addressBean);
	
	// 원래 존재하던 기본배송지를 해제
	public boolean updateDepth(String email);
	
	// 수정할 배송지를 업데이트
	public boolean updateAddress(AddressBean addressBean);
	
	// 배송지를 삭제
	public boolean deleteAddress(AddressBean addressBean);
}
