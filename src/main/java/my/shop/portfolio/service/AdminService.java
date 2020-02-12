package my.shop.portfolio.service;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dto.AdminDto;

public interface AdminService {
	// 관리자 로그인 체크
	public AdminDto loginAdmin(AdminBean adminBean);
}
