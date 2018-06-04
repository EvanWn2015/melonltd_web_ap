package com.melonltd.naber.rdbms.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.bean.SellerRegistered;
import com.melonltd.naber.rdbms.model.dao.SellerRegisteredDao;
import com.melonltd.naber.rdbms.model.dao.stored.procedure.TestDao;
import com.melonltd.naber.rdbms.model.vo.SellerRegisteredVo;

@Service("sellerRegisteredService")
public class SellerRegisteredService {

	@Autowired
	private SellerRegisteredDao sellerRegisteredDao;

	@Autowired
	private TestDao testDao;

	public SellerRegistered save(SellerRegisteredVo vo) {
//
//		List<SellerRegistered> test = testDao.text("0987654321");
		List<SellerRegistered> test = testDao.text("0987654321");
		
//		for (Map map : test) {
//			System.out.println(map);
//			System.out.println(map);
//		}
		System.out.println(test);

		vo.setCreate_date(Tools.getGMTDate("yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'"));
		vo.setStatus(SellerRegisteredVo.Status.UNFINISHED.name());
		
		SellerRegistered info = SellerRegistered.valueOf(vo);
		return sellerRegisteredDao.save(info);
	}

}
