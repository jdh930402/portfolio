package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import my.shop.portfolio.command.AddressBean;
import my.shop.portfolio.dto.AddressDto;

public interface AddressMapper {
	// select all
	// 등록한 배송지 정보 출력(기본배송지, 등록번호 순으로 정렬)
	@Select("SELECT id, tel, addr1, addr2, addr3, recipient, depth FROM address WHERE email = #{email} AND NOT depth = 2 ORDER BY depth asc, id desc")
	public ArrayList<AddressDto> selectUserAddress(String email);
	
	// 기본 배송지를 출력
	@Select("SELECT id, tel, addr1, addr2, addr3, recipient, depth FROM address WHERE email = #{email} AND depth =  0")
	public AddressDto selectDefaultAddress(AddressBean addressBean);
	
	// 수정할 배송지의 정보를 출력
	@Select("SELECT id, tel, addr1, addr2, addr3, recipient, depth FROM address WHERE email = #{email} AND id = #{id}")
	public AddressDto selectUpdateAddress(AddressBean addressBean);
	
	// 유저의 기본배송지가 있는지 확인한다.
	@Select("SELECT COUNT(depth) FROM address WHERE email = #{email} AND depth = 0")
	public int countDepth(String email);
	
	// 배송지 아이디가 존재하는지 검사한다.
	@Select("SELECT id FROM address WHERE recipient = #{recipient} AND tel = #{tel} AND addr1 = #{addr1} AND addr2 = #{addr2} AND addr3 = #{addr3} AND email = #{email} limit 1")
	public String selectPurchaseAddress(AddressBean addressBean);
	
	// insert
	// 배송지를 추가
	@Insert("INSERT INTO address (email, tel, addr1, addr2, addr3, recipient, depth) values(#{email}, #{tel}, #{addr1}, #{addr2}, #{addr3}, #{recipient}, #{depth})")
	public boolean insertUserAddress(AddressBean addressBean);
	
	// 새로운 기본배송지 등록시 이전자료가 존재할경우 이전 자료의 기본배송지를 해제
	@Update("UPDATE address SET depth = 1 WHERE email = #{email} AND depth = 0")
	public boolean updateDepth(String email);
	
	// 수정할 배송지를 업데이트
	@Update("UPDATE address SET tel = #{tel}, addr1 = #{addr1}, addr2 = #{addr2}, addr3 = #{addr3}, recipient = #{recipient}, depth = #{depth} WHERE email = #{email} AND id = #{id}")
	public boolean updateAddress(AddressBean addressBean);
	
	// 배송지를 삭제
	@Delete("DELETE FROM address WHERE id = #{id} AND email = #{email}")
	public boolean deleteAddress(AddressBean addressBean);

}
