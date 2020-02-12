package my.shop.portfolio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.UserDao;
import my.shop.portfolio.dto.UserDto;
import my.shop.portfolio.mapper.UserMapper;

@Repository("userDao")
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	// 유저 전체 인원수
	public int countUserAll() {
		return userMapper.countUserAll();
	}
	
	@Override
	// 유저정보 페이징 처리후 출력
	public ArrayList<UserDto> selectUserAll(Pagination pagination) {
		return userMapper.selectUserAll(pagination);
	}
	
	@Override
	// 로그인시 아이디, 패스워드 체크
	public UserDto selectLogin(UserBean userBean) {
		return userMapper.selectLogin(userBean);
	}
	
	@Override
	// 마이페이지접근시 유저의 정보를 가져온다.
	public UserDto selectUserData(String email) {
		return userMapper.selectUserData(email);
	}
	
	@Override
	// 아이디 중복검사
	public boolean selectCountEmail(String email) {
		return userMapper.selectCountEmail(email);
	}
	
	@Override
	// 유저의 패스워드 확인
	public boolean selectCountCheckPassword(UserBean bean) {
		return userMapper.selectCountCheckPassword(bean);
	}
	
	@Override
	// 회원가입 등록시 사용
	public boolean insertRegiser(UserBean userBean) {
		return userMapper.insertRegiser(userBean);
	}
	
	@Override
	// 이메일 변경시 사용
	public boolean updateUserEmail(UserBean userBean) {
		return userMapper.updateUserEmail(userBean);
	}

	@Override
	// 비밀번호 변경시 사용
	public boolean updateUserPassword(UserBean userBean) {
		return userMapper.updateUserPassword(userBean);
	}
	
	@Override
	// 핸드폰 번호 변경시 사용
	public boolean updateUserTel(UserBean userBean) {
		return userMapper.updateUserTel(userBean);
	}
	
	@Override
	// 회원탈퇴시 사용
	public boolean updateUserWithdrawal(UserBean userBean) {
		return userMapper.updateUserWithdrawal(userBean);
	}

	@Override
	// 강제탈퇴시 사용
	public boolean updateAdminKickOut(String email) {
		return userMapper.updateAdminKickOut(email);
	}
}
