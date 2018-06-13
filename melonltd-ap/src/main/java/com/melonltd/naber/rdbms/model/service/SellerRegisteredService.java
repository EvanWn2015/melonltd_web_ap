package com.melonltd.naber.rdbms.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
import com.melonltd.naber.rdbms.model.dao.SellerRegisteredDao;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@Service("sellerRegisteredService")
public class SellerRegisteredService {

	@Autowired
	private SellerRegisteredDao sellerRegisteredDao;

	public SellerRegistered save(SellerRegisteredVo vo) {
		vo.setCreate_date(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
		vo.setStatus(SellerRegisteredVo.Status.UNFINISHED.name());
		SellerRegistered info = SellerRegistered.valueOf(vo);
		return sellerRegisteredDao.save(info);
	}

}
