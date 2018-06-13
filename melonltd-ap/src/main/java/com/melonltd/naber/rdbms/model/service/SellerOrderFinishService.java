package com.melonltd.naber.rdbms.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.rdbms.model.bean.SellerOrderFinish;
import com.melonltd.naber.rdbms.model.dao.SellerOrderFinishDao;

@Service("sellerOrderFinishService")
public class SellerOrderFinishService {
	
	@Autowired
	private SellerOrderFinishDao sellerOrderFinishDao;
	
	public void save(SellerOrderFinish info) {
		sellerOrderFinishDao.save(info);
	}
	

}
