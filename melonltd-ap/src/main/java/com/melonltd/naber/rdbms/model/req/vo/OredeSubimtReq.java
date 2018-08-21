package com.melonltd.naber.rdbms.model.req.vo;

import java.util.List;

import com.google.common.base.MoreObjects;

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
	
	private List<OrderData> orders;

	public String getRestaurant_uuid() {
		return restaurant_uuid;
	}

	public void setRestaurant_uuid(String restaurant_uuid) {
		this.restaurant_uuid = restaurant_uuid;
	}

	public String getFetch_date() {
		return fetch_date;
	}

	public void setFetch_date(String fetch_date) {
		this.fetch_date = fetch_date;
	}

	public String getUser_message() {
		return user_message;
	}

	public void setUser_message(String user_message) {
		this.user_message = user_message;
	}

	public String getRestaurant_name() {
		return restaurant_name;
	}

	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}

	public String getRestaurant_address() {
		return restaurant_address;
	}

	public void setRestaurant_address(String restaurant_address) {
		this.restaurant_address = restaurant_address;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public List<OrderData> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderData> orders) {
		this.orders = orders;
	}

	public String getUse_bonus() {
		return use_bonus;
	}

	public void setUse_bonus(String use_bonus) {
		this.use_bonus = use_bonus;
	}

	public String getCan_discount() {
		return can_discount;
	}

	public void setCan_discount(String can_discount) {
		this.can_discount = can_discount;
	}

	public static OredeSubimtReq ofOrders(List<OrderData> orders) {
		OredeSubimtReq data = new OredeSubimtReq();
		data.orders = orders;
		return data;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurant_uuid", restaurant_uuid)
				.add("can_discount", can_discount)
				.add("fetch_date", fetch_date)
				.add("user_message", user_message)
				.add("use_bonus", use_bonus)
				.add("orders", orders)
				.toString();
	}

	public class OrderData {
		private String category_uuid;
		private String user_name;
		private String user_phone;
		private String restaurant_name;
		private String restaurant_address;
		private String count;
		private FoodItemVo item;

		public String getCategory_uuid() {
			return category_uuid;
		}

		public void setCategory_uuid(String category_uuid) {
			this.category_uuid = category_uuid;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getUser_phone() {
			return user_phone;
		}

		public void setUser_phone(String user_phone) {
			this.user_phone = user_phone;
		}

		public String getRestaurant_name() {
			return restaurant_name;
		}

		public void setRestaurant_name(String restaurant_name) {
			this.restaurant_name = restaurant_name;
		}

		public String getRestaurant_address() {
			return restaurant_address;
		}

		public void setRestaurant_address(String restaurant_address) {
			this.restaurant_address = restaurant_address;
		}

		public String getCount() {
			return count;
		}

		public void setCount(String count) {
			this.count = count;
		}

		public FoodItemVo getItem() {
			return item;
		}

		public void setItem(FoodItemVo item) {
			this.item = item;
		}

		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this.getClass()).add("category_uuid", category_uuid).add("count", count)
					.add("item", item).toString();
		}

	}
}
