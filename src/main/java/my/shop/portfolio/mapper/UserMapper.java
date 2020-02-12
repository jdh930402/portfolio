package my.shop.portfolio.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dto.UserDto;

public interface UserMapper {
	// select all문
	@Select("SELECT email, name, tel, date_format(regdate, '%Y-%m-%d %H:%i:%s') as regdate, withdrawal FROM user ORDER BY withdrawal ASC, regdate DESC  LIMIT #{start}, #{length}")
	public ArrayList<UserDto> selectUserAll(Pagination pagination);
	
	// select one문
	// 로그인시 아이디, 패스워드 체크
	@Select("SELECT COUNT(email) FROM user")
	public int countUserAll();
	
	@Select("SELECT email, name FROM user WHERE email = #{email} AND password = password(password(#{password})) and withdrawal = 0")
	public UserDto selectLogin(UserBean userBean);
	
	// 마이페이지접근시 유저의 정보를 가져온다.
	@Select("SELECT email, name, tel, DATE_FORMAT(regdate, '%Y-%m-%d') AS regdate, withdrawal FROM user WHERE email = #{email}")
	public UserDto selectUserData(String email);
	
	// 회원가입시 아이디 중복검사
	@Select("SELECT COUNT(email) FROM user where email = #{email}")
	public boolean selectCountEmail(String email);
	
	// 마이페이지 접근시 비밀번호 일치검사
	@Select("SELECT COUNT(email) FROM user where email = #{email} AND password = password(password(#{password}))")
	public boolean selectCountCheckPassword(UserBean bean);

	// insert문
	// 회원가입시 사용
	@Insert("INSERT INTO user(email, password, name, tel) VALUES( #{email}, password(password(#{password})), #{name}, #{tel})")
	public boolean insertRegiser(UserBean userBean);
	
	// update문
	// 이메일 변경시 사용
	@Update("UPDATE user SET email = #{newEmail} where email = #{email}")
	public boolean updateUserEmail(UserBean userBean);
	
	// 비밀번호 변경시 사용
	@Update("UPDATE user SET password = password(password(#{password})) where email = #{email}")
	public boolean updateUserPassword(UserBean userBean);
	
	// 핸드폰 번호 변경시 사용
	@Update("UPDATE user SET tel = #{tel} where email = #{email}")
	public boolean updateUserTel(UserBean userBean);
	
	// 회원탈퇴시 사용
	@Update("UPDATE user SET withdrawal = 1 WHERE email = #{email} AND password = password(password(#{modalPassword}))")
	public boolean updateUserWithdrawal(UserBean userBean);
	
	// 관리자가 강제탈퇴 시킬시 사용
	@Update("UPDATE user SET withdrawal = 1 WHERE email = #{email}")
	public boolean updateAdminKickOut(String email);
	
	// delete문
}
