package my.shop.portfolio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.shop.portfolio.command.PurchaseBean;
import my.shop.portfolio.dao.PurchaseDao;
import my.shop.portfolio.service.PurchaseService;

@Service("purchaseService")
public class PurchaseServiceImpl implements PurchaseService {
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Override
	// 구매목록에 저장	
	public boolean insertPurchaseList(PurchaseBean purchaseBean) {
		return purchaseDao.insertPurchaseList(purchaseBean);
	}
}
