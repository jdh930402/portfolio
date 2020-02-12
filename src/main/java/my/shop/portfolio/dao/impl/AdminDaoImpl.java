package my.shop.portfolio.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dao.AdminDao;
import my.shop.portfolio.dto.AdminDto;
import my.shop.portfolio.mapper.AdminMapper;

@Repository("adminDao")
public class AdminDaoImpl implements AdminDao{
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	// 관리자 로그인 체크
	public AdminDto selectLoginAdmin(AdminBean adminBean) {
		return adminMapper.selectLoginAdmin(adminBean);
	}
}
