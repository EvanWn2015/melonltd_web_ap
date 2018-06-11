package com.melonltd.naber.rdbms.model.stored.service;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.melonltd.naber.endpoint.util.Tools;
import com.melonltd.naber.rdbms.model.stored.bean.UserOrderLogStored;
import com.melonltd.naber.rdbms.model.stored.dao.UserOrderLogStoredDao;
import com.melonltd.naber.rdbms.model.stored.vo.UserOrderLogStoredVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;
import com.melonltd.naber.rdbms.model.vo.json.data.OredeSubimtReq;

@Service("userOrderLogStoredService")
public class UserOrderLogStoredService {

	@Autowired
	private UserOrderLogStoredDao userOrderLogStoredDao;

	public List<UserOrderLogStoredVo> findByAccountUUIDAndPage(String uuid, int page) {
		int inPage = page * 10 - 10;
		List<UserOrderLogStored> list = userOrderLogStoredDao.getRestaurantDistanceByLatlng(uuid, inPage);
		return UserOrderLogStoredVo.valueOfArray(list);
	}
	
	
	public String[] getFoodStatusOpenByUUIDs(String[] uuids) {
		System.out.println(new String[]{"AA", "DD"}.toString());
		String ids = StringUtils.strip(Arrays.asList(uuids).toString(), "[]");
		return userOrderLogStoredDao.getFoodStatusByUUIDs(ids.replace(" ", ""));
	}
	
	
	public UserOrderLogStoredVo submitOrder(String accountUUID ,String orderUUID, RestaurantInfoVo vo, OredeSubimtReq req, String price, String bonus, String jsonData) {
		String restaurantUUID = vo.getRestaurant_uuid();
		String restaurantName = vo.getName();
		String restaurantAddress = vo.getAddress();
		String userMassage = req.getUser_message();
		String data = Tools.getNowUTC();
		String orderPrice = price;
		String orderBonus = bonus;
		String fetchDate = req.getFetch_date();
		String orderData = jsonData;
		UserOrderLogStored info = userOrderLogStoredDao.submitOrder(orderUUID, accountUUID, restaurantUUID, restaurantName, restaurantAddress, userMassage, data, orderPrice, orderBonus, fetchDate, orderData);
		return UserOrderLogStoredVo.valueOf(info);
	}
	
}
