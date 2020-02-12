package my.shop.portfolio.service;

import java.util.ArrayList;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.UserDto;

public interface UserService {
	
	// 유저 페이징 처리 후 출력
	public ArrayList<UserDto> selectUserAll(Pagination pagination);
	
	// 유저 전체 인원수
	public int countUserAll();
	
	// 로그인시 아이디, 패스워드 체크
	public UserDto loginCheck(UserBean userBean);
	
	// 마이페이지접근시 유저의 정보를 가져온다.
	public UserDto userData(String email);
	
	// 아이디 중복검사
	public boolean isExistEmail(String email);
	
	// 비밀번호 일치 검사
	public boolean isPasswordMatch(UserBean userBean);
	
	// 회원가입시에 사용
	public boolean userRegiser(UserBean userBean);
	
	// 이메일 변경시 사용
	public boolean updateEmail(UserBean userBean);
	
	// 비밀번호 변경시 사용
	public boolean updatePassword(UserBean userBean);
	
	// 핸드폰 번호 변경시 사용
	public boolean updateTel(UserBean userBean);
	
	// 회원탈퇴시 사용
	public boolean withdrawal(UserBean userBean);
	
	// 강제탈퇴시 사용
	public boolean updateAdminKickOut(String email);
}
