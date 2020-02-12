package my.shop.portfolio.mapper;

import org.apache.ibatis.annotations.Select;

import my.shop.portfolio.command.AdminBean;
import my.shop.portfolio.dto.AdminDto;

public interface AdminMapper {
	@Select("SELECT id, password FROM admin WHERE id=#{id} AND password = password(password(#{password}))")
	public AdminDto selectLoginAdmin(AdminBean adminBean);
}
