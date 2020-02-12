package my.shop.portfolio.service;

import my.shop.portfolio.command.PurchaseBean;

public interface PurchaseService {
	// 구매목록에 저장	
	public boolean insertPurchaseList(PurchaseBean purchaseBean);
}
