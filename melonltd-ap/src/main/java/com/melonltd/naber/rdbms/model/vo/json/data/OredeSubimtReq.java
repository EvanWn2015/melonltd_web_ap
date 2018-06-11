package com.melonltd.naber.rdbms.model.vo.json.data;

import java.util.List;

import com.google.common.base.MoreObjects;

public class OredeSubimtReq {

	private String restaurant_uuid;
	private String fetch_date;
	private String user_message;
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

	public List<OrderData> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderData> orders) {
		this.orders = orders;
	}
	
	
	public static OredeSubimtReq ofOrders (List<OrderData> orders) {
		OredeSubimtReq data = new OredeSubimtReq();
		data.orders = orders;
		return data;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this.getClass())
				.add("restaurant_uuid", restaurant_uuid)
				.add("fetch_date", fetch_date)
				.add("user_message", user_message)
				.add("orders", orders)
				.toString();
	}
	

	public class OrderData {
		private String category_uuid;
		private String food_uuid;
		private FoodItemVo item;

		public String getCategory_uuid() {
			return category_uuid;
		}

		public void setCategory_uuid(String category_uuid) {
			this.category_uuid = category_uuid;
		}

		public String getFood_uuid() {
			return food_uuid;
		}

		public void setFood_uuid(String food_uuid) {
			this.food_uuid = food_uuid;
		}

		public FoodItemVo getItem() {
			return item;
		}

		public void setItem(FoodItemVo item) {
			this.item = item;
		}
		
		@Override
		public String toString() {
			return MoreObjects.toStringHelper(this.getClass())
					.add("category_uuid", category_uuid)
					.add("food_uuid", food_uuid)
					.add("item", item)
					.toString();
		}

	}
}
