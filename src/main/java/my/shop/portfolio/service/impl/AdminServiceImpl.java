package my.shop.portfolio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dao.AdminDao;
import my.shop.portfolio.dto.AdminDto;
import my.shop.portfolio.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminDao adminDao;
	
	@Override
	// 관리자 로그인 체크
	public AdminDto loginAdmin(AdminBean adminBean) {
		return adminDao.selectLoginAdmin(adminBean);
	}

}
