package my.shop.portfolio.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.UserBean;
import my.shop.portfolio.common.Pagination;
import my.shop.portfolio.dao.UserDao;
import my.shop.portfolio.dto.UserDto;
import my.shop.portfolio.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	// 유저 페이징 처리 후 출력
	public ArrayList<UserDto> selectUserAll(Pagination pagination) {
		return userDao.selectUserAll(pagination);
	}
	
	@Override
	// 유저 전체인원 수 
	public int countUserAll() {
		return userDao.countUserAll();
	}

	@Override
	// 로그인 체크
	public UserDto loginCheck(UserBean userBean) {
		return userDao.selectLogin(userBean);
	}

	@Override
	// 유저 정보 출력
	public UserDto userData(String email) {
		return userDao.selectUserData(email);
	}

	@Override
	// 이메일 중복체크
	public boolean isExistEmail(String email) {
		return userDao.selectCountEmail(email);
	}

	@Override
	// 비밀번호 일치검사
	public boolean isPasswordMatch(UserBean userBean) {
		return userDao.selectCountCheckPassword(userBean);
	}

	@Override
	// 회원 등록시 사용
	public boolean userRegiser(UserBean userBean) {
		return userDao.insertRegiser(userBean);
	}

	@Override
	// 이메일 변경시 사용
	public boolean updateEmail(UserBean userBean) {
		return userDao.updateUserEmail(userBean);
	}

	@Override
	// 비밀번호 변경시 사용
	public boolean updatePassword(UserBean userBean) {
		return userDao.updateUserPassword(userBean);
	}

	@Override
	// 핸드폰 번호 변경시 사용
	public boolean updateTel(UserBean userBean) {
		return userDao.updateUserTel(userBean);
	}

	@Override
	// 회원탈퇴시 사용
	public boolean withdrawal(UserBean userBean) {
		return userDao.updateUserWithdrawal(userBean);
	}
	
	@Override
	// 강제탈퇴시 사용
	public boolean updateAdminKickOut(String email) {
		return userDao.updateAdminKickOut(email);
	}
}
