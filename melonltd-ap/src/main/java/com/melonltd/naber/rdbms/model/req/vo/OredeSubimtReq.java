package com.melonltd.naber.rdbms.model.req.vo;

import java.util.List;

import com.melonltd.naber.endpoint.util.JsonHelper;

import lombok.Data;

@Data
public class OredeSubimtReq {

	private String restaurant_uuid;
	private String fetch_date;
	private String user_message;
	private String restaurant_name;
	private String restaurant_address;
	private String user_name;
	private String user_phone;
	private String use_bonus;
	private String can_discount;
	private OrderType order_type;
	private List<OrderData> orders;

	@Override
	public String toString() {
		return JsonHelper.toJson(this);
	}

	public static OredeSubimtReq ofOrders(List<OrderData> orders) {
		OredeSubimtReq data = new OredeSubimtReq();
		data.orders = orders;
		return data;
	}

	public static OrderType setDefault() {
		return new OrderType("ORIGINAL", "OUT");
	}

	/**
	 * billing : ORIGINAL, DISCOUNT, COUPON delivery : IN, OUT, SEND
	 * 
	 * @author evan
	 *
	 */
	@Data
	public static class OrderType {
		private String billing;
		private String delivery;

		OrderType() {
		}

		OrderType(String billing, String delivery) {
			this.billing = billing;
			this.delivery = delivery;
		}

		@Override
		public String toString() {
			return JsonHelper.toJson(this);
		}
	}

	@Data
	public class OrderData {
		private String category_uuid;
		private String user_name;
		private String user_phone;
		private String restaurant_name;
		private String restaurant_address;
		private String count;
		private FoodItemVo item;

		@Override
		public String toString() {
			return JsonHelper.toJson(this);
		}
	}
}
