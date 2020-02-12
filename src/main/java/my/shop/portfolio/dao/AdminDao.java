package my.shop.portfolio.dao;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dto.AdminDto;

public interface AdminDao {
	// 관리자 로그인 체크
	public AdminDto selectLoginAdmin(AdminBean adminBean);
}
