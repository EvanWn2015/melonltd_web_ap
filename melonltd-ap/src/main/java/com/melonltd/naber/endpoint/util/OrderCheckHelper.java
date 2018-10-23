package com.melonltd.naber.endpoint.util;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import com.melonltd.naber.rdbms.model.req.vo.DemandsItemVo;
import com.melonltd.naber.rdbms.model.req.vo.FoodItemVo;
import com.melonltd.naber.rdbms.model.req.vo.ItemVo;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq;
import com.melonltd.naber.rdbms.model.req.vo.OredeSubimtReq.OrderData;
import com.melonltd.naber.rdbms.model.type.SwitchStatus;
import com.melonltd.naber.rdbms.model.vo.DateRangeVo;
import com.melonltd.naber.rdbms.model.vo.FoodInfoVo;
import com.melonltd.naber.rdbms.model.vo.RestaurantInfoVo;

public class OrderCheckHelper {

	
	public static boolean checkLiveItemData(List<OrderData> orders, List<FoodInfoVo> foodList) {
		List<FoodItemVo> items = orders.stream().map(a -> a.getItem()).collect(Collectors.toList());
		long count = items.stream().filter(o -> {
			Predicate<FoodInfoVo> equalfoodUUID = (a) -> a.getFood_uuid().equals(o.getFood_uuid());
			List<ItemVo> foodScopes = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getScopes().stream()).collect(Collectors.toList());
			List<ItemVo> foodOpts = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getOpts().stream()).collect(Collectors.toList());
			List<DemandsItemVo> foodDemands = foodList.stream().filter(equalfoodUUID)
					.flatMap(r -> r.getFood_data().getDemands().stream()).collect(Collectors.toList());
			long c = o.getDemands().stream()
					.filter(d -> foodDemands.stream().filter(b -> StringUtils.equals(b.getName(), d.getName()))
							.flatMap(b -> b.getDatas().stream()).collect(Collectors.toList()).containsAll(d.getDatas()))
					.count();
			return c == foodDemands.size() && foodScopes.containsAll(o.getScopes())
					&& foodOpts.containsAll(o.getOpts());
		}).count();
		return count == items.size();
	}
	
	
	public static String checkCanOrderTime(OredeSubimtReq req, RestaurantInfoVo vo) {
		String fetch_date = Tools.fromatGMT("HH:mm", req.getFetch_date());
		boolean isStoreOpen = Tools.checkOpenStore(vo.getStore_start(), vo.getStore_end(), fetch_date);
		if (!isStoreOpen) {
			return "您所選的取餐時間，$split並非該商家營業時段。";
		}
		List<DateRangeVo> notCanStoreRangeList = vo.getCan_store_range().stream()
				.filter(a -> SwitchStatus.CLOSE.name().equals(a.getStatus())).collect(Collectors.toList());
		long notCanSize = notCanStoreRangeList.stream().filter(f -> Range
				.<String>between(f.getDate().substring(0, 5), f.getDate().substring(6, 11)).contains(fetch_date))
				.count();
		if (notCanSize > 0) {
			String msg = "";
			for (int i = 0; i < notCanStoreRangeList.size(); i++) {
				msg += "[" + notCanStoreRangeList.get(i).getDate() + "]$split";
			}
			return "您所選的取餐時間，該商家無法接單，$split該商家下列的時間不接單:$split" + msg;
		}

		int nowGMT = Tools.getDayOfYear(req.getFetch_date(), "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'");
		long notBusiness = vo.getNot_business().stream()
				.filter(a -> Tools.getDayOfYear(a, "yyyy-MM-dd'T'HH:mm:ss.SSSS'Z'") == nowGMT).count();
		if (notBusiness > 0) {
			return "您所選的取餐時間，$split該商家今日不營業。";
		}

		boolean status = Range.<String>between(Tools.getNowGMT(), Tools.getPlusDayGMT(3)).contains(req.getFetch_date());
		if (!status) {
			return "無法接受此訂單。";
		}
		
		if (StringUtils.isBlank(req.getCan_discount())) {
			return "";	
		}
		
		if (!vo.getCan_discount().equals(req.getCan_discount()) ) {
			return "商家已經改變品項內容，無法接受此訂單。";
		}

		return "";
	}
	
}
