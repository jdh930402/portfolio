package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.dto.AddressDto;

public interface AddressDao {
	
	// 등록한 배송지 정보 출력(기본배송지, 등록번호 순으로 정렬)
	public ArrayList<AddressDto> selectUserAddress(String email);
	
	// 기본 배송지를 출력
	public AddressDto selectDefaultAddress(AddressBean addressBean);
	
	// 수정할 배송지 정보 출력
	public AddressDto selectUpdateAddress(AddressBean addressBean);
	
	// 기본배송지 존재 여부 검색
	public int countDepth(String email);
	
	// 배송지 아이디가 존재하는지 검사한다.
	public String selectPurchaseAddress(AddressBean addressBean);
	
	// 배송지 추가
	public boolean insertUserAddress(AddressBean addressBean);
	
	// 원래 존재하던 기본배송지를 해제
	public boolean updateDepth(String email);
	
	// 수정할 배송지를 업데이트
	public boolean updateAddress(AddressBean addressBean);
	
	// 배송지를 삭제
	public boolean deleteAddress(AddressBean addressBean);
}
