package my.shop.portfolio.dao;

import my.shop.portfolio.command.PurchaseBean;

public interface PurchaseDao {
	// 구매목록에 저장
	public boolean insertPurchaseList(PurchaseBean purchaseBean);
}
