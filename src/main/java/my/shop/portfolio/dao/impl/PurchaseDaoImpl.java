package my.shop.portfolio.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import my.shop.portfolio.command.PurchaseBean;
import my.shop.portfolio.dao.PurchaseDao;
import my.shop.portfolio.mapper.PurchaseMapper;

@Repository("purchaseDao")
public class PurchaseDaoImpl implements PurchaseDao {
	
	@Autowired
	private PurchaseMapper purchaseMapper;
	
	@Override
	// 구매목록에 저장	
	public boolean insertPurchaseList(PurchaseBean purchaseBean) {
		return purchaseMapper.insertPurchaseList(purchaseBean);
	}
}
