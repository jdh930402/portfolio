package my.shop.portfolio.dao;

import java.util.ArrayList;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.UserDto;

public interface UserDao {
	// select all
	public ArrayList<UserDto> selectUserAll(Pagination pagination);
	
	// select one
	// 유저 전체 인원수
	public int countUserAll();
	
	// 로그인시 아이디, 패스워드 체크
	public UserDto selectLogin(UserBean userBean);
	
	// 마이페이지접근시 유저의 정보를 가져온다.
	public UserDto selectUserData(String email);
	
	// 아이디 중복검사
	public boolean selectCountEmail(String email);
	
	// 회원가입시 비밀번호 일치검사
	public boolean selectCountCheckPassword(UserBean bean);
	
	// insert
	// 회원가입 등록시 사용
	public boolean insertRegiser(UserBean userBean);
	
	// update
	// 이메일 변경
	public boolean updateUserEmail(UserBean userBean);
	
	// 비밀번호 변경
	public boolean updateUserPassword(UserBean userBean);
	
	// 핸드폰 번호 변경
	public boolean updateUserTel(UserBean userBean);
	
	// 회원탈퇴
	public boolean updateUserWithdrawal(UserBean userBean);
	
	// 관리자가 강제탈퇴 시킬시 사용
	public boolean updateAdminKickOut(String email);
}
