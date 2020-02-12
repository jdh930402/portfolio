package my.shop.portfolio.mapper;

import org.apache.ibatis.annotations.Insert;

import my.shop.portfolio.command.PurchaseBean;

public interface PurchaseMapper {

	// 구매목록에 저장
	@Insert("INSERT INTO purchase(purchase_number, address_id, user_email, product_id, productoption_id, quantity, price) values(#{purchaseNumber} , #{address_id}, #{user_email}, #{product_id}, #{productoption_id}, #{quantity}, #{price})")
	public boolean insertPurchaseList(PurchaseBean purchaseBean);
}
